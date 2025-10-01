package com.example.playingwithmonster.presentation.uistate

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

@Parcelize
data class MonsterUI public constructor(
    var id: String,
    var attack: String,
    var defense: String,
    var maxHealth: String,
    var currentHealth: String,
    var damage: String
) : Parcelable {
    companion object {
        var health = Random.nextDouble(1.0,30.0).toString()

        operator fun invoke(
            id:String,
            attack: String,
            defense: String,
            maxHealth: String,
            currentHealth: String,
            damage: String
        ) = MonsterUI(
            /*id = id ?: "Id",
            attack = attack ?: "Attzck",
            defense = defense ?: "Defense",
            maxHealth = maxHealth ?: "MaxHealth",
            currentHealth = currentHealth ?: "CurrentHealth",
            damage = damage ?: "Damage"*/

            id = null.toString(),
            attack = Random.nextInt(1,30).toString(),
            defense = Random.nextInt(1,30).toString(),
            maxHealth = health,
            currentHealth = health,
            damage = Random.nextDouble(1.0,6.0).toString()
        )
    }
}