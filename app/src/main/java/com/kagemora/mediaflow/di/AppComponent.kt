package com.kagemora.mediaflow.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.kagemora.mediaflow.MediaFlowApp
import com.kagemora.mediaflow.core.common.IsDebug
import com.kagemora.mediaflow.core.common.ViewModelFactoryModule
import com.kagemora.mediaflow.core.datastore.DataStoreModule
import com.kagemora.mediaflow.core.network.NetworkModule
import com.kagemora.mediaflow.feature.auth.impl.di.AuthModule
import com.kagemora.mediaflow.feature.auth.impl.di.ViewModelBindingModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelFactoryModule::class,
        NetworkModule::class,
        DataStoreModule::class,
        AuthModule::class,
        ViewModelBindingModule::class
    ]
)
interface AppComponent {
    fun viewModelFactory(): ViewModelProvider.Factory
    fun inject(mediaFlowApp: MediaFlowApp)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application,
            @BindsInstance @IsDebug isDebug: Boolean
        ): AppComponent
    }
}