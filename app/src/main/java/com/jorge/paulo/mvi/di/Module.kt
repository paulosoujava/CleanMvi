package com.jorge.paulo.mvi.di

import com.jorge.paulo.mvi.data.remote.MyApi
import com.jorge.paulo.mvi.data.repository.MyRepositoryImpl
import com.jorge.paulo.mvi.domain.repository.MyRepository
import com.jorge.paulo.mvi.domain.use_case.GetAnimalUseCase
import com.jorge.paulo.mvi.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideMyApi(): MyApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMyRepository(api: MyApi): MyRepository {
        return MyRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideMyUseCase(repository: MyRepository): GetAnimalUseCase {
        return GetAnimalUseCase(repository)
    }
}