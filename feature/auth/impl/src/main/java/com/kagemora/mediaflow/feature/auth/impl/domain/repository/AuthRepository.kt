package com.kagemora.mediaflow.feature.auth.impl.domain.repository

import com.kagemora.mediaflow.core.common.Result
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun accessTokenFlow(): Flow<String?>
    fun isLoggedIn(): Flow<Boolean>
    suspend fun login(username: String, password: String): Result<Unit>
    suspend fun refreshToken(): Result<Unit>
    suspend fun logout()
}