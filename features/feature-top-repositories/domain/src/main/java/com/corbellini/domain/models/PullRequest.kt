package com.corbellini.domain.models

data class PullRequest(
    val title: String,
    val createdAt: String,
    val body: String,
    val user: Owner
)
