package com.example.pokemonapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.pokemonapp.data.dao.PokemonDao
import com.example.pokemonapp.data.dao.SeasonDao
import com.example.pokemonapp.data.entities.PokemonLocalEntity
import com.example.pokemonapp.data.entities.SeasonLocalEntity
import com.example.pokemonapp.data.workers.SeasonWorker
import com.example.pokemonapp.domain.entities.SeasonsStatusTypeConverter


const val DATABASE_NAME = "pokemons-db.db"

@Database(
    entities = [PokemonLocalEntity::class, SeasonLocalEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    SeasonsStatusTypeConverter::class,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
    abstract fun seasonDao(): SeasonDao

    companion object {

        private val instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context)
            }

        private fun buildDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                //.allowMainThreadQueries() // TODO: 7/14/2021 - remove when adjust delete database
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val request = OneTimeWorkRequestBuilder<SeasonWorker>().build()
                        WorkManager.getInstance(context).enqueue(request)
                    }
                }).build()

        fun deleteDatabase(context: Context): Boolean =
            getInstance(context).let {
                it.clearAllTables()
                true
            } ?: false
    }

}