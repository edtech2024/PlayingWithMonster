package com.example.playingwithmonster.domain.iusecase

import com.example.playingwithmonster.domain.model.PlayerModel
import kotlinx.coroutines.flow.Flow

interface IUseCaseReadCreature {
    //operator fun invoke(): Flow<List<PlayerModel>>
    operator fun invoke(): List<PlayerModel>
}