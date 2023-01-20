package com.example.internproject.di

import android.content.Context
import androidx.room.Room
import com.example.internproject.database.MyDatabase
import com.example.internproject.database.RegisterDbDao
import com.example.internproject.model.User
import com.example.internproject.utils.Constants.Database_name
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MyDatabase = Room.databaseBuilder(
        context, MyDatabase::class.java, Database_name
    ).allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(db: MyDatabase): RegisterDbDao = db.registerDbDao()

    @Provides
    fun provideEntity() = User()
}