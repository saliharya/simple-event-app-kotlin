package com.salih.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salih.common.util.ResourceState
import com.salih.core.domain.usecase.IsLoggedInUseCase
import com.salih.core.domain.usecase.LoginUseCase
import com.salih.core.domain.usecase.LogoutUseCase
import kotlinx.coroutines.launch
import androidx.lifecycle.asLiveData

class AuthViewModel(
    private val loginUseCase: LoginUseCase,
    private val logoutUseCase: LogoutUseCase,
    isLoggedInUseCase: IsLoggedInUseCase
) : ViewModel() {

    private val _loginState = MutableLiveData<ResourceState<Boolean>>()
    val loginState: LiveData<ResourceState<Boolean>> = _loginState
    val isLoggedIn = isLoggedInUseCase().asLiveData()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            loginUseCase(username, password).collect {
                _loginState.value = it
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
        }
    }
}
