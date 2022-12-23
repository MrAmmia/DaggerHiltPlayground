package net.thebookofcode.www.daggerhiltplayground.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.thebookofcode.www.daggerhiltplayground.repository.MainRepository
import net.thebookofcode.www.daggerhiltplayground.retrofit.BlogRetrofit
import net.thebookofcode.www.daggerhiltplayground.retrofit.NetworkMapper
import net.thebookofcode.www.daggerhiltplayground.room.BlogDao
import net.thebookofcode.www.daggerhiltplayground.room.CacheMapper
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        blogRetrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ):MainRepository{
        return MainRepository(blogDao,blogRetrofit,cacheMapper,networkMapper)
    }
}