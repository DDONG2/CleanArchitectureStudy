package com.example.cleanarchitecturestudy.presenter

import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitecturestudy.BaseViewModel
import com.example.cleanarchitecturestudy.databinding.HolderItemBinding
import com.example.cleanarchitecturestudy.domain.model.RepoInfo

class GithubUserPagingViewHolder(
    private val binding: HolderItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    init {
    }

    fun bind(item: RepoInfo) {
        binding.run {
            repoInfo = item
//            executePendingBindings()
        }
    }
}