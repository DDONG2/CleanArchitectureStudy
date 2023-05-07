package com.example.cleanarchitecturestudy.presenter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.cleanarchitecturestudy.BaseFragment
import com.example.cleanarchitecturestudy.R
import com.example.cleanarchitecturestudy.databinding.FragmentDetailBinding
import com.example.cleanarchitecturestudy.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    private val detailViewModel by viewModels<DetailViewModel>()

    override val layoutId: Int
        get() = R.layout.fragment_detail

    private val userDataAdapter: UserAdapter by lazy {
        UserAdapter(detailViewModel)
    }

//    private val userPagingDataAdapter: UserPagingAdapter by lazy {
//        UserPagingAdapter(detailViewModel)
//    }

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
                        detailViewModel.result.collectLatest { repoInfo ->
//                            userPagingDataAdapter.submitData(repoInfo)
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
//            recyclerView.adapter = userPagingDataAdapter
        }
    }

    private fun search() {
        Log.d("IDE", "Call Search API")
        detailViewModel.search("DONI")


    }

    override fun initArgument(bundle: Bundle) {
    }

    companion object {
        private val TAG = DetailFragment::class.java.simpleName

        @JvmStatic
        fun newInstance() =
            DetailFragment().apply {
            }
    }
}
