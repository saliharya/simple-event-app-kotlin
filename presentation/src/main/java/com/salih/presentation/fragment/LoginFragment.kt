package com.salih.presentation.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.salih.common.base.BaseFragment
import com.salih.presentation.databinding.FragmentLoginBinding
import com.salih.presentation.viewmodel.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding, AuthViewModel>() {

    override val viewModel: AuthViewModel by viewModel()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
//            viewModel.login(username, password)
        }
    }

    override fun observeData() {
    }
}
