package com.salih.presentation.activity

import com.salih.common.base.BaseActivity
import com.salih.presentation.databinding.ActivityMainBinding
import com.salih.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override val viewModel: MainViewModel by viewModel()

    override fun setupViews() {

    }

    override fun observeData() {}
}