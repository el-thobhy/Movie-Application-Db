package com.elthobhy.movieapplicationdb.core.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.elthobhy.movieapplicationdb.core.data.local.entity.Entity
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("SELECT * FROM entity WHERE isTvShow = 0")
    fun getMovies(): Flow<List<Entity>>

    @Query("SELECT * FROM entity WHERE id = :id")
    fun getDetailById(id: Int): LiveData<Entity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<Entity>)

}