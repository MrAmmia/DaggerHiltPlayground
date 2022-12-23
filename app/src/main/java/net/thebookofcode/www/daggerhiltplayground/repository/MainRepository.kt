package net.thebookofcode.www.daggerhiltplayground.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.thebookofcode.www.daggerhiltplayground.entities.Blog
import net.thebookofcode.www.daggerhiltplayground.retrofit.BlogRetrofit
import net.thebookofcode.www.daggerhiltplayground.retrofit.NetworkMapper
import net.thebookofcode.www.daggerhiltplayground.room.BlogDao
import net.thebookofcode.www.daggerhiltplayground.room.CacheMapper
import net.thebookofcode.www.daggerhiltplayground.util.DataState

class MainRepository
constructor(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
)
{
    suspend fun getBlog(): Flow<DataState<List<Blog>>> = flow{
        emit(DataState.Loading)
        try {
            val networkBlogs = blogRetrofit.get()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for (blog in blogs){
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cachedBlogs = blogDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        }catch (e:Exception){
            emit(DataState.Error(e))
        }
    }
}