package com.kagemora.mediaflow.core.common

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val defaultFactories: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<out ViewModel>>,
    private val assistedFactories: @JvmSuppressWildcards Map<Class<out ViewModel>, ViewModelAssistedFactory>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        val assistedFactory = assistedFactories[modelClass]
        if(assistedFactory!=null){
            val handle = extras.createSavedStateHandle()
            @Suppress("UNCHECKED_CAST")
            return assistedFactory.create(handle) as T
        }
        val plainCreator = defaultFactories[modelClass]
            ?: throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
        @Suppress("UNCHECKED_CAST")
        return plainCreator.get() as T
    }
}

/**
 * Фабрика для создания ViewModel с SavedStateHandle
 *
 * После реализации, необходимо добавить в dagger модуль
 *  @Binds
 *  @IntoMap
 *  @ViewModelKey(UserDetailViewModel::class)
 *  fun bindUserDetailFactory(impl: UserDetailViewModelFactory): ViewModelAssistedFactory
 *
 * Используется в обычной ViewModelFactory
 * private val assistedFactories: @JvmSuppressWildcards Map<Class<out ViewModel>, ViewModelAssistedFactory>
 */
fun interface ViewModelAssistedFactory {
    fun create(savedStateHandle: SavedStateHandle): ViewModel
}