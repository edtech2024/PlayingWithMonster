package com.example.playingwithmonster.domain.iusecase

interface IUseCaseCalculateAttackSuccess {
    //operator fun invoke(dice: Int): Boolean
    operator fun invoke(dice: List<Int>): Boolean
}