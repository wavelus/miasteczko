package com.wavelus.miasteczko.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wavelus.miasteczko.R

/** Klasa zawierająca w informacje na temat licencji*/
class AboutAppActivity : AppCompatActivity() {

    /** Akcja podejmowana po utworzeniu aktywności */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_app)
    }
}
