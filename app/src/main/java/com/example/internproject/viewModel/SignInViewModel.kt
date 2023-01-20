package com.example.internproject.viewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.internproject.model.User
import com.example.internproject.repository.DbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val repository: DbRepository) : ViewModel() {


    fun checkUser(mobile: String, pass: String) = viewModelScope.launch {
        repository.checkUser(mobile, pass)
    }

    fun loginUser(mobile: String, pass: String) = viewModelScope.launch {
        repository.loginUser(mobile, pass)
    }
}