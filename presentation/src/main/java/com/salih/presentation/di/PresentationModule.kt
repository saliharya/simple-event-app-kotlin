package com.salih.presentation.di
import com.salih.presentation.viewmodel.AuthViewModel
import com.salih.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { MainViewModel() }
    viewModel { AuthViewModel(get()) }
}