package com.kagemora.mediaflow.feature.auth.impl.data.repository

import com.kagemora.mediaflow.feature.auth.api.AuthApi
import com.kagemora.mediaflow.feature.auth.impl.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthApiImpl @Inject constructor(
    private val repository: AuthRepository
) : AuthApi {
    override fun tokenFlow(): Flow<String?> = repository.accessTokenFlow()

    override fun isLoggedIn(): Flow<Boolean> =
        repository.isLoggedIn()

    override suspend fun logout() = repository.logout()
}