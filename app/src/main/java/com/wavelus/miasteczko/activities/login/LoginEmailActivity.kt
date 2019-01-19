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
/**Aktywność umożliwająca logowanie poprzez adres email i hasło*/
class LoginEmailActivity : AppCompatActivity() {
    /**
     * Zmienna przechowująca informacje określający, czy użytkownik jest zalogowany*/
    var mAuth: FirebaseAuth? = null
    /**
     * Akcja podejmowana podczas tworzenia aktywności*/
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
                loginUser(email, password)

            }else{
                Toast.makeText(this,"Podaj email i hasło!", Toast.LENGTH_LONG).show()
            }
        }


    }
    /**
     * Funkcja umożliwiająca zalogowanie poprzez adres email i hasło
     * @param email: Adres email wpisany przez użytkownika do zalogowania
     * @param password: Hasło wpisane przez użytkownika*/
    fun loginUser(email: String, password: String){
        mAuth!!.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                task: Task<AuthResult> ->
            if(task.isSuccessful){
                var menuIntent = Intent(this, MenuActivity::class.java)
                finishAffinity()
                startActivity(menuIntent)
            }else{
                Toast.makeText(this, "Błedny email lub hasło", Toast.LENGTH_LONG).show()
            }
        }
    }
    /**
     * Funkcja sprawdzająca czy podany adres email i hasło są w odpowiednim formacie i długości
     * @param email: Adres email wpisany przez użytkownika do zalogowania
     * @param password: Hasło wpisane przez użytkownika*/
    fun isEmailAndPasswordValid(email:String, password: String):Boolean{
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                &&!TextUtils.isEmpty(password) && password.length>5
    }

}
