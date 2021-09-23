package com.corbellini.presentation.models

import com.corbellini.domain.models.Owner

data class OwnerPresentation(
    val login: String,
    val avatarUrl: String,
)

fun Owner.toPresentation() = OwnerPresentation(
    login = login,
    avatarUrl = avatarUrl
)