package com.elthobhy.movieapplicatiodb.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elthobhy.movieapplicationdb.core.domain.model.DomainModel
import com.elthobhy.movieapplicationdb.core.domain.usecase.UseCase

class DetailViewModel(private val useCase: UseCase): ViewModel() {
    fun getDetailById(id: Int): LiveData<DomainModel> = useCase.getDetailById(id)
}