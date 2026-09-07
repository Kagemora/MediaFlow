package com.kagemora.mediaflow.core.common

sealed interface AppError {
    data object NoInternet : AppError
    data object Unauthorized : AppError
    data class Server(val code: Int, val message: String?) : AppError
    data class Unknown(val cause: Throwable) : AppError
}