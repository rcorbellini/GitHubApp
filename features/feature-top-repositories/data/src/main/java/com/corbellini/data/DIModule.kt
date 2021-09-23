package com.corbellini.data

import com.corbellini.data.remote.services.RepositoryService
import com.corbellini.data.repositories.RepositoryRepositoryImp
import com.corbellini.domain.repositories.RepositoryRepository
import com.corbellini.domain.usecases.LoadAllPagedUseCase
import com.corbellini.domain.usecases.LoadAllPagedUseCaseImp
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val topRepositoryData = module {
    factory <RepositoryRepository> {
        RepositoryRepositoryImp(service = get())
    }
    single { provideRepoService(retrofit = get()) }

    single {
        provideRetrofit(
            okHttpClient = get(),
            url = "https://api.github.com/"
        )
    }


    single { provideOkHttpClient() }
}

internal fun provideRepoService(retrofit: Retrofit): RepositoryService =
    retrofit.create(RepositoryService::class.java)


internal fun provideRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    val gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .serializeNulls()
        .create()

    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

internal fun provideOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}