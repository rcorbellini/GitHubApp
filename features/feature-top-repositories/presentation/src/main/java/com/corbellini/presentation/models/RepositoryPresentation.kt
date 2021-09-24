package com.corbellini.presentation.models

import com.corbellini.domain.models.Repository

data class RepositoryPresentation(
    val name: String,
    val description: String,
    val starCount: Int,
    val forkCount: Int,
    val owner: OwnerPresentation
){
    fun resolvedForkCount() = "${forkCount / 1000}k"

    fun resolvedStarCount() = "${starCount / 1000}k"

    fun resolvedName() = "${owner.login}/${name}"
}


fun Repository.toPresentation() = RepositoryPresentation(
    name = name,
    description = description,
    starCount = starCount,
    forkCount = forkCount,
    owner = owner.toPresentation()
)