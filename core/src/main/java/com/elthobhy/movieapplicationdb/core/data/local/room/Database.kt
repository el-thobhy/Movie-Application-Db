package com.elthobhy.movieapplicationdb.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.elthobhy.movieapplicationdb.core.data.local.entity.Entity
import com.elthobhy.movieapplicationdb.core.data.local.room.Dao

@Database(entities = [Entity::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun dao(): Dao
}