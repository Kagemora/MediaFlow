package com.kagemora.mediaflow.feature.auth.impl.di

import androidx.lifecycle.ViewModel
import com.kagemora.mediaflow.core.common.ViewModelKey
import com.kagemora.mediaflow.feature.auth.impl.presentation.loginscreen.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBindingModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel
}