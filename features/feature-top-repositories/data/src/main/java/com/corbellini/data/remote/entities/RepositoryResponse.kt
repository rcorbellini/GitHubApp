package com.corbellini.data.remote.entities

data class RepositoryResponse(
    val totalCount: Int,
    val incompleteResults: Boolean,
    val items: List<RepositoryEntity>
)
