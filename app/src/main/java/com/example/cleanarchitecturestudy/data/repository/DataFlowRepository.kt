package com.example.cleanarchitecturestudy.data.repository

import com.example.cleanarchitecturestudy.data.dto.DataDto
import kotlinx.coroutines.flow.Flow

interface DataFlowRepository {
    fun queryFlow(
        userId: String,
        page: Int,
        perPage: Int
    ): Flow<DataDto>
}
