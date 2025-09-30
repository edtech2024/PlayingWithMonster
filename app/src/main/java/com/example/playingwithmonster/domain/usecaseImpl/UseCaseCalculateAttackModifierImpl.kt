package com.example.playingwithmonster.domain.usecaseImpl

import com.example.playingwithmonster.domain.irepository.ICreatureRepository
import com.example.playingwithmonster.domain.iusecase.IUseCaseCalculateAttackModifier
import javax.inject.Inject

class UseCaseCalculateAttackModifierImpl @Inject constructor(val repository: ICreatureRepository) : IUseCaseCalculateAttackModifier {
    override fun invoke(attacksOfTheAttacker: Int, defensesOfTheDefender: Int): Int {
        return attacksOfTheAttacker - defensesOfTheDefender + 1
    }
}