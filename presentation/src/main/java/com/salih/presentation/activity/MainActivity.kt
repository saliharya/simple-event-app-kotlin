package com.salih.presentation.activity

import com.salih.common.base.BaseActivity
import com.salih.presentation.R
import com.salih.presentation.databinding.ActivityMainBinding
import com.salih.presentation.fragment.EventFragment
import com.salih.presentation.viewmodel.EventViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, EventViewModel>() {

    override val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override val viewModel: EventViewModel by viewModel()

    override fun setupViews() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, EventFragment())
            .commit()
    }

    override fun observeData() {}
}