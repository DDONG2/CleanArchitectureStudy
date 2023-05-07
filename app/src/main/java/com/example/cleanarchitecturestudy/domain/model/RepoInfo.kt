package com.example.cleanarchitecturestudy.domain.model

import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class RepoInfo(
    val id: Int,
    val login: String,
    val reposUrl: String,
    val url: String
) : Serializable
