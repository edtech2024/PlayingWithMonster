package com.example.playingwithmonster.domain.model

class MainFactory {
    companion object {
        fun createFacory(factoryType: String): CreatureAbstractFactory? {
            return when(factoryType) {
                "a" -> PlayerModel.PlayerFactory
                "b" -> MonsterModel.MonsterFactory
                else -> null
            }
        }
    }
}