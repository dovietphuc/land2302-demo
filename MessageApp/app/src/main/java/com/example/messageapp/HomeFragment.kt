package com.example.messageapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.messageapp.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var mBinding: FragmentHomeBinding
    private val firebaseAuthHelper = FirebaseAuthHelper()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentHomeBinding.bind(view)

        val firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser
        if (currentUser == null) {
            throw Exception("No current user")
        }
        mBinding.txtUserEmail.text = currentUser.email

        mBinding.btnLogout.setOnClickListener {
            firebaseAuth.signOut()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_host, LoginFragment())
                .commit()
        }

        mBinding.btnChangePwd.setOnClickListener {
            firebaseAuthHelper.changePassword(
                requireContext(),
                mBinding.edtOldPwd.text.toString(),
                mBinding.edtNewPwd.text.toString()
            )
        }
    }
}