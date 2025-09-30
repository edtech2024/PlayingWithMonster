package com.example.playingwithmonster.data.entity

import androidx.room.*
import androidx.room.Entity

@Entity(tableName = "players")
class PlayerEntity public constructor(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int?,
    @ColumnInfo(name = "attack")
    val attack: Int,
    @ColumnInfo(name = "defense")
    val defense: Int,
    @ColumnInfo(name = "maxHealth")
    val maxHealth: Double,
    @ColumnInfo(name = "currentHealth")
    var currentHealth: Double,
    @ColumnInfo(name = "damage")
    val damage: Double,
    @ColumnInfo(name = "numberOfTimes")
    var numberOfTimes: Int
    ) {
    companion object {
        operator fun invoke(
            id: Int?,
            attack: Int, defense: Int,
            maxHealth: Double, currentHealth: Double,
            damage: Double, numberOfTimes: Int
        ) = PlayerEntity(
            id = id,
            attack = attack,
            defense = defense,
            maxHealth = maxHealth,
            currentHealth = currentHealth,
            damage = damage,
            numberOfTimes = numberOfTimes
        )
    }

}
