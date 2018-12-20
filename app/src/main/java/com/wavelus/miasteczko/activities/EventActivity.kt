package com.wavelus.miasteczko.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.wavelus.miasteczko.R
import com.wavelus.miasteczko.models.MyTable
import kotlinx.android.synthetic.main.activity_event.*

class EventActivity : AppCompatActivity() {
    /** Punkt wejścia pakietu SDK Firebase Authenticaion*/
    var mAuth: FirebaseAuth? = null
    /** Referencja do bazy danych*/
    var mDatabase: FirebaseDatabase? = null

    var eventName: String?=null
    var eventOwner: String?=null
    var eventOwnerId: String?=null
    var eventStatus: String?=null
    var eventPlace: String?=null
    var eventDateStart: String?=null
    var eventDateEnd: String?=null
    var eventNumberOfAttendees: String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance()
        var currentUserId = mAuth!!.currentUser!!.uid

        eventName = intent.extras.getString("event_name")
        eventOwner = intent.extras.getString("event_owner_name")
        eventOwnerId = intent.extras.getString("event_owner_id")
        eventStatus = intent.extras.getString("event_status")
        eventPlace = intent.extras.getString("event_place_name")
        eventDateStart = intent.extras.getString("event_date_start")
        eventDateEnd = intent.extras.getString("event_date_end")
        eventNumberOfAttendees = intent.extras.getString("event_number_attendees")

        eventNameTv.text = eventName
        eventOwnerTv.text = "Organizator: " + eventOwner
        eventStatusTv.text = "Wkrótce"
//        eventStatusTv.text = eventStatus
        eventNumberOfAttendeesTv.text = "Liczba chętnych: " + eventNumberOfAttendees
        eventPlaceTv.text = eventPlace
        eventDateStartTv.text = "Kiedy? " + eventDateStart
        eventDateEndTv.text = eventDateEnd

        joinToEvent.setOnClickListener {
            joinToEvent.visibility = View.GONE
            disJoinToEvent.visibility = View.VISIBLE
        }
        disJoinToEvent.setOnClickListener {
            disJoinToEvent.visibility = View.GONE
            joinToEvent.visibility = View.VISIBLE
        }
        Log.d("TAGGG", "$currentUserId  $eventOwner")
        if (currentUserId.equals(eventOwnerId)){
            removeEvent.visibility = View.VISIBLE
        }else{
            removeEvent.visibility = View.GONE
        }

        removeEvent.setOnClickListener {
            removeEvent.visibility = View.GONE
            finish()
        }

    }
    fun joinToEvent(uid: FirebaseUser, eventId:String){

    }
}
