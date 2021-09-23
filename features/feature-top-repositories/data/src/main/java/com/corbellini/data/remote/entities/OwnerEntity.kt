package com.corbellini.data.remote.entities

import com.corbellini.domain.models.Owner

data class OwnerEntity(
    val login: String,
    val avatarUrl: String?,
) {

    fun toModel() = Owner(
        login = login,
        avatarUrl = avatarUrl ?: "https://www.ecp.org.br/wp-content/uploads/2017/12/default-avatar-1.png",
    )
}