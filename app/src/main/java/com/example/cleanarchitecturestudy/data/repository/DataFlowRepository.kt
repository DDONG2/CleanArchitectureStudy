package com.example.cleanarchitecturestudy.data.repository

import androidx.paging.PagingData
import com.example.cleanarchitecturestudy.data.dto.Item
import kotlinx.coroutines.flow.Flow

interface DataFlowRepository {
    fun queryFlow(
        userId: String,
    ): Flow<PagingData<Item>>
}
