package com.wavelus.miasteczko.models

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.wavelus.miasteczko.activities.MenuActivity

class EndUser() {
    public var mFirebaseAuth: FirebaseAuth? = null

    constructor(mFirebaseAuth: FirebaseAuth) : this() {
        this.mFirebaseAuth = mFirebaseAuth
    }

    public fun loginUser(email:String, password: String, context: Context): Boolean{
        var result: Boolean = false
        mFirebaseAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                task: Task<AuthResult> ->
            result = task.isSuccessful
            Log.d("TAGGG", "reult $result")

            if(task.isSuccessful){
                //var username = email.split("@")[0]
                var menuIntent = Intent(context, MenuActivity::class.java)
                //menuIntent.putExtra("name", username)
                context.startActivity(menuIntent)
//                context.finish()
            }else{
//                Toast.makeText(this, "Błedny email lub hasło", Toast.LENGTH_LONG).show()
            }
        }
        return result
    }
}