package com.ihavenodomain.quotesterminal.di

import com.ihavenodomain.quotesterminal.quoteslist.data.repository.SecuritiesRepositoryImpl
import com.ihavenodomain.quotesterminal.quoteslist.domain.repository.SecuritiesRepository
import org.koin.dsl.bind
import org.koin.dsl.module

object RepositoryModule {
    val module = module {
        single { SecuritiesRepositoryImpl(get()) } bind SecuritiesRepository::class
    }
}