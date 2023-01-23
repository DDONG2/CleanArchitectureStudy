package com.example.cleanarchitecturestudy.di


import com.example.cleanarchitecturestudy.data.repository.DataFlowRepository
import com.example.cleanarchitecturestudy.data.repository.DataFlowRepositoryImpl
import com.example.cleanarchitecturestudy.data.service.DataService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
abstract class RetrofitDataModule {
    @Binds
    @ViewModelScoped
    abstract fun bindDataFlowRepository(dataFlowRepositoryImpl: DataFlowRepositoryImpl): DataFlowRepository

    companion object {
        @Provides
        @ViewModelScoped
        fun provideApiService(): DataService =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(DataService::class.java)

        private const val BASE_URL = "https://api.github.com/"

        private val client by lazy {
            OkHttpClient.Builder().apply {
                addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
            }.build()
        }
    }
}