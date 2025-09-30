package com.example.playingwithmonster.domain.usecaseImpl

import com.example.playingwithmonster.domain.iusecase.IUseCaseCreateCreature
import com.example.playingwithmonster.domain.model.CreatureModel
import com.example.playingwithmonster.domain.model.PlayerModel
import com.example.playingwithmonster.domain.irepository.ICreatureRepository
import javax.inject.Inject

class UseCaseCreateCreatureImpl @Inject constructor(val repository: ICreatureRepository) : IUseCaseCreateCreature {
    override suspend fun invoke(item: PlayerModel): Int {
        return repository.insertItem(item).toInt()
    }
}