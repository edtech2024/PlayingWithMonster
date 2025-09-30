package com.example.playingwithmonster.domain.iusecase

interface IUseCaseCalculateAttackModifier {
    operator fun invoke(attacksOfTheAttacker: Int, defensesOfTheDefender: Int): Int
}