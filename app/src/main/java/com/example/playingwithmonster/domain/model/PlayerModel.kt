package com.example.playingwithmonster.domain.model

import kotlin.random.Random

data class PlayerModel(
    override val id: Int?,
    override val attack: Int,
    override val defense: Int,
    override val maxHealth: Double,
    override var currentHealth: Double,
    override val damage: Double,
    var numberOfTimes: Int
) : CreatureModel {

    companion object PlayerFactory : CreatureAbstractFactory() {
        override fun create(
            /*id: Int?,
            attack: Int, defense: Int,
            maxHealth: Double, currentHealth: Double,
            damage: Double, numberOfTimes: Int?*/
        ): CreatureModel {
            return PlayerModel.invoke(
                /*id = id,
                attack = attack,
                defense = defense,
                maxHealth = maxHealth,
                currentHealth = currentHealth,
                damage = damage,
                numberOfTimes = numberOfTimes*/
            )

            /*return Player(id = id,
                attack = attack,
                defense = defense,
                maxHealth = maxHealth,
                currentHealth = currentHealth,
                damage = damage,
                numberOfTimes = numberOfTimes
            )*/
        }

        operator fun invoke(
            /*id: Int?,
            attack: Int,
            defense: Int,
            maxHealth: Double,
            currentHealth: Double,
            damage: Double,
            numberOfTimes: Int?*/
           ) = PlayerModel(
               /*id = id ?: null,
               attack = attack,
               defense = defense,
               maxHealth = maxHealth,
               currentHealth = currentHealth,
               damage = damage,
               numberOfTimes = numberOfTimes ?: 4*/

              id = null,
              attack = Random.nextInt(1,30),
              defense = Random.nextInt(1,30),
              maxHealth = Random.nextDouble(1.0,30.0),
              currentHealth = Random.nextDouble(1.0,30.0),
              damage = Random.nextDouble(1.0,6.0),
              numberOfTimes = 4
          )

      }

      override fun hit(attackerdamage: Double) {
          currentHealth =  currentHealth.minus(attackerdamage) // rnd
      }

      fun healYourself() {
          if (numberOfTimes > 0) {
              numberOfTimes -= 1
              currentHealth = currentHealth.plus((maxHealth * 0.3))
          }
      }

   }


