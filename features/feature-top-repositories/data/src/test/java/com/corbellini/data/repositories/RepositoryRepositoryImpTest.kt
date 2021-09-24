package com.corbellini.data.repositories

import com.corbellini.core.errors.RemoteApiExceptions
import com.corbellini.data.remote.entities.OwnerEntity
import com.corbellini.data.remote.entities.RepositoryEntity
import com.corbellini.data.remote.entities.RepositoryResponse
import com.corbellini.data.remote.services.RepositoryService
import com.corbellini.domain.usecases.LoadAllRepositoryParams
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RepositoryRepositoryImpTest {
    @Mock
    private lateinit var service: RepositoryService
    private lateinit var repository: RepositoryRepositoryImp

    @Before
    fun `RepositoryRepositoryImpTest before each`() {
        MockitoAnnotations.initMocks(this)
        repository = RepositoryRepositoryImp(service = service)
    }

    @ExperimentalCoroutinesApi
    @ExperimentalStdlibApi
    @Test
    fun `loadAllPaged must return a Repo`() = runBlockingTest {
        //arrange
        val params = LoadAllRepositoryParams(page = 1)
        val (page, language) = params
        val repo = RepositoryEntity(
            name = "Repo Rc",
            description = "Desc Repo Rc",
            stargazersCount = 2,
            forksCount = 3,
            owner = OwnerEntity(login = "rc", avatarUrl = "")
        )
        val query = "language:$language"
        val sort = "stars"
        val response = RepositoryResponse(1, true, listOf(repo))

        whenever(
            service.search(
                query,
                1,
                sort
            )
        ).thenReturn(response)

        //act
        val result = repository.loadAllPaged(page, language).first()

        //assert
        assertEquals(repo.toModel(), result.getOrNull()?.first())

    }

    @ExperimentalCoroutinesApi
    @ExperimentalStdlibApi
    @Test
    fun `loadAllPaged must throw a Remote API Exception when a Exception rise`() = runBlockingTest {
        //arrange
        val params = LoadAllRepositoryParams(page = 1)
        val (page, language) = params
        val query = "language:$language"
        val sort = "stars"

        whenever(
            service.search(
                query,
                1,
                sort
            )
        ).thenThrow(RuntimeException())

        //act
        val result = repository.loadAllPaged(page, language).first()

        //assert
        assertTrue(result.exceptionOrNull() is RemoteApiExceptions)

    }
}