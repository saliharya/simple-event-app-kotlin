package com.salih.presentation.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.salih.common.base.BaseFragment
import com.salih.common.util.ResourceState
import com.salih.common.util.showToast
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
            viewModel.login(username, password)
        }
    }

    override fun observeData() {
        viewModel.loginState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResourceState.Loading -> showLoading()
                is ResourceState.Success -> {
                    hideLoading()
                    navigateToHome()
                }

                is ResourceState.Error -> {
                    hideLoading()
                    requireActivity().showToast(state.message)
                }
            }
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = android.view.View.VISIBLE
        binding.btnLogin.isEnabled = false
    }

    private fun hideLoading() {
        binding.progressBar.visibility = android.view.View.GONE
        binding.btnLogin.isEnabled = true
    }

    private fun navigateToHome() {
        requireActivity().showToast("Login berhasil!")
    }
}