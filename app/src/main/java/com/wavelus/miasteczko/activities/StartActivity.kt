package com.wavelus.miasteczko.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.wavelus.miasteczko.R
import com.wavelus.miasteczko.activities.login.LoginActivity
import com.wavelus.miasteczko.activities.signup.SignUpActivity
import kotlinx.android.synthetic.main.activity_start.*

/** Pierwszy ekran. Możliwe przejście do ekranu logowania bądź zakładania konta*/
class StartActivity : AppCompatActivity() {
    /** Punkt wejścia pakietu SDK Firebase Authenticaion*/
    var mAuth: FirebaseAuth? = null
    /** Referencja do bazy danych*/
    var user: FirebaseUser? = null
    /** Pozwala sprawdzić aktualny stan użytkownika - zalogowany/niezalogowany*/
    var mAuthListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        mAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener {
                firebaseAuth: FirebaseAuth ->

            user = firebaseAuth.currentUser

            if(user != null){
                startActivity(Intent(this, MenuActivity::class.java))
                finish()
            }else{
                Toast.makeText(this, "Not signed in", Toast.LENGTH_LONG).show()
            }
        }

        loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        createActButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    /** Przypisanie listenera - sprawdzenie czy użytkownik jest zalogowany*/
    override fun onStart() {
        super.onStart()
        mAuth!!.addAuthStateListener(mAuthListener!!)
    }

    /** Usunięcie listenera*/
    override fun onStop() {
        super.onStop()
        if(mAuthListener!=null){
            mAuth!!.removeAuthStateListener(mAuthListener!!)
        }
    }
}
