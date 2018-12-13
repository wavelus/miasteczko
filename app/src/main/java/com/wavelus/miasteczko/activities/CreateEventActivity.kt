package com.wavelus.miasteczko.activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.wavelus.miasteczko.EventStatus
import com.wavelus.miasteczko.R
import com.wavelus.miasteczko.R.id.snap
import kotlinx.android.synthetic.main.activity_create_event.*
import android.widget.Spinner
import com.wavelus.miasteczko.models.MyTable
import kotlinx.android.synthetic.main.fragment_all_events.*


/**
 *  Klasa odpowiedzialna za dodawania wydarzeń do bazy.
 *  @author Łukasz Wolski
 */
class CreateEventActivity : AppCompatActivity() {
    /** Punkt wejścia pakietu SDK Firebase Authenticaion*/
    var mAuth: FirebaseAuth? = null
    /** Referencja do bazy danych*/
    var mDatabase: FirebaseDatabase? = null

    var eventPlaces = arrayOf("Flankowy Zaułek","Piwna Siedziba")

    var eventPlace = "Place"
    lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_event)

        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance()

        spinner = eventPlaceSpinner

        spinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, eventPlaces)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                eventPlace = "miasteczko"
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                eventPlace = MyTable.getTableId(position)
//                eventPlace = getTableId(position)
            }

        }

        createEventBtn.setOnClickListener {
            /** ProgressBar w celu uniknięcie utworzenia dwóch takich samych wydarzeń w tym samym czasie*/
            createEventProgressBarId.visibility= View.VISIBLE


            var eventName = eventNameEt.text.toString().trim()
//            var eventPlace = eventPlaceEt.text.toString().trim()
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
        var eventUsersDatabaseRef = mDatabase!!.reference.child("users")
        var eventsDatabaseRef = mDatabase!!.reference.child("events")
        var pushedEventsDatabaseRef = mDatabase!!.reference.child("location_events").child(eventPlace).push()
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

        attendant[currentUserId] = currentUserId
//        attendant[currentUserId] = eventUsersDatabaseRef.child("user_id")
        pushedEventsDatabaseRef.setValue(eventObject).addOnCompleteListener {
            task: Task<Void> ->
            if (task.isSuccessful){
                eventsDatabaseRef.child(event_key).setValue(eventObject)
                eventAttendeesDatabaseRef.child(event_key).setValue(attendant)
                eventAttendeesDatabaseRef.child(event_key).addListenerForSingleValueEvent(object: ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(data: DataSnapshot) {
                        Log.d("TAG", data.child("user_id").value.toString())
//                        Log.d("TAG", data.childrenCount.toString())
                        eventUsersDatabaseRef.child(data.child("user_id").value.toString()).addListenerForSingleValueEvent(object: ValueEventListener{
                            override fun onCancelled(p0: DatabaseError) {
                            }

                            override fun onDataChange(p0: DataSnapshot) {
                                Log.d("TAG", p0.child("display_name").value.toString())
                            }

                        })

                    }
                })
                startActivity(Intent(this, MenuActivity::class.java))
                finish()
            }else{
                Toast.makeText(this, "Nie udało się utworzyć wydarzenia", Toast.LENGTH_LONG).show()
            }
        }

    }
}
