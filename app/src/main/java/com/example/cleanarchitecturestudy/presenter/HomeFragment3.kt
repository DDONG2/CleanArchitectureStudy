package com.example.cleanarchitecturestudy.presenter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.cleanarchitecturestudy.BaseFragment
import com.example.cleanarchitecturestudy.R
import com.example.cleanarchitecturestudy.databinding.FragmentHome3Binding
import com.example.cleanarchitecturestudy.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
@AndroidEntryPoint
class HomeFragment3 : BaseFragment<FragmentHome3Binding>() {
    private val homeFragmentViewModel3 by viewModels<HomeFragmentViewModel3>()

    override val layoutId: Int
        get() = R.layout.fragment_home3


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
        private val TAG = HomeFragment3::class.java.simpleName

        @JvmStatic
        fun newInstance() =
            HomeFragment3().apply {
            }
    }
}
