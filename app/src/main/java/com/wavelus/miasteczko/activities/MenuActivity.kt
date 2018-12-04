package com.wavelus.miasteczko.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wavelus.miasteczko.R
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        allEventsBtn.setOnClickListener {
            startActivity(Intent(this, EventListActivity::class.java))
        }

    }
}
