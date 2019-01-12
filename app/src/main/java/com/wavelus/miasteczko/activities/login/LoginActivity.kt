package com.wavelus.miasteczko.activities.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wavelus.miasteczko.R
import kotlinx.android.synthetic.main.activity_login.*
/**Aktywność umożliwiająca wybór sposobu logowania*/
class LoginActivity : AppCompatActivity() {

    /**Akcja podejmowana podczas tworzenia aktywności*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginEmailBtn.setOnClickListener {
            startActivity(Intent(this, LoginEmailActivity::class.java))
        }
    }
}
