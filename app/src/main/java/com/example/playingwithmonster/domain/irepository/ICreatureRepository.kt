package com.example.playingwithmonster.domain.irepository

import com.example.playingwithmonster.domain.model.CreatureModel
import com.example.playingwithmonster.domain.model.PlayerModel
import kotlinx.coroutines.flow.Flow

interface ICreatureRepository {

    suspend fun insertItem(item: PlayerModel): Long

    suspend fun updateItem(item: PlayerModel): Int

    suspend fun deleteItems()

    //fun queryItemsfromDatabase(): Flow<List<PlayerModel>>
    fun queryItemsfromDatabase(): List<PlayerModel>

    suspend fun getCountRows(): Int

}