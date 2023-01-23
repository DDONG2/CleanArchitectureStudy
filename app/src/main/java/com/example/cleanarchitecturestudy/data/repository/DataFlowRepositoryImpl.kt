package com.example.cleanarchitecturestudy.data.repository

import com.example.cleanarchitecturestudy.data.service.DataService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DataFlowRepositoryImpl @Inject constructor(
    private val dataService: DataService,
) : DataFlowRepository {

    override fun queryFlow(
        userId: String,
        page: Int,
        perPage: Int
    ) = flow {
        emit(dataService.getSearchUsers(userId, page, perPage))
    }.flowOn(Dispatchers.IO)

    companion object {
        private val TAG = DataFlowRepositoryImpl::class.java.simpleName
    }
}
