package com.elthobhy.movieapplicationdb.core.di

import androidx.room.Room
import com.elthobhy.movieapplicationdb.core.data.Repository
import com.elthobhy.movieapplicationdb.core.data.local.LocalDataSource
import com.elthobhy.movieapplicationdb.core.data.local.room.Database
import com.elthobhy.movieapplicationdb.core.data.remote.networking.ApiConfig
import com.elthobhy.movieapplicationdb.core.domain.repository.RepositoryInterface
import com.elthobhy.movieapplicationdb.core.utils.AppExecutors
import com.elthobhy.movieapplicationdb.core.data.remote.RemoteDataSource
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networking = module {
    single { ApiConfig }
}

val repository = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource() }
    factory { AppExecutors() }
    single<RepositoryInterface> { Repository(get(), get()) }
}
val database = module {
    factory { get<Database>().dao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("passphrase".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            Database::class.java, "movie_db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}