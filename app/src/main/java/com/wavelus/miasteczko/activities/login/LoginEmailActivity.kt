package com.wavelus.miasteczko.activities.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.wavelus.miasteczko.R
import com.wavelus.miasteczko.activities.MenuActivity
import com.wavelus.miasteczko.models.EndUser
import kotlinx.android.synthetic.main.activity_login_email.*

class LoginEmailActivity : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null
    var mUser: EndUser? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        //FirebaseApp.initializeApp(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_email)
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance()

        loginEmailButtonId.setOnClickListener {
            var email = loginEmailEt.text.toString().trim()
            var password = loginEmailPasswordEt.text.toString().trim()
            if (isEmailAndPasswordValid(email,password)){
//                loginUser(email, password)
                mUser = EndUser(mAuth!!)
            var isUserLoggedIn: Boolean = mUser!!.loginUser(email,password)

                if(isUserLoggedIn) {
                    startNextActivity()
                }else{
                Toast.makeText(this, "Błedny email lub hasło", Toast.LENGTH_LONG).show()
            }


            }else{
                Toast.makeText(this,"Podaj email i hasło!", Toast.LENGTH_LONG).show()
            }
        }


    }
    fun loginUser(email: String, password: String){
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
    fun isEmailAndPasswordValid(email:String, password: String):Boolean{
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                &&!TextUtils.isEmpty(password) && password.length>5
    }

    fun startNextActivity(){
        var menuIntent = Intent(this, MenuActivity::class.java)
        startActivity(menuIntent)
        finish()
    }

}
