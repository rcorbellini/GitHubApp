package com.corbellini.domain.repositories

import com.corbellini.domain.models.Repository
import kotlinx.coroutines.flow.Flow

interface RepositoryRepository {
    suspend fun loadAllPaged(page: Int, language: String): Flow<Result<Repository>>
}