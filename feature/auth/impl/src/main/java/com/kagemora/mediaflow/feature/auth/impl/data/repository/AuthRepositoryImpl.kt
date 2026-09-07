package com.kagemora.mediaflow.feature.auth.impl.data.repository

import com.kagemora.mediaflow.core.common.Result
import com.kagemora.mediaflow.core.common.resultOf
import com.kagemora.mediaflow.core.datastore.TokenStorage
import com.kagemora.mediaflow.feature.auth.impl.data.remote.AuthService
import com.kagemora.mediaflow.feature.auth.impl.data.remote.LoginRequest
import com.kagemora.mediaflow.feature.auth.impl.data.remote.RefreshRequest
import com.kagemora.mediaflow.feature.auth.impl.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val tokenStorage: TokenStorage
) : AuthRepository {
    override fun accessTokenFlow(): Flow<String?> = tokenStorage.accessTokenFlow()

    override fun isLoggedIn(): Flow<Boolean> = accessTokenFlow().map { it != null }

    override suspend fun login(
        username: String,
        password: String
    ): Result<Unit> = resultOf {
        val response = authService.login(LoginRequest(username, password))
        tokenStorage.saveTokens(response.accessToken, response.refreshToken)
    }


    override suspend fun refreshToken(): Result<Unit> = resultOf {
        val savedRefreshToken = tokenStorage.getRefreshToken()
            ?: throw IllegalStateException("No refresh token, need re-login")
        val response = authService.refresh(RefreshRequest(savedRefreshToken))
        tokenStorage.saveTokens(response.accessToken, response.refreshToken)
    }

    override suspend fun logout() {
        tokenStorage.clearTokens()
    }
}