package com.harikrish.planit.di

import android.content.Context
import androidx.room.Room
import com.harikrish.planit.db.TaskDao
import com.harikrish.planit.db.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesTaskDatabase(@ApplicationContext context: Context) : TaskDatabase{
        return Room.databaseBuilder(
            context,
            TaskDatabase::class.java,
            "task_database"
        ).build()
    }

    @Provides
    fun provideTaskDao(database: TaskDatabase): TaskDao{
        return database.taskDao()
    }

}