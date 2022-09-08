package com.jorge.paulo.mvi.di

import android.content.Context
import androidx.room.Room
import com.jorge.paulo.mvi.data.local.AnimalDao
import com.jorge.paulo.mvi.data.local.AppDatabase
import com.jorge.paulo.mvi.data.remote.MyApi
import com.jorge.paulo.mvi.data.repository.MyRepositoryApiImpl
import com.jorge.paulo.mvi.data.repository.MyRepositoryLocalImpl
import com.jorge.paulo.mvi.domain.repository.MyRepositoryApi
import com.jorge.paulo.mvi.domain.repository.MyRepositoryLocal
import com.jorge.paulo.mvi.domain.use_case.GetAnimalUseCase
import com.jorge.paulo.mvi.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideMyRepositoryApi(api: MyApi): MyRepositoryApi {
        return MyRepositoryApiImpl(api)
    }

    @Provides
    @Singleton
    fun provideMyRepositoryLocal(dao: AnimalDao): MyRepositoryLocal {
        return MyRepositoryLocalImpl(dao)
    }

    @Provides
    @Singleton
    fun provideMyUseCase(
        repositoryApi: MyRepositoryApi,
        repositoryLocal: MyRepositoryLocal
    ): GetAnimalUseCase {
        return GetAnimalUseCase(repositoryApi, repositoryLocal)
    }

    @Provides
    fun provideDao(appDatabase: AppDatabase): AnimalDao {
        return appDatabase.dbDao()
    }

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext applicationContext: Context) =
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, Constants.DB
        ).build()

}