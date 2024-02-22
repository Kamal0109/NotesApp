package com.example.internshala

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            // If there is no saved instance state, add the LoginFragment
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoginFragment())
                .commit()
        }

    }

    fun showNotesFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, NotesFragment())
            .commit()
    }

    fun onGoogleLoginSuccess(account: GoogleSignInAccount?) {
        // Save user login state using SharedPreferences or SQLite
        // Navigate to NotesFragment
        showNotesFragment()
    }

}