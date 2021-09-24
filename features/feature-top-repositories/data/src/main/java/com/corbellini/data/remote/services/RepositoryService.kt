package com.corbellini.data.remote.services

import com.corbellini.data.remote.entities.RepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryService {

    @GET("search/repositories")
    suspend fun search(
        @Query(value = "q") query: String,
        @Query(value = "page") page: Int,
        @Query(value = "sort") sort: String
    ): RepositoryResponse
}