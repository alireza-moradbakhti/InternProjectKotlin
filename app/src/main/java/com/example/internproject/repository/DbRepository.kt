package com.example.internproject.repository

import com.example.internproject.database.RegisterDbDao
import com.example.internproject.model.User
import javax.inject.Inject

class DbRepository @Inject constructor(
    private val dao: RegisterDbDao
) {

    fun signUpUser(user: User) = dao.signUpUsers(user)
    fun loginUser(mobile: String, password: String) = dao.loginUsers(mobile, password)
    fun checkUser(mobile: String, pass: String) = dao.checkUser(mobile, pass)

}