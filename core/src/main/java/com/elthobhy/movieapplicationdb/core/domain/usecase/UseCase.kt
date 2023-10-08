package com.elthobhy.movieapplicationdb.core.domain.usecase

import androidx.lifecycle.LiveData
import com.elthobhy.movieapplicationdb.core.data.Resource
import com.elthobhy.movieapplicationdb.core.domain.model.DomainModel
import kotlinx.coroutines.flow.Flow

interface UseCase {
    fun getMovies(): Flow<Resource<List<DomainModel>>>
    fun getDetailById(id: Int): LiveData<DomainModel>
}