package com.wavelus.miasteczko.activities.signup

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.wavelus.miasteczko.R
import com.wavelus.miasteczko.activities.MenuActivity
import kotlinx.android.synthetic.main.activity_create_email_account.*

class CreateEmailAccountActivity : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null;
    var mDatabase: DatabaseReference? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_email_account)



        mAuth = FirebaseAuth.getInstance()

        accountEmailCreateActBtn.setOnClickListener {
            var email = accountEmailEt.text.toString().trim()
            var password = accountPasswordEt.text.toString().trim()
            var displayName = accountDisplayNameEt.text.toString().trim()

            if(!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(password)&&!TextUtils.isEmpty(displayName)){
                createAccount(email,password,displayName)
            }else{
                Toast.makeText(this,"Wypełnij pola", Toast.LENGTH_LONG).show()
            }
        }

    }

    fun createAccount(email: String, password: String, displayName:String){
        mAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                task: Task<AuthResult> ->
            if(task.isSuccessful){
                var currentUserId = mAuth!!.currentUser
                var userId = currentUserId!!.uid

                mDatabase = FirebaseDatabase.getInstance().reference.child("users").child(userId)

                var userObject = HashMap<String,String>()
                userObject.put("display_name", displayName)
                userObject.put("status", "Helloooo")
                userObject.put("rank", "0")
                userObject.put("image", "default")
                userObject.put("thumb_image", "default")

                mDatabase!!.setValue(userObject).addOnCompleteListener {
                        task: Task<Void> ->
                    if(task.isSuccessful){
                        var menuIntent = Intent(this, MenuActivity::class.java)
                        menuIntent.putExtra("name", displayName)
                        startActivity(menuIntent)
                        finish()

                    }else{
                        Toast.makeText(this, "User not Created", Toast.LENGTH_LONG).show()

                    }
                }

            }else{
                Toast.makeText(this, "Try Again", Toast.LENGTH_LONG).show()

            }

        }


    }

}
