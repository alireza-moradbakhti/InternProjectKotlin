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
import com.example.internproject.database.RegisterDbDao
import com.example.internproject.databinding.FragmentSignUpBinding
import com.example.internproject.model.User
import com.example.internproject.repository.DbRepository
import com.example.internproject.viewModel.SignUpViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentSignUpBinding
//
//    @Inject
//    lateinit var registerDbDao: RegisterDbDao
//
//    @Inject
//    lateinit var dbRepository: DbRepository

    private val signUpViewModel : SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        validate()
        signUp()

        return binding.root
    }


    private fun signUp() {
        binding.apply {
            btnSignUp.setOnClickListener {
                val uname = SignUpNameEdt.text.toString()
                val mobile = SignUpMobileEdt.text.toString()
                val pass = SignUpPassEdt.text.toString()

                if (SignUpNameEdt.text.isEmpty() || SignUpMobileEdt.text.isEmpty() || SignUpPassEdt.text.isEmpty()) {
                    Toast.makeText(
                        requireActivity(),
                        "لطفا مقادیر خواسته شده را وارد کنید!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    signUpViewModel.signUpUser(
                        user = User(
                            UUID.randomUUID().toString(),
                            uname,
                            mobile,
                            pass
                        )
                    )
                    Toast.makeText(
                        requireActivity(),
                        "حساب کاربری با موفقیت ساخته شد",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intent = Intent(requireActivity(),MovieActivity::class.java)
                    startActivity(intent)
                }

            }
        }
    }

    private fun validate() {
        binding.apply {
            SignUpNameEdt.doOnTextChanged { text, start, before, count ->
                if (text!!.length < 4) {
                    textInputLayout1.error =
                        "لطفا نام کاربری خود را به درستی وارد نمایید!"
                } else if (text.isNotEmpty()) {
                    textInputLayout1.error = null
                }
            }
            SignUpMobileEdt.doOnTextChanged { text, start, before, count ->
                if (text!!.length < 10) {
                    textInputLayout2.error =
                        "لطفا شماره همراه خود را به درستی وارد نمایید!"
                } else if (text.isNotEmpty()) {
                    textInputLayout2.error = null
                }
            }
            SignUpPassEdt.doOnTextChanged { text, start, before, count ->
                if (text!!.length < 4) {
                    textInputLayout3.error =
                        "لطفا رمز عبور خود را به درستی وارد نمایید!"
                } else if (text.isNotEmpty()) {
                    textInputLayout3.error = null
                }
            }
        }

    }

    override fun getTheme(): Int {
        return R.style.CustomBottomSheetDialog
    }
}