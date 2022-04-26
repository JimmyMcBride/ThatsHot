package com.example.thatshot.di

import android.content.Context
import androidx.room.Room
import com.example.thatshot.domain.repository.LocalDataSource
import com.example.thatshot.data.local.ThatsHotDatabase
import com.example.thatshot.data.repository.LocalDataSourceImpl
import com.example.thatshot.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): ThatsHotDatabase {
        return Room.databaseBuilder(
            context,
            ThatsHotDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(database: ThatsHotDatabase): LocalDataSource {
        return LocalDataSourceImpl(database)
    }
}