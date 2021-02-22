package com.example.rickandmortyandroid.database.dao

import androidx.room.*
import com.example.rickandmortyandroid.models.data.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged


@Dao
interface CharacterDAO {
    @Query("SELECT * FROM Character")
    fun getAll(): Flow<List<Character>>


    @Query("SELECT * FROM Character WHERE id <= :page*20 and id > (:page*20 -20)")
    suspend fun getPage(page: Int): List<Character>

    @Query("SELECT * FROM Character WHERE id = :id")
    fun getById(id: Long): Flow<Character>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addChar(char: Character)

    @Delete
    suspend fun removeById(char: Character)

    fun getCharDistinctUntilChanged(id: Long) = getById(id).distinctUntilChanged()
}


