package com.example.playingwithmonster.domain.iusecase

import kotlinx.coroutines.flow.Flow

interface IUseCaseThrowDice {
    //operator fun invoke(attackModifier: Int): Int
    //operator fun invoke(attackModifier: Int): Boolean
    //operator fun invoke(attackModifier: Int): Flow<List<Int>>
    operator fun invoke(attackModifier: Int): List<Int>
}