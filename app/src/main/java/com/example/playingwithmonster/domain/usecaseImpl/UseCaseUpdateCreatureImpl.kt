package com.example.playingwithmonster.domain.usecaseImpl

import com.example.playingwithmonster.domain.iusecase.IUseCaseUpdateCreature
import com.example.playingwithmonster.domain.model.CreatureModel
import com.example.playingwithmonster.domain.model.PlayerModel
import com.example.playingwithmonster.domain.irepository.ICreatureRepository
import javax.inject.Inject

class UseCaseUpdateCreatureImpl @Inject constructor(val repository: ICreatureRepository) :
    IUseCaseUpdateCreature {
    override suspend fun invoke(item: PlayerModel): Int {
        return repository.updateItem(item)
    }
}
