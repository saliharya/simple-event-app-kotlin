package com.salih.presentation.di
import com.salih.presentation.viewmodel.AuthViewModel
import com.salih.presentation.viewmodel.EventViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { EventViewModel() }
    viewModel { AuthViewModel(get()) }
}