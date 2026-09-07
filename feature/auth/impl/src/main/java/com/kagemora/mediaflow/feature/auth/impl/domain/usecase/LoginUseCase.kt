package com.kagemora.mediaflow.feature.auth.impl.domain.usecase

import com.kagemora.mediaflow.core.common.Result
import com.kagemora.mediaflow.feature.auth.impl.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String): Result<Unit> =
        repository.login(username, password)
}