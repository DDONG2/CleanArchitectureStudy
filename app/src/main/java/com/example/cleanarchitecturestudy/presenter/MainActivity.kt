package com.example.cleanarchitecturestudy.presenter

import com.example.cleanarchitecturestudy.BaseActivity
import com.example.cleanarchitecturestudy.R
import com.example.cleanarchitecturestudy.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun createObserveData() {

    }

}