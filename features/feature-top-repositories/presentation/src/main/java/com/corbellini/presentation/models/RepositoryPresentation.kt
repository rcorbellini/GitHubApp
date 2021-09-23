package com.corbellini.presentation.models

import com.corbellini.domain.models.Repository

data class RepositoryPresentation(
    val name: String,
    val description: String,
    val starCount: Int,
    val forkCount: Int,
    val owner: OwnerPresentation
)


fun Repository.toPresentation() = RepositoryPresentation(
    name = name,
    description = description,
    starCount = starCount,
    forkCount = forkCount,
    owner = owner.toPresentation()
)