package com.example.playingwithmonster.domain.usecaseImpl

import com.example.playingwithmonster.domain.irepository.ICreatureRepository
import com.example.playingwithmonster.domain.iusecase.IUseCaseCalculateAttackSuccess
import javax.inject.Inject
import kotlin.random.Random

class UseCaseCalculateAttackSuccessImpl @Inject constructor(val repository: ICreatureRepository) : IUseCaseCalculateAttackSuccess {
    /*override fun invoke(dice: Int): Boolean {
        return dice >= 5
    }*/

    override fun invoke(dice: List<Int>): Boolean {
        for (item in dice) {
            if (item >= 5) return true
        }
        return false
    }
}