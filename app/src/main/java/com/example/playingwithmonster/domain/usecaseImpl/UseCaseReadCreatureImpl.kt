package com.example.playingwithmonster.domain.usecaseImpl

import com.example.playingwithmonster.domain.iusecase.IUseCaseReadCreature
import com.example.playingwithmonster.domain.model.PlayerModel
import com.example.playingwithmonster.domain.irepository.ICreatureRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UseCaseReadCreatureImpl @Inject constructor(val repository: ICreatureRepository) : IUseCaseReadCreature {
    /*override fun invoke(): Flow<List<PlayerModel>> {
        return repository.queryItemsfromDatabase()
    }*/
    override fun invoke(): List<PlayerModel> {
        return repository.queryItemsfromDatabase()
    }
}