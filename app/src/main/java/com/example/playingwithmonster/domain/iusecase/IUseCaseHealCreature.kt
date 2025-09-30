package com.example.playingwithmonster.domain.iusecase

import com.example.playingwithmonster.domain.model.PlayerModel
import kotlinx.coroutines.flow.Flow

interface IUseCaseHealCreature {
    operator fun invoke(currentHealth: Double, maxHealth: Double, countUnusedFirstAidKits: Int): Double
}