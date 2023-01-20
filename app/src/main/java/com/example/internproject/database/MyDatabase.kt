package com.example.internproject.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.internproject.model.User

@Database(entities = [User::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun registerDbDao(): RegisterDbDao

}