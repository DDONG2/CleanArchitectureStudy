package com.example.cleanarchitecturestudy.presenter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.cleanarchitecturestudy.BaseFragment
import com.example.cleanarchitecturestudy.R
import com.example.cleanarchitecturestudy.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val homeFragmentViewModel by viewModels<HomeFragmentViewModel>()

    override val layoutId: Int
        get() = R.layout.fragment_home

    private val userDataAdapter: UserAdapter by lazy {
        UserAdapter(homeFragmentViewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // onViewCreated
    override fun createObserveData() {
        viewLifecycleOwner.lifecycleScope.launch {
            homeFragmentViewModel.error
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect {
                    Log.d("Error", it)
                }
        }

        // 데이터 요청!
        search()
    }

    // onViewCreated
    override fun initView() {
        dataBinding.run {
            viewModel = homeFragmentViewModel
            recyclerView.adapter = userDataAdapter
        }
    }

    private fun search() {
        Log.d("IDE", "Call Search API")
        homeFragmentViewModel.search("DONI", 1, 1)
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
