package com.example.cleanarchitecturestudy.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.cleanarchitecturestudy.data.dto.Item
import com.example.cleanarchitecturestudy.data.source.datasource.DataSource
import com.example.cleanarchitecturestudy.data.source.pagingsource.DataPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataFlowRepositoryImpl @Inject constructor(
    private val dataSource: DataSource,
) : DataFlowRepository {

    override fun queryFlow(
        userId: String,
    ): Flow<PagingData<Item>> =
        Pager(PagingConfig(PAGE_SIZE)) {
            DataPagingSource(userId, dataSource)
        }.flow

    companion object {
        private val TAG = DataFlowRepositoryImpl::class.java.simpleName

        private const val PAGE_SIZE = 10

    }
}
