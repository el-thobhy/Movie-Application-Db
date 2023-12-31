package com.elthobhy.movieapplicatiodb.ui.movie

import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.elthobhy.movieapplicationdb.core.data.Resource
import com.elthobhy.movieapplicationdb.core.domain.model.DomainModel
import com.elthobhy.movieapplicationdb.core.domain.usecase.UseCase


class MovieViewModel(private val useCase: UseCase) : ViewModel() {
    fun getMovies(cm: ConnectivityManager): LiveData<Resource<List<DomainModel>>> =
        useCase.getMovies(cm).asLiveData()
    suspend fun delete()=useCase.delete()
}