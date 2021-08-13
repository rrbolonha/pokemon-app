package com.example.pokemonapp.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.pokemonapp.data.database.AppDatabase
import com.example.pokemonapp.data.entities.SeasonLocalEntity
import com.example.pokemonapp.domain.entities.SeasonStatusType.NOT_SELECTED
import kotlinx.coroutines.coroutineScope

class SeasonWorker(private val context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            val database = AppDatabase.getInstance(context)
            val seasonList = listOf(
                SeasonLocalEntity(1, "Season One", 1, 151, NOT_SELECTED),
                SeasonLocalEntity(2, "Season Two", 152, 251, NOT_SELECTED),
                SeasonLocalEntity(3, "Season Three", 252, 386, NOT_SELECTED),
                SeasonLocalEntity(4, "Season Four", 387, 494, NOT_SELECTED),
                SeasonLocalEntity(5, "Season Five", 495, 649, NOT_SELECTED),
                SeasonLocalEntity(6, "Season Six", 650, 721, NOT_SELECTED),
                SeasonLocalEntity(7, "Season Seven", 722, 809, NOT_SELECTED),
                SeasonLocalEntity(7, "Season Eight", 810, 898, NOT_SELECTED),
            )
            database.seasonDao().insert(seasonList)

            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }

}