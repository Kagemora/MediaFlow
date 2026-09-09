package com.kagemora.mediaflow

import android.app.Application
import com.kagemora.mediaflow.di.AppComponent
import com.kagemora.mediaflow.di.DaggerAppComponent



class MediaFlowApp : Application() {

    val daggerComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this, BuildConfig.DEBUG)
    }

    override fun onCreate() {
        super.onCreate()
        daggerComponent.inject(this)
    }
}