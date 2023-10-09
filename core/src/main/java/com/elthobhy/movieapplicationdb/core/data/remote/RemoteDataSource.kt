package com.elthobhy.movieapplicationdb.core.data.remote

import com.elthobhy.movieapplicationdb.BuildConfig
import com.elthobhy.movieapplicationdb.core.data.remote.networking.ApiConfig
import com.elthobhy.movieapplicationdb.core.data.remote.networking.ApiResponse
import com.elthobhy.movieapplicationdb.core.data.remote.response.MovieResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource {

    suspend fun getMovies(): Flow<ApiResponse<List<MovieResponseItem>>> {
        return flow {
            try {
                val response = ApiConfig.getApiService().getMovies(BuildConfig.API_KEY)
                val list = response.results
                if (list.isNotEmpty()) {
                    emit(ApiResponse.Success(list))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}