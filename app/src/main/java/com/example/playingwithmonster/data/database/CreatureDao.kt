package com.example.playingwithmonster.data.database

import androidx.room.*
import com.example.playingwithmonster.data.entity.PlayerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CreatureDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(playerEntity: PlayerEntity): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(playerEntity: PlayerEntity): Int

    @Query("DELETE FROM players")
    suspend fun deleteAll()

    /*@Query("SELECT * FROM players")
    fun getAllItems(): Flow<List<PlayerEntity>>*/
    @Query("SELECT * FROM players")
    fun getAllItems(): List<PlayerEntity>

    @Query("SELECT COUNT(*) FROM players")
    fun getCount(): Int

}