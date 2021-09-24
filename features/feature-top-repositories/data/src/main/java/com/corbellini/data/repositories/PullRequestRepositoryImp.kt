package com.corbellini.data.repositories

import com.corbellini.core.errors.RemoteApiExceptions
import com.corbellini.data.remote.services.PullRequestService
import com.corbellini.domain.models.PullRequest
import com.corbellini.domain.repositories.PullRequestRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PullRequestRepositoryImp(
    private val service: PullRequestService
) : PullRequestRepository {


    override suspend fun loadAll(
        ownerName: String,
        repoName: String
    ) =
        flow<Result<List<PullRequest>>> {
            try {
                val response = service.loadAll(
                    ownerName = ownerName,
                    repoName = repoName,
                )

                emit(Result.success(response.map { it.toModel() }))
            } catch (e: Exception) {
                e.printStackTrace();
                emit(Result.failure(RemoteApiExceptions(e.message ?: "empty")))
            }
        }
}