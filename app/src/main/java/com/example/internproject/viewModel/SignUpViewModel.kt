package com.example.internproject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.internproject.model.User
import com.example.internproject.repository.DbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val repository: DbRepository) : ViewModel() {

    fun signUpUser(user: User) = viewModelScope.launch {
        repository.signUpUser(user)
    }
}