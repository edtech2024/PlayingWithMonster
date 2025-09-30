package com.example.playingwithmonster.presentation.mapper

import com.example.playingwithmonster.domain.mapper.Mapper
import com.example.playingwithmonster.domain.model.*
import com.example.playingwithmonster.presentation.uistate.PlayerUI

class UIToModelMapperPlayer : Mapper<PlayerUI, PlayerModel> {
    override fun map(from: PlayerUI): PlayerModel {
        return PlayerModel(
            id = from.id.toInt(),
            attack = from.attack.toInt(),
            defense = from.defense.toInt(),
            maxHealth = from.maxHealth.toDouble(),
            currentHealth = from.currentHealth.toDouble(),
            damage = from.damage.toDouble(),
            numberOfTimes = from.numberOfTimes.toInt()
        )
    }
}
