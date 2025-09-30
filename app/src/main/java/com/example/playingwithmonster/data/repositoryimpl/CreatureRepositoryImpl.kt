package com.example.playingwithmonster.data.repositoryimpl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.example.playingwithmonster.domain.model.PlayerModel
import com.example.playingwithmonster.data.database.CreatureDao
import com.example.playingwithmonster.data.mapper.EntityToModelMapper
import com.example.playingwithmonster.data.mapper.ModelToEntityMapper
import com.example.playingwithmonster.domain.irepository.ICreatureRepository
import com.example.playingwithmonster.domain.model.CreatureModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class CreatureRepositoryImpl @Inject constructor(val itemDao: CreatureDao,
                                             val modelToEntity: ModelToEntityMapper,
                                             val entityToModel: EntityToModelMapper
) : ICreatureRepository {

    override suspend fun insertItem(item: PlayerModel): Long {
        return itemDao.insert(modelToEntity.map(item))
    }

    override suspend fun updateItem(item: PlayerModel): Int {
        return itemDao.update(modelToEntity.map(item))
    }

    override suspend fun deleteItems() {
        itemDao.deleteAll()
    }

    /*override fun queryItemsfromDatabase(): Flow<List<PlayerModel>> {
        return itemDao.getAllItems().map { entityToModel.mapAll(it) }
    }*/
    override fun queryItemsfromDatabase(): List<PlayerModel> {
        return entityToModel.mapAll(itemDao.getAllItems())
    }

    override suspend fun getCountRows(): Int {
        return itemDao.getCount()
    }

}
