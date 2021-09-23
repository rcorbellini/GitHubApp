package com.corbellini.presentation.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corbellini.domain.models.PullRequest
import com.corbellini.domain.usecases.LoadAllPullRequestParams
import com.corbellini.domain.usecases.LoadAllPullRequestUseCase
import com.corbellini.presentation.models.PullRequestPresentation
import com.corbellini.presentation.models.toPresentation
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PullRequestListViewModel(
    private val loadAllPullRequestUseCase: LoadAllPullRequestUseCase
) : ViewModel() {

    private val _listState =
        MutableStateFlow(
            PullRequestListState(
                error = null,
                list = emptyList()
            )
        )

    val listState: StateFlow<PullRequestListState>
        get() = _listState

    private var jobList: Job? = null

    fun dispatchEvent(event: PullRequestListEvent) {
        when (event) {
            is PullRequestListEvent.LoadAll -> loadMore(event)

        }
    }


    private fun loadMore(event: PullRequestListEvent.LoadAll) {
        jobList?.cancel()
        jobList = viewModelScope.launch {
            loadAllPullRequestUseCase.execute(
                LoadAllPullRequestParams(
                    ownerName = event.ownerName,
                    repoName = event.repoName
                )
            )
                .onStart {
                    onLoading(true)
                }
                .onCompletion {
                    onLoading(false)
                }
                .collect { result ->
                    result.fold(onFailure = ::onFail, onSuccess = ::onSuccessLoadAll)
                }
        }
    }

    private fun onLoading(loading: Boolean) {
        _listState.value = _listState.value.copy(complete = !loading)
    }

    private fun onSuccessLoadAll(pullrequests: List<PullRequest>) {
        val currentList = pullrequests.map { it.toPresentation() }
        _listState.value = _listState.value.copy(list = currentList)
    }

    private fun onFail(error: Throwable) {
        _listState.value = _listState.value.copy(error = error.message)
    }

}