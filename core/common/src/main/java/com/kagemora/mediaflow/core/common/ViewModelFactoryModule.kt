package com.kagemora.mediaflow.core.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.Multibinds
import javax.inject.Provider

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Multibinds
    abstract fun bindViewModelCreators(): Map<Class<out ViewModel>, @JvmSuppressWildcards ViewModel>

    @Multibinds
    abstract fun bindViewModelAssistedFactories(): Map<Class<out ViewModel>, ViewModelAssistedFactory>
}