package com.fireninja.lib_recipes.di

import android.content.Context
import androidx.room.Room
import com.fireninja.lib_recipes.domain.repository.LocalDataSource
import com.fireninja.lib_recipes.data.local.ThatsHotDatabase
import com.fireninja.lib_recipes.data.repository.LocalDataSourceImpl
import com.fireninja.lib_recipes.utils.Constants.DATABASE_NAME
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