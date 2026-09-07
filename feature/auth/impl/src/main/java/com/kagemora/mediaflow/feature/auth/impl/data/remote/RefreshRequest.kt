package com.kagemora.mediaflow.feature.auth.impl.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class RefreshRequest(val refreshToken: String)
