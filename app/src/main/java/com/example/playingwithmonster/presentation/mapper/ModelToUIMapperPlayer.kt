package com.example.playingwithmonster.presentation.mapper

import com.example.playingwithmonster.domain.mapper.Mapper
import com.example.playingwithmonster.domain.model.PlayerModel
import com.example.playingwithmonster.presentation.uistate.PlayerUI

class ModelToUIMapperPlayer : Mapper<PlayerModel, PlayerUI> {
    override fun map(from: PlayerModel): PlayerUI {
        return PlayerUI(
            id = from.id.toString(),
            attack = from.attack.toString(),
            defense = from.defense.toString(),
            maxHealth = from.maxHealth.toString(),
            currentHealth = from.currentHealth.toString(),
            damage = from.damage.toString(),
            numberOfTimes = from.numberOfTimes.toString()
        )
    }
}
