package com.ihavenodomain.quotesterminal.di

import com.ihavenodomain.quotesterminal.quoteslist.domain.interactors.QuotesInteractor
import com.ihavenodomain.quotesterminal.quoteslist.domain.interactors.QuotesInteractorImpl
import org.koin.dsl.bind
import org.koin.dsl.module

object InteractorModule {
    val module by lazy {
        module {
            single { QuotesInteractorImpl(get()) } bind QuotesInteractor::class
        }
    }
}