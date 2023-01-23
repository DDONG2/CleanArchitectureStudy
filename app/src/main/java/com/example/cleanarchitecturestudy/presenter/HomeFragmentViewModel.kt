package com.example.cleanarchitecturestudy.presenter

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecturestudy.BaseViewModel
import com.example.cleanarchitecturestudy.domain.DataFlowUseCase
import com.example.cleanarchitecturestudy.domain.model.RepoInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val dataFlowUserCase: DataFlowUseCase,
) : BaseViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _result = MutableStateFlow<List<RepoInfo>>(emptyList())
    val result = _result.asStateFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()

    fun search(userId: String, page: Int, count: Int) {
        viewModelScope.launch {
            dataFlowUserCase(
                DataFlowUseCase.Params(
                    userId,
                    page,
                    count
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
        private val TAG = HomeFragmentViewModel::class.java.simpleName
    }
}
