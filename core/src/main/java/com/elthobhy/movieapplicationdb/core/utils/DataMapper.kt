package com.elthobhy.movieapplicationdb.core.utils

import com.elthobhy.movieapplicationdb.core.data.local.entity.Entity
import com.elthobhy.movieapplicationdb.core.data.remote.response.MovieResponseItem
import com.elthobhy.movieapplicationdb.core.data.remote.response.TvShowResponseItem
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
                backdrop_path = it.backdrop_path,
                originalLanguage = it.originalLanguage,
            )
            output.add(list)
        }
        return output
    }

    fun mapDomainToEntity(input: DomainModel): Entity {
        return Entity(
            title = input.title,
            voteCount = input.voteCount,
            voteAverage = input.voteAverage,
            releaseDate = input.releaseDate,
            posterPath = input.posterPath,
            popularity = input.popularity,
            overview = input.overview,
            id = input.id,
            isTvShow = input.isTvShows,
            isFavorite = input.favorite,
            originalTitle = input.original_title,
            backdrop_path = input.backdrop_path,
            originalLanguage = input.originalLanguage,
        )
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
            original_title = input.originalTitle,
            backdrop_path = input.backdrop_path,
            originalLanguage = input.originalLanguage,
        )
    }

    fun mapShowResponseToEntity(input: List<TvShowResponseItem>): List<Entity> {
        val output = ArrayList<Entity>()
        input.map {
            val list = it.id?.let { it1 ->
                Entity(
                    title = it.name,
                    voteCount = it.voteCount,
                    voteAverage = it.voteAverage,
                    releaseDate = it.firstAirDate,
                    posterPath = it.posterPath,
                    popularity = it.popularity,
                    overview = it.overview,
                    id = it1,
                    isTvShow = true,
                    isFavorite = false,
                    originalTitle = it.originalTitle,
                    backdrop_path = it.backdropPath,
                    originalLanguage = it.originalLanguage,
                )
            }
            if (list != null) {
                output.add(list)
            }
        }
        return output
    }

    fun mapEntityToDomain(input: List<Entity>): List<DomainModel> {
        val output = ArrayList<DomainModel>()
        input.map {
            val list = DomainModel(
                title = it.title,
                backdrop_path = it.backdrop_path,
                voteCount = it.voteCount,
                voteAverage = it.voteAverage,
                releaseDate = it.releaseDate,
                posterPath = it.posterPath,
                original_title = it.originalTitle,
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