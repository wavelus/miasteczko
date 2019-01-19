package com.wavelus.miasteczko.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.wavelus.miasteczko.R
import kotlinx.android.synthetic.main.activity_places.*

/**
 * Aktywność odpowiadająca za wyświetlanie dostępnych lokalizacji i ich krótkich opisów
 * @see AppCompatActivity
 */
class PlacesActivity : AppCompatActivity() {

    /** Akcja podejmowana pod utworzeniu aktywności*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places)

        var place: String? = intent.extras.getString("townName")

        if(place=="Politechnika"){
            mapPoliImageView.visibility = View.VISIBLE
            mapMiasteczkoImageView.visibility = View.GONE

        }else{
            mapMiasteczkoImageView.visibility = View.VISIBLE
            mapPoliImageView.visibility = View.GONE

        }
    }
}
