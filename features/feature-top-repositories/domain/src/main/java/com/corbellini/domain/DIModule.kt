package com.corbellini.domain

import com.corbellini.domain.usecases.LoadAllPagedUseCase
import com.corbellini.domain.usecases.LoadAllPagedUseCaseImp
import org.koin.dsl.module

val topRepositoryDomain = module {
    factory<LoadAllPagedUseCase> {
        LoadAllPagedUseCaseImp(repository = get())
    }
}
