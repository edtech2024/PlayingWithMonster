package com.example.playingwithmonster.domain.model

interface CreatureModel {
    val id: Int?
    val attack: Int
    val defense: Int
    val maxHealth: Double
    var currentHealth: Double
    val damage: Double

    fun hit(attackerdamage: Double)

}


/*

fun hit(attackerdamage: Double?) {
        currentHealth = attackerdamage?.let { currentHealth?.minus(it) } // rnd
    }
}

/companion object Factory() :
    override fun create(): Monster {
        return Monster(id, attack, defense, maxHealth, currentHealth, damage)
    }

    override fun create(): Player {
        return Player(id, attack, defense, maxHealth, currentHealth, damage)
    }

 */