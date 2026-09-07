package com.kagemora.mediaflow.feature.auth.api

import kotlinx.coroutines.flow.Flow

interface AuthApi {

    fun tokenFlow(): Flow<String?>
    fun isLoggedIn():Flow<Boolean>
    suspend fun logout()
}