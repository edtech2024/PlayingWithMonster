package com.example.playingwithmonster.domain.usecaseImpl

import com.example.playingwithmonster.domain.irepository.ICreatureRepository
import com.example.playingwithmonster.domain.iusecase.IUseCaseDeleteCreature
import javax.inject.Inject

class UseCaseDeleteCreatureImpl @Inject constructor(val repository: ICreatureRepository) : IUseCaseDeleteCreature {
    override suspend fun invoke() {
        repository.deleteItems()
    }

}