package com.elthobhy.movieapplicationdb.core.utils

import com.elthobhy.movieapplicationdb.core.data.local.entity.Entity
import com.elthobhy.movieapplicationdb.core.data.remote.response.MovieResponseItem
import com.elthobhy.movieapplicationdb.core.domain.model.DomainModel

object DataMapper {
    fun mapMovieResponseToEntity(input: List<MovieResponseItem>): List<Entity> {
        val output = ArrayList<Entity>()
        input.map {
            val list = Entity(
                title = it.title,
                voteCount = it.voteCount,
                voteAverage = it.voteAverage,
                releaseDate = it.releaseDate,
                posterPath = it.posterPath,
                popularity = it.popularity,
                overview = it.overview,
                id = it.id,
                isTvShow = false,
                isFavorite = false,
                originalTitle = it.originalTitle,
                backdropPath = it.backdropPath,
                originalLanguage = it.originalLanguage,
            )
            output.add(list)
        }
        return output
    }

    fun mapDataEntityToDomain(input: Entity): DomainModel {
        return DomainModel(
            title = input.title,
            voteCount = input.voteCount,
            voteAverage = input.voteAverage,
            releaseDate = input.releaseDate,
            posterPath = input.posterPath,
            popularity = input.popularity,
            overview = input.overview,
            id = input.id,
            isTvShows = input.isTvShow,
            favorite = input.isFavorite,
            originalTitle = input.originalTitle,
            backdropPath = input.backdropPath,
            originalLanguage = input.originalLanguage,
        )
    }

    fun mapEntityToDomain(input: List<Entity>): List<DomainModel> {
        val output = ArrayList<DomainModel>()
        input.map {
            val list = DomainModel(
                title = it.title,
                backdropPath = it.backdropPath,
                voteCount = it.voteCount,
                voteAverage = it.voteAverage,
                releaseDate = it.releaseDate,
                posterPath = it.posterPath,
                originalTitle = it.originalTitle,
                popularity = it.popularity,
                overview = it.overview,
                id = it.id,
                isTvShows = it.isTvShow,
                favorite = it.isFavorite,
                originalLanguage = it.originalLanguage,
            )
            output.add(list)
        }
        return output
    }
}