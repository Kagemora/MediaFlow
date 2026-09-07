package com.kagemora.mediaflow.core.common

import kotlinx.coroutines.CancellationException
import retrofit2.HttpException
import java.io.IOException

sealed interface Result<out T> {

    data class Success<out T>(val result: T) : Result<T>
    data class Error(val error: AppError) : Result<Nothing>
}

suspend inline fun <R> resultOf(action: suspend () -> R): Result<R> {
    return try {
        Result.Success(action())
    } catch (e: CancellationException) {
        throw e
    } catch (e: IOException) {
        Result.Error(AppError.NoInternet)
    } catch (e: HttpException) {
        val appError = if (e.code() == 401) AppError.Unauthorized
        else AppError.Server(e.code(), e.message())
        Result.Error(appError)
    } catch (e: Exception) {
        Result.Error(AppError.Unknown(e))
    }
}