package com.corbellini.domain.usecases


import com.corbellini.core.errors.RemoteApiExceptions
import com.corbellini.domain.models.Owner
import com.corbellini.domain.models.Repository
import com.corbellini.domain.repositories.RepositoryRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetByIdUseCaseTest {

    @Mock
    private lateinit var repository: RepositoryRepository
    private lateinit var useCase: LoadAllPagedRepositoryUseCaseImp

    @Before
    fun `GetById before each`() {
        MockitoAnnotations.initMocks(this)
        useCase = LoadAllPagedRepositoryUseCaseImp(repository)
    }

    @ExperimentalCoroutinesApi
    @ExperimentalStdlibApi
    @Test
    fun `Execute LoadAllPagedRepositoryUseCaseImp return a success list of repo`() {
        runBlockingTest {
            //arrange
            val params = LoadAllRepositoryParams(page = 1)
            val (page, language) = params
            val repo = Repository(
                name = "Repo Rc",
                description = "Desc Repo Rc",
                starCount = 2,
                forkCount = 3,
                owner = Owner(login = "rc", avatarUrl = "")
            )
            whenever(
                repository.loadAllPaged(
                    page,
                    language
                )
            ).thenReturn(flow { emit(Result.success(listOf(repo))) })

            //act
            val result = useCase.execute(params = params).first()

            //assert
            assertEquals(Result.success(listOf(repo)), result)

        }
    }


    @ExperimentalCoroutinesApi
    @ExperimentalStdlibApi
    @Test
    fun `LoadAllPagedRepositoryUseCaseImp must return a failure, when exception`() {
        runBlockingTest {
            //arrange
            val params = LoadAllRepositoryParams(page = 1)
            val (page, language) = params
            whenever(
                repository.loadAllPaged(
                    page,
                    language
                )
            ).thenReturn(flow<Result<List<Repository>>> {
                emit(
                    Result.failure(
                        RemoteApiExceptions("fail")
                    )
                )
            })

            //act
            val result = useCase.execute(params = params).first()

            //assert
            assertTrue(result.isFailure && result.exceptionOrNull() is RemoteApiExceptions)
        }
    }
}