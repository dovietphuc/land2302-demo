package com.example.messageapp

import android.content.Context
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class FirebaseAuthHelper {
    public fun login(email: String, password: String) : Task<AuthResult> {
        val firebaseAuth = FirebaseAuth.getInstance()
        return firebaseAuth.signInWithEmailAndPassword(email, password)
    }

    public fun register(email: String, password: String) : Task<AuthResult> {
        val firebaseAuth = FirebaseAuth.getInstance()
        return firebaseAuth.createUserWithEmailAndPassword(email, password)
    }

    public fun forgotPassword(email: String) : Task<Void> {
        val firebaseAuth = FirebaseAuth.getInstance()
        return firebaseAuth.sendPasswordResetEmail(email)
    }

    public fun changePassword(context: Context, oldPwd: String, newPwd: String) {
        val firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser
        val credential = EmailAuthProvider.getCredential(currentUser!!.email!!, oldPwd)
        currentUser.reauthenticate(credential).addOnCompleteListener { task ->
            if(task.isSuccessful){
                currentUser.updatePassword(newPwd).addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Toast.makeText(context, "Password changed", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, task.exception!!.message, Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(context, task.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}