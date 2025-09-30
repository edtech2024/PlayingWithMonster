package com.example.playingwithmonster.domain.iusecase

interface IUseCaseCalculateForceImpact {
    operator fun invoke(attackersDamage: Double, defendersHealth: Double): Double
}