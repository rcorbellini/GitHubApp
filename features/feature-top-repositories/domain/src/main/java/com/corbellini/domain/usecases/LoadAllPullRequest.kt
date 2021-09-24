package com.corbellini.domain.usecases

import com.corbellini.core.usecases.UseCase
import com.corbellini.core.usecases.UseCaseParam
import com.corbellini.domain.models.PullRequest
import com.corbellini.domain.repositories.PullRequestRepository
import kotlinx.coroutines.flow.Flow

interface LoadAllPullRequestUseCase : UseCase<List<PullRequest>, LoadAllPullRequestParams> {
    override suspend fun execute(params: LoadAllPullRequestParams): Flow<Result<List<PullRequest>>>
}

class LoadAllPullRequestUseCaseImp(private val repository: PullRequestRepository) :
    LoadAllPullRequestUseCase {
    override suspend fun execute(params: LoadAllPullRequestParams) =
        repository.loadAll(ownerName = params.ownerName, repoName = params.repoName)
}

data class LoadAllPullRequestParams(
    val ownerName: String = "",
    val repoName: String = ""
) : UseCaseParam()