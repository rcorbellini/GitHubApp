package com.corbellini.data.remote.entities

import com.corbellini.domain.models.Repository


data class RepositoryEntity(
    val name: String?,
    val description: String?,
    val stargazersCount: Int?,
    val forksCount: Int?,
    val owner: OwnerEntity
) {

    fun toModel() = Repository(
        name = name ?: "--",
        description = description?: "--",
        starCount = stargazersCount ?: 0,
        forkCount = forksCount?: 0,
        owner = owner.toModel()
    )
}
