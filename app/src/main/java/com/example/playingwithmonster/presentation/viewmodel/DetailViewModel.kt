package com.example.playingwithmonster.presentation.viewmodel

import android.os.Bundle
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.playingwithmonster.presentation.fragment.DetailFragment
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Inject

class DetailViewModel constructor(val bundle: Bundle?, val context: DetailFragment.OnItemCloseListener?) : ViewModel() {

}