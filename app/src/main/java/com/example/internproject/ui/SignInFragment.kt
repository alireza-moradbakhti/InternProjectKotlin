package com.example.internproject.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.example.internproject.R
import com.example.internproject.databinding.FragmentSignInBinding
import com.example.internproject.viewModel.SignInViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignInFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentSignInBinding


    private val loginViewModel: SignInViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        validate()
        signIn()

        return binding.root
    }

    private fun signIn() {
        binding.apply {
            btnLogin.setOnClickListener {
                val mobile = loginMobileEdt.text.toString()
                val pass = LoginPassEdt.text.toString()
                val check = loginViewModel.checkUser(mobile,pass)
                if (loginMobileEdt.text!!.isEmpty() || LoginPassEdt.text!!.isEmpty()) {
                    Toast.makeText(
                        requireActivity(),
                        "لطفا اطلاعات خود را وارد نمایید!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    if (check == null) {
                        Toast.makeText(
                            requireActivity(),
                            "حساب کاربری یافت نشد!",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    } else {
                        loginViewModel.loginUser(mobile,pass)
                        Toast.makeText(
                            requireActivity(),
                            "خوش آمدید",
                            Toast.LENGTH_SHORT
                        ).show()

                        val intent = Intent(requireActivity(), MovieActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
    }

    private fun validate() {

        binding.loginMobileEdt.doOnTextChanged { text, start, before, count ->
            if (text!!.length < 5) {
                binding.textInputLayout1.error =
                    "لطفا شماره همراه خود را به درستی وارد کنید!"
            } else if (text.isNotEmpty()) {
                binding.textInputLayout1.error = null
            }
        }
        binding.LoginPassEdt.doOnTextChanged { text, start, before, count ->
            if (text!!.length < 5) {
                binding.textInputLayout2.error = "لطفا رمز عبور خود را به درستی وارد کنید! "
            } else if (text.isNotEmpty()) {
                binding.textInputLayout2.error = null
            }
        }
    }

    override fun getTheme(): Int {
        return R.style.CustomBottomSheetDialog
    }
}