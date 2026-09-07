package com.kagemora.mediaflow.feature.auth.impl.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(val username: String, val password: String)