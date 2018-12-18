package com.wavelus.miasteczko.models

import android.content.Intent
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

    public fun loginUser(email:String, password: String): Boolean{
        var result: Boolean = false
        mFirebaseAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                task: Task<AuthResult> ->
            result = task.isSuccessful
        }
        return result
    }
}