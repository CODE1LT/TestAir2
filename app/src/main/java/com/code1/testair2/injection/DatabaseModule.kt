package com.code1.testair2.injection

import com.code1.testair2.common.Database
import androidx.room.Room
import com.code1.testair2.util.Application
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

private const val DATABASE_NAME = "testair-db.db"

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(application: Application): Database {
        return Room.databaseBuilder(
            application.applicationContext,
            Database::class.java,
            DATABASE_NAME
        ).build()
    }
}