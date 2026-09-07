package com.kagemora.mediaflow.core.datastore

import kotlinx.coroutines.flow.Flow

interface TokenStorage {

    fun accessTokenFlow(): Flow<String?>
    suspend fun getAccessToken(): String?
    suspend fun getRefreshToken(): String?
    suspend fun saveTokens(accessToken: String, refreshToken: String)
    suspend fun clearTokens()
}