package com.wavelus.miasteczko.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.wavelus.miasteczko.R
import kotlinx.android.synthetic.main.activity_menu.*

/**
 * Menu główne aplikacji
 * @see AppCompatActivity
 */
class MenuActivity : AppCompatActivity() {

    /** Zmienna przechowująca nazwę miejsca wydarzenia*/
    lateinit var townName: String

    /** Akcja podejmowana pod utworzeniu aktywności*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        townName = "Miasteczko AGH"

        allEventsBtn.setOnClickListener {
            var eventListIntent = Intent(this, EventListActivity::class.java)
            eventListIntent.putExtra("townName", townName)
            startActivity(eventListIntent)
        }
        userEventsBtn.setOnClickListener {
            var eventListIntent = Intent(this, EventListActivity::class.java)
            eventListIntent.putExtra("townName", townName)
            startActivity(eventListIntent)
        }
        userCreatedEventsBtnw.setOnClickListener {
            var eventListIntent = Intent(this, EventListActivity::class.java)
            eventListIntent.putExtra("townName", townName)
            startActivity(eventListIntent)
        }
        searchEventsByTagBtn.setOnClickListener {
            startActivity(Intent(this, SearchEventsActivity::class.java))
        }

        addEventBtn.setOnClickListener {
            var createEventIntent = Intent(this, CreateEventActivity::class.java)
            createEventIntent.putExtra("townName", townName)
            startActivity(createEventIntent)
        }
        mapBtn.setOnClickListener {
            var placeIntent = Intent(this, PlacesActivity::class.java)
            placeIntent.putExtra("townName", townName)
            startActivity(placeIntent)
        }
        aboutAppBtn.setOnClickListener {
            startActivity(Intent(this, AboutAppActivity::class.java))
        }


        menuTownTV.setOnClickListener {
            var options = arrayOf("Miasteczko AGH", "Politechnika Czyżyny")
            var builder = AlertDialog.Builder(this)
            builder.setTitle("Wybierz Miejsce")
            builder.setItems(options){_,which ->
                if(which == 0){
                    menuTownTV.text = "Miasteczko AGH"
                    townName = "Miasteczko AGH"
                }else{
                    menuTownTV.text = "Politechnika Czyżyny"
                    townName = "Politechnika"
                }

            }
            builder.show()
        }

    }


    /** Tworzenie menu opcji - przycisku */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    /**Przypisanie akcji w menu opcji*/
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        super.onOptionsItemSelected(item)
        if(item != null){
            if(item.itemId == R.id.logoutId){
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this,StartActivity::class.java))
                finish()
            }else if(item.itemId == R.id.settingsId){
                startActivity(Intent(this, AboutAppActivity::class.java))
            }
        }
        return true
    }
}
