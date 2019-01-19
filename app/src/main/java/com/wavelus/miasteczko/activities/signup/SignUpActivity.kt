package com.wavelus.miasteczko.activities.signup

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wavelus.miasteczko.R
import kotlinx.android.synthetic.main.activity_sign_up.*

/**
 * Aktywność umożliwiająca wybór sposobu rejestracji*/
class SignUpActivity : AppCompatActivity() {
    /**
     * Akcja podejmowana podczas tworzenia aktywności*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        signUpEmailBtn.setOnClickListener {
            startActivity(Intent(this, CreateEmailAccountActivity::class.java))
        }
    }
}
