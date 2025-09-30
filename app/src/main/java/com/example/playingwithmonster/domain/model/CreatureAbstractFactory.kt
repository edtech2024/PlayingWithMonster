package com.example.playingwithmonster.domain.model

abstract class CreatureAbstractFactory {
    abstract fun create(
        /*id: Int?,
        attack: Int, defense: Int,
        maxHealth: Double, currentHealth: Double,
        damage: Double, numberOfTimes: Int?*/
    ) : CreatureModel

    /*fun hit(attackerdamage: Double?){
        val creature = create()
        creature.hit(attackerdamage)
    }*/

}