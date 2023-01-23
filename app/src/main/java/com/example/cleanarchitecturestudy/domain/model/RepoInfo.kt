package com.example.cleanarchitecturestudy.domain.model

import androidx.annotation.Keep

@Keep
data class RepoInfo(
    val login: String,
    val reposUrl: String,
    val url: String
)
