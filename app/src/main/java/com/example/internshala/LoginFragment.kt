package com.example.internshala

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.api.GoogleApiClient

class LoginFragment : Fragment() {

    private lateinit var googleApiClient: GoogleApiClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        // Configure Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        // Initialize GoogleApiClient
        googleApiClient = GoogleApiClient.Builder(requireContext())
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build()

        // Set up the Google Sign-In button click listener
        view.findViewById<com.google.android.gms.common.SignInButton>(R.id.btnGoogleSignIn)
            .setOnClickListener {
                signInWithGoogle()
            }

        return view
    }

    private fun signInWithGoogle() {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
        startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_GOOGLE_SIGN_IN) {
            val result = data?.let { Auth.GoogleSignInApi.getSignInResultFromIntent(it) }
            if (result != null) {
                handleGoogleSignInResult(result)
            }
        }
    }


    private fun handleGoogleSignInResult(result: GoogleSignInResult) {
        if (result.isSuccess) {
            // Google Sign-In was successful, authenticate with your server or save user info
            val account = result.signInAccount
            // Call the onGoogleLoginSuccess() method in MainActivity to switch to NotesFragment
            (activity as? MainActivity)?.onGoogleLoginSuccess(account)
        } else {
            // Google Sign-In failed, update UI accordingly (display error message, etc.)
        }
    }

    // Constants
    companion object {
        private const val RC_GOOGLE_SIGN_IN = 9001
    }
}