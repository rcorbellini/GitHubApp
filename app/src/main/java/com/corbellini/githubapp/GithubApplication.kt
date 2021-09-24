package com.corbellini.githubapp

import android.app.Application
import com.corbellini.data.topRepositoryData
import com.corbellini.domain.topRepositoryDomain
import com.corbellini.presentation.topRepositoryPresentation
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GithubApplication : Application() {

    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidContext(this@GithubApplication)
            modules(
                topRepositoryDomain,
                topRepositoryData,
                topRepositoryPresentation,
            )
        }

    }

}