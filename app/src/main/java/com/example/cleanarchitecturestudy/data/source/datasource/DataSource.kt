package com.example.cleanarchitecturestudy.data.source.datasource

import com.example.cleanarchitecturestudy.data.dto.DataDto
import com.example.cleanarchitecturestudy.data.service.DataService
import javax.inject.Inject

class DataSource @Inject constructor(
    private val dataService: DataService
) {
    suspend fun getSearchUsers(
        userId: String,
        page: Int,
        perPage: Int
    ): DataDto {
        return dataService.getSearchUsers(userId, page, perPage)
    }

}