package com.corbellini.presentation.ui.list

sealed class RepositoryListEvent {
    object LoadMore : RepositoryListEvent()
}