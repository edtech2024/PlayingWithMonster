package com.example.playingwithmonster.domain.model

import kotlin.random.Random

data class MonsterModel(
    override val id: Int?,
    override val attack: Int,
    override val defense: Int,
    override val maxHealth: Double,
    override var currentHealth: Double,
    override val damage: Double
) : CreatureModel {

    companion object MonsterFactory : CreatureAbstractFactory() {
        override fun create(
            /*id: Int?,
            attack: Int, defense: Int,
            maxHealth: Double, currentHealth: Double,
            damage: Double, numberOfTimes: Int?*/
        ): MonsterModel {

            return MonsterModel.invoke(
                /*id = id,
                attack = attack,
                defense = defense,
                maxHealth = maxHealth,
                currentHealth = currentHealth,
                damage = damage*/
            )

            /*return Monster(
                id = id,
                attack = attack,
                defense = defense,
                maxHealth = maxHealth,
                currentHealth = currentHealth,
                damage = damage
            )*/

        }

        operator fun invoke(
            /*id: Int?,
            attack: Int,
            defense: Int,
            maxHealth: Double,
            currentHealth: Double,
            damage: Double*/
        ) = MonsterModel(
            /*id = id ?: null,
            attack = attack,
            defense = defense,
            maxHealth = maxHealth,
            currentHealth = currentHealth,
            damage = damage*/

            id = null,
            attack = Random.nextInt(1,30),
            defense = Random.nextInt(1,30),
            maxHealth = Random.nextDouble(1.0,30.0),
            currentHealth = Random.nextDouble(1.0,30.0),
            damage = Random.nextDouble(1.0,6.0)
        )

    }

    override fun hit(attackerdamage: Double) {
        currentHealth =  currentHealth.minus(attackerdamage) // rnd
    }

}
