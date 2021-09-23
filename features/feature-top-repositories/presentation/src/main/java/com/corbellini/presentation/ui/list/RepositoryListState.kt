package com.corbellini.presentation.ui.list

import com.corbellini.presentation.models.RepositoryPresentation

data class RepositoryListState(
    val complete: Boolean = false,
    val error: String? = "",
    val currentPage: Int = 0,
    val list: List<RepositoryPresentation> = emptyList(),
)