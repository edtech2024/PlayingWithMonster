package com.example.playingwithmonster.domain.usecaseImpl

import com.example.playingwithmonster.domain.irepository.ICreatureRepository
import com.example.playingwithmonster.domain.iusecase.IUseCaseThrowDice
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.random.Random

class UseCaseThrowDiceImpl @Inject constructor(val repository: ICreatureRepository) : IUseCaseThrowDice {
    //override fun invoke(attackModifier: Int): Int {
    /*override fun invoke(attackModifier: Int): Boolean {

        while (attackModifier > 0) {
            var a = Random.nextInt(1, 6)
            attackModifier.minus(1)
            if (a >= 5) {
                return true
            }
        }

        return false

    }*/

    /*override fun invoke(attackModifier: Int): Flow<List<Int>> {

        return flow<List<Int>> {
            while(attackModifier > 0) {
                attackModifier.minus(1)
                val response = Random.nextInt(1, 6)
                var items: List<Int> = listOf()
                items += response
                emit(items)
            }
        }.flowOn(Dispatchers.Default)

    }*/

    override fun invoke(attackModifier: Int): List<Int> {
        var items: List<Int> = listOf()
        if (attackModifier > 0){
            var i  = attackModifier
            while(i > 0) {
                val response = Random.nextInt(1, 6)
                items += response
                i -= 1
            }
            return items
        } else {
            val response = Random.nextInt(1, 6)
            items += response
            return items
        }
    }
}
