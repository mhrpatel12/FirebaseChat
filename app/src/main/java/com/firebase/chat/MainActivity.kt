package com.firebase.chat

import android.content.Context
import android.os.Bundle
import android.widget.Toast

import com.google.firebase.auth.AuthResult
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class MainActivity : BaseActivity() {

    private val TAG = this::class.java.simpleName

    private var mContext: Context? = null
    // [START declare_auth]
    private var mAuth: FirebaseAuth? = null
    // [END declare_auth]


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mContext = this
        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance()
        // [END initialize_auth]
        signIn("mihirpatel@thehushapp.com", "khalnayak")
    }

    private fun createAccount(email: String, password: String) {
        Log.d(TAG, "createAccount:$email")
        showProgressDialog()
        // [START create_user_with_email]
        mAuth?.createUserWithEmailAndPassword(email, password)?.addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success")
                val user = mAuth?.currentUser
                updateUI(user)
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "createUserWithEmail:failure", task.exception)
                Toast.makeText(mContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                updateUI(null)
            }

            // [START_EXCLUDE]
            hideProgressDialog()
            // [END_EXCLUDE]
        })
        // [END create_user_with_email]
    }

    private fun signIn(email: String, password: String) {
        Log.d(TAG, "signIn:$email")

        showProgressDialog()

        // [START sign_in_with_email]
        mAuth?.signInWithEmailAndPassword(email, password)?.addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "signInWithEmail:success")
                val user = mAuth?.currentUser
                updateUI(user)
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "signInWithEmail:failure", task.exception)
                Toast.makeText(mContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                updateUI(null)
            }

            // [START_EXCLUDE]
            hideProgressDialog()
            // [END_EXCLUDE]
        })
        // [END sign_in_with_email]
    }

    private fun updateUI(user: FirebaseUser?) {
        hideProgressDialog()
    }
}
