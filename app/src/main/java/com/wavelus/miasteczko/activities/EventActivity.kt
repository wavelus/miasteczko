package com.wavelus.miasteczko.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wavelus.miasteczko.R
import kotlinx.android.synthetic.main.activity_event.*

class EventActivity : AppCompatActivity() {
    var eventName: String?=null
    var eventOwner: String?=null
    var eventStatus: String?=null
    var eventPlace: String?=null
    var eventDateStart: String?=null
    var eventDateEnd: String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        eventName = intent.extras.getString("event_name")
        eventOwner = intent.extras.getString("get_owner_id")
        eventStatus = intent.extras.getString("event_status")
        eventPlace = intent.extras.getString("event_place_id")
        eventDateStart = intent.extras.getString("event_date_start")
        eventDateEnd = intent.extras.getString("event_date_end")

        eventNameTv.text = eventName
        eventOwnerTv.text = "Organizator" + eventOwner.toString()
        eventStatusTv.text = eventStatus
//        eventNumberOfAttendeesTv
        eventPlaceTv.text = eventPlace
        eventDateStartTv.text = eventDateStart
        eventDateEndTv.text = eventDateEnd


    }
}
