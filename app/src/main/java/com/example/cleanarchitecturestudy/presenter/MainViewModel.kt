package com.example.cleanarchitecturestudy.presenter


import com.example.cleanarchitecturestudy.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : BaseViewModel() {


    companion object {
        private val TAG = MainViewModel::class.java.simpleName
    }
}
