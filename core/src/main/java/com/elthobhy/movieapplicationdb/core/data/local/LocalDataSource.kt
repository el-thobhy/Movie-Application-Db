package com.elthobhy.movieapplicationdb.core.data.local

import androidx.lifecycle.LiveData
import com.elthobhy.movieapplicationdb.core.data.local.entity.Entity
import com.elthobhy.movieapplicationdb.core.data.local.room.Dao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val dao: Dao) {
    fun getMovies(): Flow<List<Entity>> = dao.getMovies()

    fun getDetailById(id: Int): LiveData<Entity> = dao.getDetailById(id)
    suspend fun insert(entity: List<Entity>) = dao.insert(entity)
}