package com.elthobhy.movieapplicationdb.core.domain.repository

import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import com.elthobhy.movieapplicationdb.core.data.Resource
import com.elthobhy.movieapplicationdb.core.domain.model.DomainModel
import kotlinx.coroutines.flow.Flow


interface RepositoryInterface {
    fun getMovies(cm: ConnectivityManager): Flow<Resource<List<DomainModel>>>
    fun getDetailById(id: Int): LiveData<DomainModel>
}