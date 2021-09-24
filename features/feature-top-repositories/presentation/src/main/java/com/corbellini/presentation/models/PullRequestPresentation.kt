package com.corbellini.presentation.models

import com.corbellini.domain.models.PullRequest
import java.text.SimpleDateFormat

data class PullRequestPresentation(
    val title: String,
    val createdAt: String,
    val body: String,
    val user: OwnerPresentation
){
    fun resolveCreatedAt() : String{
        val parser =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm")
        return  formatter.format(parser.parse(createdAt))
    }
}

fun PullRequest.toPresentation() = PullRequestPresentation(
    title = title,
    createdAt = createdAt,
    body = body,
    user = user.toPresentation()
)