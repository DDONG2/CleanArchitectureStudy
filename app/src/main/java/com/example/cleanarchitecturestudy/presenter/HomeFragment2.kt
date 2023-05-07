package com.example.cleanarchitecturestudy.presenter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.cleanarchitecturestudy.BaseFragment
import com.example.cleanarchitecturestudy.R
import com.example.cleanarchitecturestudy.databinding.FragmentHome2Binding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
@AndroidEntryPoint
class HomeFragment2 : BaseFragment<FragmentHome2Binding>() {
    private val homeFragmentViewModel2 by viewModels<HomeFragmentViewModel2>()

    override val layoutId: Int
        get() = R.layout.fragment_home2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // onViewCreated
    override fun createObserveData() {

    }

    // onViewCreated
    override fun initView() {
    }

    private fun search() {

    }

    override fun initArgument(bundle: Bundle) {
    }

    companion object {
        private val TAG = HomeFragment2::class.java.simpleName

        @JvmStatic
        fun newInstance() =
            HomeFragment2().apply {
            }
    }
}
