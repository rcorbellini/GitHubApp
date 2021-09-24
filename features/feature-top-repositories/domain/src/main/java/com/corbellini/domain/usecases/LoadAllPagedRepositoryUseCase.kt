package com.corbellini.domain.usecases

import com.corbellini.core.usecases.UseCase
import com.corbellini.core.usecases.UseCaseParam
import com.corbellini.domain.models.Repository
import com.corbellini.domain.repositories.RepositoryRepository
import kotlinx.coroutines.flow.Flow

interface LoadAllPagedRepositoryUseCase : UseCase<List<Repository>, LoadAllRepositoryParams> {
    override suspend fun execute(params: LoadAllRepositoryParams): Flow<Result<List<Repository>>>
}

class LoadAllPagedRepositoryUseCaseImp(private val repository: RepositoryRepository) : LoadAllPagedRepositoryUseCase {
    override suspend fun execute(params: LoadAllRepositoryParams) =
        repository.loadAllPaged(page = params.page, language = params.language)
}

data class LoadAllRepositoryParams(
    val page: Int = 1,
    val language: String = "Java"
) : UseCaseParam()