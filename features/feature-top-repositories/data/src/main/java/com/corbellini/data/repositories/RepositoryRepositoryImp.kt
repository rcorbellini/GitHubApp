package com.corbellini.data.repositories

import com.corbellini.core.errors.RemoteApiExceptions
import com.corbellini.data.remote.services.RepositoryService
import com.corbellini.domain.models.Repository
import com.corbellini.domain.repositories.RepositoryRepository
import kotlinx.coroutines.flow.flow

class RepositoryRepositoryImp(
    private val service: RepositoryService
) : RepositoryRepository {


    override suspend fun loadAllPaged(page: Int, language: String) =
        flow<Result<List<Repository>>> {
            try {
                val response = service.search(
                    page = page,
                    query = "language:$language",
                    sort = "stars"
                )

                emit(Result.success(response.items.map { it.toModel() }))
            } catch (e: Exception) {
                emit(Result.failure(RemoteApiExceptions()))
            }
        }

}