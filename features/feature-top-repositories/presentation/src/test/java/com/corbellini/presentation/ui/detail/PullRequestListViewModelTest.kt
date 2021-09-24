package com.corbellini.presentation.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.corbellini.core.errors.RemoteApiExceptions
import com.corbellini.domain.models.Owner
import com.corbellini.domain.models.PullRequest
import com.corbellini.domain.usecases.LoadAllPullRequestParams
import com.corbellini.domain.usecases.LoadAllPullRequestUseCase
import com.corbellini.presentation.models.toPresentation
import com.corbellini.presentation.util.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import kotlin.test.assertTrue

class PullRequestListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Mock
    private lateinit var useCase: LoadAllPullRequestUseCase
    private lateinit var viewModel: PullRequestListViewModel

    @Before
    fun `GetById before each`() {
        MockitoAnnotations.initMocks(this)
        viewModel = PullRequestListViewModel(useCase)
    }

    @ExperimentalCoroutinesApi
    @ExperimentalStdlibApi
    @Test
    fun `dispatchEvent LoadAll when failure, must result a error state`() {
        runBlocking {
            //arrange
            val params = LoadAllPullRequestParams(ownerName = "rafael", repoName = "repo rc")

            whenever(
                useCase.execute(params = params)
            ).thenReturn(flow<Result<List<PullRequest>>> {
                emit(
                    Result.failure(
                        RemoteApiExceptions("fail")
                    )
                )
            })

            //act
            viewModel.dispatchEvent(
                PullRequestListEvent.LoadAll(
                    ownerName = params.ownerName,
                    repoName = params.repoName
                )
            )

            //assert
            val result = viewModel.listState.first()
            assertTrue(result.error?.isNotEmpty() ?: false)
        }
    }

    @ExperimentalCoroutinesApi
    @ExperimentalStdlibApi
    @Test
    fun `dispatchEvent LoadAll when success, must result a success state`() {
        runBlocking{
            //arrange
            val params = LoadAllPullRequestParams(ownerName = "rafael", repoName = "repo rc")
            val pullRequest = PullRequest(
                title = "title",
                createdAt = "222",
                body = "body",
                user = Owner(login = "rc", avatarUrl = "avatar")
            )

            whenever(
                useCase.execute(params = params)
            ).thenReturn(flow<Result<List<PullRequest>>> {
                emit(
                    Result.success(
                        listOf(pullRequest)
                    )
                )
            })

            //act
            viewModel.dispatchEvent(
                PullRequestListEvent.LoadAll(
                    ownerName = params.ownerName,
                    repoName = params.repoName
                )
            )

            val result = viewModel.listState.first()
            assertTrue(result.list.firstOrNull()?.equals(pullRequest.toPresentation()) ?: false)
        }
    }
}