package com.corbellini.presentation.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corbellini.domain.models.Repository
import com.corbellini.domain.usecases.LoadAllPagedUseCase
import com.corbellini.domain.usecases.LoadAllParams
import com.corbellini.presentation.models.RepositoryPresentation
import com.corbellini.presentation.models.toPresentation
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RepositoryListViewModel(
    private val loadAllPagedUseCase: LoadAllPagedUseCase,
) : ViewModel() {

    private val _listState =
        MutableStateFlow(
            RepositoryListState(
                complete = false,
                error = null,
                list = emptyList()
            )
        )

    val listState: StateFlow<RepositoryListState>
        get() = _listState

    private var jobList: Job? = null

    fun dispatchEvent(repoListEvent: RepositoryListEvent) {
        when (repoListEvent) {
            is RepositoryListEvent.LoadMore -> loadMore()

        }
    }


    private fun loadMore() {
        jobList?.cancel()
        jobList = viewModelScope.launch {
            val nextPage = _listState.value.currentPage + 1;
            loadAllPagedUseCase.execute(LoadAllParams(page = nextPage))
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

    private fun onSuccessLoadAll(repositories: List<Repository>) {
        val oldList: List<RepositoryPresentation> = _listState.value.list ?: emptyList()
        val newList: List<RepositoryPresentation> = repositories.map { it.toPresentation() }
        val currentList = oldList + newList
        val currentPage = _listState.value.currentPage + 1;
        _listState.value = _listState.value.copy(list = currentList, currentPage = currentPage)
    }

    private fun onFail(error: Throwable) {
        _listState.value = _listState.value.copy(error = error.message)
    }

}