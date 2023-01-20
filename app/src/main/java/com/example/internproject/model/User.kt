package com.example.internproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.internproject.utils.Constants.Table_name

@Entity(tableName = Table_name)
data class User(
    @PrimaryKey(autoGenerate = false)
    val user_id: String = "",

    @ColumnInfo(name = "user_name")
    val username: String = "",

    @ColumnInfo(name = "user_mobile")
    val mobile: String = "",

    @ColumnInfo(name = "user_pass")
    val password: String = ""
)