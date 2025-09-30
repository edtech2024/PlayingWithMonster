package com.example.playingwithmonster.presentation.di

import android.content.Context
import com.example.playingwithmonster.data.di.DataModule
import com.example.playingwithmonster.domain.di.DomainModule
import com.example.playingwithmonster.presentation.fragment.DetailFragment
import com.example.playingwithmonster.presentation.fragment.GameFragment
import com.example.playingwithmonster.presentation.fragment.HistoryFragment
import com.example.playingwithmonster.presentation.fragment.MainFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Component(modules = [DataModule::class, DomainModule::class, PresentationModule::class ] )
@Singleton
interface ApplicationComponent {

    @Component.Factory
    interface SingletonComponentFactory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun inject(fragment: MainFragment)
    fun inject(fragment: DetailFragment)
    fun inject(fragment: HistoryFragment)
    fun inject(fragment: GameFragment)

}
