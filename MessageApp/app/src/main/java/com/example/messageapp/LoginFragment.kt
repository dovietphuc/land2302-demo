package com.example.messageapp

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.messageapp.databinding.FragmentLoginBinding
import com.github.razir.progressbutton.attachTextChangeAnimator
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.isProgressActive
import com.github.razir.progressbutton.showProgress
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val firebaseAuthHelper = FirebaseAuthHelper()
    private lateinit var mBinding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentLoginBinding.bind(view)

        bindProgressButton(mBinding.btnLogin)
        mBinding.btnLogin.attachTextChangeAnimator()

        mBinding.btnLogin.setOnClickListener {
            if(mBinding.btnLogin.isProgressActive()){
                return@setOnClickListener
            }
            mBinding.btnLogin.showProgress {
                buttonTextRes = R.string.loading
                progressColor = Color.WHITE
            }

            firebaseAuthHelper.login(
                mBinding.edtEmail.text.toString(),
                mBinding.edtPassword.text.toString()
            ).addOnCompleteListener { task ->
                mBinding.btnLogin.hideProgress()
                if (task.isSuccessful) {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_host, HomeFragment())
                        .commit()
                } else {
                    Toast.makeText(requireContext(), task.exception!!.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        mBinding.txtSignup.setOnClickListener {
            firebaseAuthHelper.register(
                mBinding.edtEmail.text.toString(),
                mBinding.edtPassword.text.toString()
            ).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), "Signup success", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), task.exception!!.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        mBinding.txtForgotPassword.setOnClickListener {
            firebaseAuthHelper.forgotPassword(mBinding.edtEmail.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            requireContext(), "Check your email to reset password",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(requireContext(), task.exception!!.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
        }
    }
}