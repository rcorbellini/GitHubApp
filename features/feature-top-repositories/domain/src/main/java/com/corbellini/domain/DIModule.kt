package com.corbellini.domain

import com.corbellini.domain.usecases.LoadAllPagedRepositoryUseCase
import com.corbellini.domain.usecases.LoadAllPagedRepositoryUseCaseImp
import com.corbellini.domain.usecases.LoadAllPullRequestUseCase
import com.corbellini.domain.usecases.LoadAllPullRequestUseCaseImp
import org.koin.dsl.module

val topRepositoryDomain = module {
    factory<LoadAllPagedRepositoryUseCase> {
        LoadAllPagedRepositoryUseCaseImp(repository = get())
    }

    factory<LoadAllPullRequestUseCase> {
        LoadAllPullRequestUseCaseImp(repository = get())
    }
}
