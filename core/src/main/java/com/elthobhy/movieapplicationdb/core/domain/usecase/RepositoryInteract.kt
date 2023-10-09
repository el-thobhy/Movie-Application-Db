package com.elthobhy.movieapplicationdb.core.domain.usecase

import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import com.elthobhy.movieapplicationdb.core.data.Resource
import com.elthobhy.movieapplicationdb.core.domain.model.DomainModel
import com.elthobhy.movieapplicationdb.core.domain.repository.RepositoryInterface
import kotlinx.coroutines.flow.Flow

class RepositoryInteract(private val repository: RepositoryInterface) : UseCase {
    override fun getMovies(cm: ConnectivityManager): Flow<Resource<List<DomainModel>>> =
        repository.getMovies(cm)
    override fun getDetailById(id: Int): LiveData<DomainModel> =
        repository.getDetailById(id)

    override suspend fun delete() {
        repository.delete()
    }

}