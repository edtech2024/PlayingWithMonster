package com.example.playingwithmonster.domain.usecaseImpl

import com.example.playingwithmonster.domain.irepository.ICreatureRepository
import com.example.playingwithmonster.domain.iusecase.IUseCaseCalculateForceImpact
import javax.inject.Inject
import kotlin.random.Random

class UseCaseCalculateForceImpactImpl @Inject constructor(val repository: ICreatureRepository) : IUseCaseCalculateForceImpact {
    override fun invoke(attackersDamage: Double, defendersHealth: Double): Double {
        return if (defendersHealth > 0.0) defendersHealth.minus(Random.nextDouble(0.0, attackersDamage))
        else defendersHealth
    }
}