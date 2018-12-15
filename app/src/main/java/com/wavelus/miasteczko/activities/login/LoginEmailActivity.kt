package com.wavelus.miasteczko.activities.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.wavelus.miasteczko.R
import com.wavelus.miasteczko.activities.MenuActivity
import kotlinx.android.synthetic.main.activity_login_email.*

class LoginEmailActivity : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        //FirebaseApp.initializeApp(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_email)

        mAuth = FirebaseAuth.getInstance()

        loginEmailButtonId.setOnClickListener {
            var email = loginEmailEt.text.toString().trim()
            var password = loginEmailPasswordEt.text.toString().trim()
            if (!TextUtils.isEmpty(email)||!TextUtils.isEmpty(password)){
                loginUser(email, password)
            }else{
                Toast.makeText(this,"Podaj email i hasło!", Toast.LENGTH_LONG).show()
            }
        }


    }
    private fun loginUser(email: String, password: String){
        mAuth!!.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                task: Task<AuthResult> ->
            if(task.isSuccessful){
                //var username = email.split("@")[0]
                var menuIntent = Intent(this, MenuActivity::class.java)
                //menuIntent.putExtra("name", username)
                startActivity(menuIntent)
                finish()
            }else{
                Toast.makeText(this, "Błedny email lub hasło", Toast.LENGTH_LONG).show()
            }
        }
    }
}
