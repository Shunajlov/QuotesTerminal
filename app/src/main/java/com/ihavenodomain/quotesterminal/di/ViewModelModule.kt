package com.ihavenodomain.quotesterminal.di

import com.ihavenodomain.quotesterminal.quoteslist.presentation.viewmodel.SecuritiesListMainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {
    val module by lazy {
        module {
            viewModel { SecuritiesListMainViewModel(get()) }
        }
    }
}