package com.corbellini.domain.models

data class Repository(
    val name: String,
    val description: String,
    val starCount: Int,
    val forkCount: Int,
    val owner: Owner
)
