package com.salih.presentation.activity

import android.os.Bundle
import com.salih.common.base.BaseActivity
import com.salih.presentation.R
import com.salih.presentation.databinding.ActivityMainBinding
import com.salih.presentation.fragment.EventFragment
import com.salih.presentation.fragment.LoginFragment
import com.salih.presentation.viewmodel.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, AuthViewModel>() {

    override val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override val viewModel: AuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeData()
    }

    override fun observeData() {
        viewModel.isLoggedIn.observe(this) { loggedIn ->
            val fragment = if (loggedIn) EventFragment() else LoginFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
    }
}