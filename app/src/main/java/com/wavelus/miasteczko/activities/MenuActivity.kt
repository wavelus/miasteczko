package com.wavelus.miasteczko.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.wavelus.miasteczko.R
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        allEventsBtn.setOnClickListener {
            startActivity(Intent(this, EventListActivity::class.java))
        }

        addEventBtn.setOnClickListener {
            startActivity(Intent(this, CreateEventActivity::class.java))
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        super.onOptionsItemSelected(item)

        if(item != null){
            if(item.itemId == R.id.logoutId){
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this,StartActivity::class.java))
                finish()
            }else if(item.itemId == R.id.settingsId){
//                startActivity(Intent(this, SettingsActivity::class.java))

            }
        }

        return true
    }


}
