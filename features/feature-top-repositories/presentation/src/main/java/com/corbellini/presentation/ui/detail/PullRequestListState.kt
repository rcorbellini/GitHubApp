package com.corbellini.presentation.ui.detail

import com.corbellini.presentation.models.PullRequestPresentation

data class PullRequestListState(
    val error: String? = "",
    val complete: Boolean = false,
    val list: List<PullRequestPresentation> = emptyList(),
)