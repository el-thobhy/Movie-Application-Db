package com.elthobhy.movieapplicationdb.core.data

import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.elthobhy.movieapplicationdb.core.data.local.LocalDataSource
import com.elthobhy.movieapplicationdb.core.data.remote.RemoteDataSource
import com.elthobhy.movieapplicationdb.core.data.remote.networking.ApiResponse
import com.elthobhy.movieapplicationdb.core.data.remote.response.MovieResponseItem
import com.elthobhy.movieapplicationdb.core.data.remote.response.TvShowResponseItem
import com.elthobhy.movieapplicationdb.core.domain.model.DomainModel
import com.elthobhy.movieapplicationdb.core.domain.repository.RepositoryInterface
import com.elthobhy.movieapplicationdb.core.utils.AppExecutors
import com.elthobhy.movieapplicationdb.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Suppress("DEPRECATION")
class Repository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
) : RepositoryInterface {
    override fun getMovies(cm: ConnectivityManager): Flow<Resource<List<DomainModel>>> =
        object : NetworkBoundResource<List<DomainModel>, List<MovieResponseItem>>() {
            override fun loadFromDB(): Flow<List<DomainModel>> {
                return localDataSource.getMovies().map { DataMapper.mapEntityToDomain(it) }
            }

            override fun shouldFetch(data: List<DomainModel>?): Boolean {
                val infoNet = cm.activeNetworkInfo != null && cm.activeNetworkInfo?.isConnected == true
                return data.isNullOrEmpty() || infoNet
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponseItem>>> {
                return remoteDataSource.getMovies()
            }

            override suspend fun saveCallResult(data: List<MovieResponseItem>) {
                val dataMap = DataMapper.mapMovieResponseToEntity(data)
                return localDataSource.insert(dataMap)
            }
        }.asFlow()

    override fun getDetailById(id: Int): LiveData<DomainModel> {
        return localDataSource.getDetailById(id).map {
            DataMapper.mapDataEntityToDomain(it)
        }
    }

    override suspend fun delete() {
        localDataSource.delete()
    }

}