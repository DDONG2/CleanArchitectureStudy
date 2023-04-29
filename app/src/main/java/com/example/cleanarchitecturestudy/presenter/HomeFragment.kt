package com.example.cleanarchitecturestudy.presenter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import com.example.cleanarchitecturestudy.BaseFragment
import com.example.cleanarchitecturestudy.R
import com.example.cleanarchitecturestudy.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val homeFragmentViewModel by viewModels<HomeFragmentViewModel>()

    override val layoutId: Int
        get() = R.layout.fragment_home

    private val userDataAdapter: UserAdapter by lazy {
        UserAdapter(homeFragmentViewModel)
    }

    private val userPagingDataAdapter: UserPagingAdapter by lazy {
        UserPagingAdapter(homeFragmentViewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // onViewCreated
    override fun createObserveData() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                val handler = CoroutineExceptionHandler { _, e -> println("Caught $e") }
                supervisorScope {
                    launch(handler) {
                        homeFragmentViewModel.result.collectLatest { repoInfo ->
                            userPagingDataAdapter.submitData(repoInfo)
                        }
                    }
                }
            }
        }

        // 데이터 요청!
        search()
    }

    // onViewCreated
    override fun initView() {
        dataBinding.run {
            viewModel = homeFragmentViewModel
            recyclerView.adapter = userPagingDataAdapter
        }
    }

    private fun search() {
        Log.d("IDE", "Call Search API")
        homeFragmentViewModel.search("DONI")


    }

    override fun initArgument(bundle: Bundle) {
    }

    companion object {
        private val TAG = HomeFragment::class.java.simpleName

        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
            }
    }
}
