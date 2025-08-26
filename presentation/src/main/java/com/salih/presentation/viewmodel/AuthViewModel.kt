package com.salih.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salih.common.util.ResourceState
import com.salih.core.domain.usecase.LoginUseCase
import kotlinx.coroutines.launch

class AuthViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _loginState = MutableLiveData<ResourceState<Boolean>>()
    val loginState: LiveData<ResourceState<Boolean>> = _loginState

    fun login(username: String, password: String) {
        viewModelScope.launch {
            loginUseCase(username, password).collect {
                _loginState.value = it
            }
        }
    }
}
