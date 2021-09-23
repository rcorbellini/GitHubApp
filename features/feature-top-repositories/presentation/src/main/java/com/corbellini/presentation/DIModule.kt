package com.corbellini.presentation

import com.corbellini.presentation.ui.detail.PullRequestListViewModel
import com.corbellini.presentation.ui.list.RepositoryListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val topRepositoryPresentation = module {
    viewModel {
        RepositoryListViewModel(
            loadAllPagedRepositoryUseCase = get(),
        )
    }
    viewModel {
        PullRequestListViewModel(
            loadAllPullRequestUseCase = get(),
        )
    }
}
