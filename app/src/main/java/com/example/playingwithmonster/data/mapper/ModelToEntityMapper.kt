package com.example.playingwithmonster.data.mapper

import com.example.playingwithmonster.data.entity.PlayerEntity
import com.example.playingwithmonster.domain.mapper.Mapper
import com.example.playingwithmonster.domain.model.PlayerModel

class ModelToEntityMapper : Mapper<PlayerModel, PlayerEntity> {
    override fun map(from: PlayerModel): PlayerEntity {
        return PlayerEntity(
            id = from.id,
            attack = from.attack,
            defense = from.defense,
            maxHealth = from.maxHealth,
            currentHealth = from.currentHealth,
            damage = from.damage,
            numberOfTimes = from.numberOfTimes
        )
    }
}
