package com.wavelus.miasteczko.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wavelus.miasteczko.R

/**
 * Aktywność odpowiadająca za wyświetlanie dostępnych lokalizacji i ich krótkich opisów
 * @see AppCompatActivity
 */
class PlacesActivity : AppCompatActivity() {

    /** Akcja podejmowana pod utworzeniu aktywności*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places)
    }
}
