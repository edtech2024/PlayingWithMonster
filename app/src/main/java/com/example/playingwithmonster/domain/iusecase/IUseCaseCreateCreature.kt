package com.example.playingwithmonster.domain.iusecase

import com.example.playingwithmonster.domain.model.PlayerModel

interface IUseCaseCreateCreature {
    suspend operator fun invoke(item: PlayerModel): Int
}