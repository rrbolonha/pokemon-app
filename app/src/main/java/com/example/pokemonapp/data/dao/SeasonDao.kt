package com.example.pokemonapp.data.dao

import androidx.room.*
import com.example.pokemonapp.data.entities.SeasonLocalEntity

@Dao
interface SeasonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(seasonList: List<SeasonLocalEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(seasonLocalEntityList: List<SeasonLocalEntity>)

    @Query("SELECT * FROM seasons")
    suspend fun getAll(): List<SeasonLocalEntity>

}