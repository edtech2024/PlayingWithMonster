package com.example.playingwithmonster.presentation.mapper

import com.example.playingwithmonster.domain.mapper.Mapper
import com.example.playingwithmonster.domain.model.*
import com.example.playingwithmonster.presentation.uistate.MonsterUI

class UIToModelMapperMonster : Mapper<MonsterUI, MonsterModel> {
    override fun map(from: MonsterUI): MonsterModel {
        return MonsterModel(
            id = from.id.toInt(),
            attack = from.attack.toInt(),
            defense = from.defense.toInt(),
            maxHealth = from.maxHealth.toDouble(),
            currentHealth = from.currentHealth.toDouble(),
            damage = from.damage.toDouble()
        )
    }
}
