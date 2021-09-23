package com.corbellini.domain.repositories

import com.corbellini.domain.models.PullRequest
import com.corbellini.domain.models.Repository
import kotlinx.coroutines.flow.Flow

interface PullRequestRepository {

    suspend fun loadAll(
        ownerName: String,
        repoName: String
    ): Flow<Result<List<PullRequest>>>
}