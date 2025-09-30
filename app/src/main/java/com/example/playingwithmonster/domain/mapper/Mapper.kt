package com.example.playingwithmonster.domain.mapper

interface Mapper<in From, out To> {
    fun map(from: From): To

    fun mapAll(list: List<From>) = list.map { map(it) }
}