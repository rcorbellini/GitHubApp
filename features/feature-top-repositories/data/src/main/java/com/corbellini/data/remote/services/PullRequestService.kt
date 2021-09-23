package com.corbellini.data.remote.services

import com.corbellini.data.remote.entities.PullRequestEntity
import com.corbellini.data.remote.entities.RepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PullRequestService {

    @GET("repos/{ownerName}/{repoName}/pulls")
    suspend fun loadAll(
        @Path(value = "ownerName") ownerName: String,
        @Path(value = "repoName") repoName: String,
    ): List<PullRequestEntity>
}