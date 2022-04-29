package com.stephan.lib_recipe.di

import android.content.Context
import androidx.room.Room
import com.stephan.lib_recipe.data.local.ThatsHotDatabase
import com.stephan.lib_recipe.data.repository.LocalDataSourceImpl
import com.stephan.lib_recipe.domain.repository.ThatsHotLocalDataSource
import com.stephan.lib_recipe.util.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule{

    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext context: Context
    ) : ThatsHotDatabase {
        return Room.databaseBuilder(
            context, ThatsHotDatabase::class.java, DB_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun providesLocalDataSource(database: ThatsHotDatabase) : ThatsHotLocalDataSource {
        return LocalDataSourceImpl(database)
    }

}