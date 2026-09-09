package com.kagemora.mediaflow.feature.auth.impl.presentation.loginscreen

import androidx.lifecycle.viewModelScope
import com.kagemora.mediaflow.core.common.AppError
import com.kagemora.mediaflow.core.common.BaseViewModel
import com.kagemora.mediaflow.core.common.Result
import com.kagemora.mediaflow.feature.auth.impl.domain.usecase.LoginUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel<LoginState, LoginEffect, LoginIntent>() {

    private var loginJob: Job? = null
    override fun createInitialState(): LoginState = LoginState()
    override fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.PasswordChanged -> passChanged(intent.value)
            LoginIntent.SubmitClicked -> submit()
            is LoginIntent.UsernameChanged -> nameChanged(intent.value)
        }
    }

    private fun passChanged(name: String) {
        setState {
            copy(password = name)
        }
    }

    private fun nameChanged(name: String) {
        setState { copy(username = name) }
    }

    private fun submit() {
        val current = state.value
        if (current.username.isBlank() || current.password.isBlank()) {
            setState { copy(errorMessage = "Введите логин и пароль") }
            return
        }
        loginJob?.cancel()
        setState { copy(isLoading = true, errorMessage = null) }
        loginJob = viewModelScope.launch {
            when (val result = loginUseCase(state.value.username, state.value.password)) {
                is Result.Error -> {
                    val message = when (val error = result.error) {
                        is AppError.NoInternet -> "Проверьте подключение к интернету"
                        is AppError.Unauthorized -> "Неверный логин или пароль"
                        is AppError.Server -> "Ошибка сервера: ${error.code}"
                        is AppError.Unknown -> "Что-то пошло не так"
                    }
                    setState { copy(isLoading = false, errorMessage = message) }
                }

                is Result.Success<*> -> {
                    setState { copy(isLoading = false) }
                    sendEffect(LoginEffect.NavigateToFeed)
                }
            }
        }

    }
}

data class LoginState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

sealed interface LoginIntent {
    data class UsernameChanged(val value: String) : LoginIntent
    data class PasswordChanged(val value: String) : LoginIntent
    data object SubmitClicked : LoginIntent
}

sealed interface LoginEffect {
    data object NavigateToFeed : LoginEffect
}