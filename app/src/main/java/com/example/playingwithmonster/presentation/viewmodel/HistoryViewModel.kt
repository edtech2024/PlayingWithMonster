package com.example.playingwithmonster.presentation.viewmodel

import androidx.lifecycle.*
import com.example.playingwithmonster.domain.iusecase.IUseCaseReadCreature
import com.example.playingwithmonster.presentation.mapper.ModelToUIMapperPlayer
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
    val useCaseReadCreature: IUseCaseReadCreature,
    val modelToUI: ModelToUIMapperPlayer
) : ViewModel() {

}
