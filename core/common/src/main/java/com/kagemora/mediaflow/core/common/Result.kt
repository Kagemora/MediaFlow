package com.kagemora.mediaflow.core.common

sealed interface Result<out T> {

    data class Success<out T>(val result: T) : Result<T>
    data class Error(val error: Throwable) : Result<Nothing>
}