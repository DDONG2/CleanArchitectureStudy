package com.example.cleanarchitecturestudy.presenter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitecturestudy.domain.model.RepoInfo

@BindingAdapter("githubUserList")
fun RecyclerView.setGithubUserList(list: List<RepoInfo>?) {
    list?.let {
        (adapter as? UserAdapter)?.submitList(it)
    }
}