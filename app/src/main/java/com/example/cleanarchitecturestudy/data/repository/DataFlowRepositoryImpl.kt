package com.example.cleanarchitecturestudy.data.repository

import com.example.cleanarchitecturestudy.data.source.datasource.DataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DataFlowRepositoryImpl @Inject constructor(
    private val dataSource: DataSource,
) : DataFlowRepository {

    override fun queryFlow(
        userId: String,
        page: Int,
        perPage: Int
    ) = flow {
        runCatching {
            dataSource.getSearchUsers(userId, page, perPage)
        }.onSuccess {
            emit(it)
        }.onFailure { exception ->
            throw exception
        }
    }.flowOn(Dispatchers.IO)

    companion object {
        private val TAG = DataFlowRepositoryImpl::class.java.simpleName
    }
}
