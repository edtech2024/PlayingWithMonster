package com.example.playingwithmonster.presentation.mapper

import com.example.playingwithmonster.domain.mapper.Mapper
import com.example.playingwithmonster.domain.model.MonsterModel
import com.example.playingwithmonster.presentation.uistate.MonsterUI

class ModelToUIMapperMonster : Mapper<MonsterModel, MonsterUI> {
    override fun map(from:MonsterModel): MonsterUI {
        return MonsterUI(
            id = from.id.toString(),
            attack = from.attack.toString(),
            defense = from.defense.toString(),
            maxHealth = from.maxHealth.toString(),
            currentHealth = from.currentHealth.toString(),
            damage = from.damage.toString()
        )
    }
}
