package com.example.cleanarchitecturestudy.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitecturestudy.R
import com.example.cleanarchitecturestudy.databinding.HolderItemBinding
import com.example.cleanarchitecturestudy.domain.model.RepoInfo

class UserPagingAdapter(
    private val homeFragmentViewModel: HomeFragmentViewModel
) : PagingDataAdapter<RepoInfo, GithubUserPagingViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RepoInfo>() {
            override fun areItemsTheSame(
                oldItem: RepoInfo,
                newItem: RepoInfo,
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: RepoInfo,
                newItem: RepoInfo,
            ): Boolean = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserPagingViewHolder {
        return GithubUserPagingViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.holder_item,
                parent,
                false
            ), homeFragmentViewModel
        )
    }

    override fun onBindViewHolder(holder: GithubUserPagingViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

}

class GithubUserPagingViewHolder(
    private val binding: HolderItemBinding,
    viewModel: HomeFragmentViewModel
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.viewModel = viewModel
    }

    fun bind(item: RepoInfo) {
        binding.run {
            repoInfo = item
//            executePendingBindings()
        }
    }
}
