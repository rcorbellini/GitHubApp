package com.corbellini.domain.usecases

import com.corbellini.core.usecases.UseCase
import com.corbellini.core.usecases.UseCaseParam
import com.corbellini.domain.models.Repository
import com.corbellini.domain.repositories.RepositoryRepository
import kotlinx.coroutines.flow.Flow

interface LoadAllPagedUseCase : UseCase<Repository, LoadAllParams> {
    override suspend fun execute(params: LoadAllParams): Flow<Result<Repository>>
}

class LoadAllPagedUseCaseImp(private val repository: RepositoryRepository) : LoadAllPagedUseCase {
    override suspend fun execute(params: LoadAllParams) =
        repository.loadAllPaged(page = params.page, language = params.language)
}

data class LoadAllParams(
    val page: Int = 1,
    val language: String = "Java"
) : UseCaseParam()