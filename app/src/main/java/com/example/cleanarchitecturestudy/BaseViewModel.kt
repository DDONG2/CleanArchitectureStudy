package com.example.cleanarchitecturestudy

import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    override fun onCleared() {
        super.onCleared()
    }
}
