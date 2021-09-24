package com.corbellini.presentation.ui.detail

sealed class PullRequestListEvent {
    data class LoadAll(val ownerName: String, val repoName: String) : PullRequestListEvent()
}