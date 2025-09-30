package com.example.playingwithmonster.domain.usecaseImpl

import com.example.playingwithmonster.domain.irepository.ICreatureRepository
import com.example.playingwithmonster.domain.iusecase.IUseCaseCalculateAttackModifier
import com.example.playingwithmonster.domain.iusecase.IUseCaseHealCreature
import com.example.playingwithmonster.domain.model.PlayerModel
import javax.inject.Inject
import kotlin.math.roundToInt

class UseCaseHealCreatureImpl @Inject constructor(val repository: ICreatureRepository) : IUseCaseHealCreature {
    override fun invoke(currentHealth: Double, maxHealth: Double, countUnusedFirstAidKits: Int): Double {
        if (countUnusedFirstAidKits > 0){
            val percent: Double = maxHealth * 0.3
            val answer = currentHealth + percent
            return answer
        }
        return currentHealth
    }
}