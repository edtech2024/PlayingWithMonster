package com.example.playingwithmonster.domain.iusecase

import com.example.playingwithmonster.domain.model.PlayerModel

interface IUseCaseUpdateCreature {
    suspend operator fun invoke(item: PlayerModel): Int
}