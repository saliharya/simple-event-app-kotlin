package com.salih.presentation.di
import com.salih.core.domain.usecase.GetAllEventsUseCase
import com.salih.core.domain.usecase.InsertEventUseCase
import com.salih.presentation.viewmodel.AuthViewModel
import com.salih.presentation.viewmodel.EventViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    factory { InsertEventUseCase(get()) }
    factory { GetAllEventsUseCase(get()) }

    viewModel { EventViewModel(get(), get()) }
    viewModel { AuthViewModel(get()) }
}