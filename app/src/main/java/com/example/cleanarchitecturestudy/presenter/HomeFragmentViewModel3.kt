package com.example.cleanarchitecturestudy.presenter

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.cleanarchitecturestudy.BaseViewModel
import com.example.cleanarchitecturestudy.domain.DataFlowUseCase
import com.example.cleanarchitecturestudy.domain.model.RepoInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel3 @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val dataFlowUserCase: DataFlowUseCase,
) : BaseViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _result = MutableStateFlow<PagingData<RepoInfo>>(PagingData.empty())
    val result = _result.asStateFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()

    fun search(userId: String) {
        viewModelScope.launch {
            dataFlowUserCase(
                DataFlowUseCase.Params(
                    userId
                )
            ).catch { e ->
                Log.e(TAG, "$e")

                _error.emit(e.message ?: "")
//                toastProvider.showToast("$e")
            }.collect {
                _result.emit(it)
                _isLoading.value = false
            }
        }
    }

    companion object {
        private val TAG = HomeFragmentViewModel3::class.java.simpleName
    }
}
