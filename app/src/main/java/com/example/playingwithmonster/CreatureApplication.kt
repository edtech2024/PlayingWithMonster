package com.example.playingwithmonster

import android.app.Application
import com.example.playingwithmonster.presentation.di.ApplicationComponent
import com.example.playingwithmonster.presentation.di.DaggerApplicationComponent


class CreatureApplication : Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent.factory().create(applicationContext)
    }

}

