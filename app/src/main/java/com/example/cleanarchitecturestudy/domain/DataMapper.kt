package com.example.cleanarchitecturestudy.domain

import com.example.cleanarchitecturestudy.data.dto.DataDto
import com.example.cleanarchitecturestudy.data.dto.Item
import com.example.cleanarchitecturestudy.domain.model.RepoInfo


fun DataDto.toInfoList() = items.map { it.toInfo() }

fun Item.toInfo() = RepoInfo(id, login, reposUrl, url)
