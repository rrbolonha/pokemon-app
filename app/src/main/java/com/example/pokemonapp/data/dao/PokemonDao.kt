package com.example.pokemonapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokemonapp.data.entities.PokemonLocalEntity

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemonList: List<PokemonLocalEntity>)

    @Query("SELECT * FROM pokemons")
    suspend fun getAll(): List<PokemonLocalEntity>

    @Query("SELECT EXISTS(SELECT 1 FROM pokemons)")
    suspend fun hasData(): Boolean

    @Query("SELECT * FROM pokemons WHERE id = :id")
    suspend fun getById(id: Int): PokemonLocalEntity

    @Query("DELETE FROM pokemons")
    suspend fun delete()

}