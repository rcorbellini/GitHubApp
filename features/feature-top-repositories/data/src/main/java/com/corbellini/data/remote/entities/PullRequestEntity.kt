package com.corbellini.data.remote.entities

import com.corbellini.domain.models.PullRequest

data class PullRequestEntity(
    val title: String,
    val createdAt: String,
    val body: String?,
    val user: OwnerEntity
) {

    fun toModel() = PullRequest(
        title = title,
        createdAt = createdAt,
        body = body ?: "",
        user = user.toModel(),

        )
}

