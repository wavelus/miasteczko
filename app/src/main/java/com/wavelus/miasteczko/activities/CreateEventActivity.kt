package com.wavelus.miasteczko.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.wavelus.miasteczko.EventStatus
import com.wavelus.miasteczko.R
import kotlinx.android.synthetic.main.activity_create_event.*

/**
 *  Klasa odpowiedzialna za dodawania wydarzeń do bazy.
 *  @author Łukasz Wolski
 */
class CreateEventActivity : AppCompatActivity() {
    /** Punkt wejścia pakietu SDK Firebase Authenticaion*/
    var mAuth: FirebaseAuth? = null
    /** Referencja do bazy danych*/
    var mDatabase: FirebaseDatabase? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_event)

        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance()


        createEventBtn.setOnClickListener {
            /** ProgressBar w celu uniknięcie utworzenia dwóch takich samych wydarzeń w tym samym czasie*/
            createEventProgressBarId.visibility= View.VISIBLE
            var eventName = eventNameEt.text.toString().trim()
            var eventPlace = eventPlaceEt.text.toString().trim()
            var eventDateStart = eventDateStartEt.text.toString().trim()
            var eventDateEnd = eventDateEndEt.text.toString().trim()

            if (!TextUtils.isEmpty(eventName)||!TextUtils.isEmpty(eventPlace)||!TextUtils.isEmpty(eventDateStart)||!TextUtils.isEmpty(eventDateEnd)){
                createEvent(eventName,eventPlace,eventDateStart,eventDateEnd)
            }else{
                Toast.makeText(this,"Tworzenie wydarzenia nie powiodło się", Toast.LENGTH_LONG).show()
            }
            createEventProgressBarId.visibility= View.INVISIBLE
        }

    }

    /**
     * @param eventName:
     * @param eventPlace:
     * @param eventDateStart:
     * @param eventDateEnd:
     *
     */
    private fun createEvent(eventName: String, eventPlace: String, eventDateStart:String, eventDateEnd:String){
        var currentUserId = mAuth!!.currentUser!!.uid
        var pushedEventsDatabaseRef = mDatabase!!.reference.child("events").push()
        var eventAttendeesDatabaseRef = mDatabase!!.reference.child("event_attendees")
        var event_key: String = pushedEventsDatabaseRef.key.toString()
        val attendant = HashMap<String,String>()

        var eventObject = HashMap<String,Any>()
        eventObject.put("event_name", eventName)
        eventObject.put("event_owner_id", currentUserId)
        eventObject.put("event_place_id", eventPlace)
        eventObject.put("event_date_start", eventDateStart)
        eventObject.put("event_date_end", eventDateEnd)
        eventObject.put("event_status", EventStatus.SOON)

        attendant["user_id"] = currentUserId
        pushedEventsDatabaseRef.setValue(eventObject).addOnCompleteListener {
            task: Task<Void> ->
            if (task.isSuccessful){
                eventAttendeesDatabaseRef.child(event_key).setValue(attendant)
                startActivity(Intent(this, MenuActivity::class.java))
                finish()
            }else{
                Toast.makeText(this, "Nie udało się utworzyć wydarzenia", Toast.LENGTH_LONG).show()
            }
        }

    }

}
