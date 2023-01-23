package com.example.cleanarchitecturestudy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment

abstract class BaseFragment<LAYOUT : ViewDataBinding> : Fragment() {

    lateinit var dataBinding: LAYOUT

    abstract val layoutId: Int

    abstract fun createObserveData()

    abstract fun initView()

    abstract fun initArgument(bundle: Bundle)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.run {
            initArgument(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.lifecycleOwner = viewLifecycleOwner

        initView()

        createObserveData()
    }

    protected fun NavDirections.navigate() {
        NavHostFragment.findNavController(this@BaseFragment).navigate(this)
    }
}
