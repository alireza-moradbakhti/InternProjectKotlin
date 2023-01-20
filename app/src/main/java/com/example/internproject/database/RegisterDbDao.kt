package com.example.internproject.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.internproject.model.User
import com.example.internproject.utils.Constants.Table_name

@Dao
interface RegisterDbDao {

    //SignUp
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun signUpUsers(user: User)

    //Login
    @Query("SELECT * FROM $Table_name WHERE user_mobile LIKE :mobile AND user_pass LIKE :pass")
    fun loginUsers(mobile: String, pass: String): User

    //check if user exist
    @Query("Select * from $Table_name where user_mobile like :mobile and user_pass like :pass")
    fun checkUser(mobile: String, pass: String): User

}