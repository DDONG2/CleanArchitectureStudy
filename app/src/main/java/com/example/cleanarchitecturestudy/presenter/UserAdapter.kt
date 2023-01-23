package com.example.cleanarchitecturestudy.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitecturestudy.R
import com.example.cleanarchitecturestudy.databinding.HolderItemBinding
import com.example.cleanarchitecturestudy.domain.model.RepoInfo


class UserAdapter(
    private val homeFragmentViewModel: HomeFragmentViewModel
) : ListAdapter<RepoInfo, GithubUserViewHolder>(diffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GithubUserViewHolder {
        return GithubUserViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.holder_item,
                parent,
                false
            ),
            homeFragmentViewModel
        )
    }

    override fun onBindViewHolder(holder: GithubUserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<RepoInfo>() {
            override fun areItemsTheSame(oldItem: RepoInfo, newItem: RepoInfo): Boolean =
                oldItem.login == newItem.login

            override fun areContentsTheSame(oldItem: RepoInfo, newItem: RepoInfo): Boolean =
                oldItem == newItem
        }
    }
}

class GithubUserViewHolder(
    private val binding: HolderItemBinding,
    viewModel: HomeFragmentViewModel
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.viewModel = viewModel
    }

    fun bind(item: RepoInfo) {
        binding.run {
            repoInfo = item
        }
    }
}
