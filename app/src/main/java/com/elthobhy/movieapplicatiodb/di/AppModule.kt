package com.elthobhy.movieapplicatiodb.di

import com.elthobhy.movieapplicatiodb.ui.detail.DetailViewModel
import com.elthobhy.movieapplicatiodb.ui.movie.MovieViewModel
import com.elthobhy.movieapplicationdb.core.domain.usecase.RepositoryInteract
import com.elthobhy.movieapplicationdb.core.domain.usecase.UseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCase = module {
    factory<UseCase> { RepositoryInteract(get()) }
}


val viewModel = module {
    viewModel { MovieViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}