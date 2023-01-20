package com.example.internproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.internproject.databinding.ActivityMainBinding
import com.example.internproject.utils.Constants.TAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        super.onCreate(savedInstanceState)
        setContentView(view)


        signInBottomSheet()
        signUpBottomSheet()
        closeApp()

    }

    private fun signInBottomSheet() {

        binding.apply {
            btnSignIn.setOnClickListener {
                val signInFragment = SignInFragment()
                signInFragment.show(supportFragmentManager, TAG)
            }
        }
    }

    private fun signUpBottomSheet() {
        binding.apply {
            btnSignUp.setOnClickListener {
                val signUpFragment = SignUpFragment()
                signUpFragment.show(supportFragmentManager, TAG)
            }
        }
    }

    private fun closeApp() {
        binding.apply {
            imageButton.setOnClickListener {
                finish()
            }
        }
    }
}