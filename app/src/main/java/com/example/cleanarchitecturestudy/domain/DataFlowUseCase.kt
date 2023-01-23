package com.example.cleanarchitecturestudy.domain

import com.example.cleanarchitecturestudy.data.repository.DataFlowRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataFlowUseCase @Inject constructor(
    private val dataFlowRepository: DataFlowRepository,
) {
    data class Params(val userId: String, val page: Int, val perPage: Int)

    operator fun invoke(params: Params) =
        dataFlowRepository.queryFlow(params.userId, params.page, params.perPage)
            .map { it.toInfoList() }
            .flowOn(Dispatchers.Default)

    companion object {
        private val TAG = DataFlowUseCase::class.java.simpleName
    }
}
