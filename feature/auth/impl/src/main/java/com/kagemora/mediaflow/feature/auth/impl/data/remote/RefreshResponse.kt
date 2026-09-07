package com.kagemora.mediaflow.feature.auth.impl.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class RefreshResponse(
    val accessToken: String,
    val refreshToken: String
)