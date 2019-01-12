package com.wavelus.miasteczko.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.wavelus.miasteczko.R
import com.wavelus.miasteczko.adapters.EventsAdapter
import com.wavelus.miasteczko.models.MyTable
import kotlinx.android.synthetic.main.activity_event.*

/** Aktywność wyświetlająca informacje o wydarzeniu */
class EventActivity : AppCompatActivity() {
    /** Punkt wejścia pakietu SDK Firebase Authenticaion*/
    var mAuth: FirebaseAuth? = null
    /** Referencja do bazy danych*/
    var mDatabase: FirebaseDatabase? = null

    private lateinit var eventAttendeesDatabaseReference: DatabaseReference

    private lateinit var currentUserId: String
    private val attendee = HashMap<String,String>()

    private var eventId: String? = null
    private var eventName: String?=null
    private var eventOwner: String?=null
    private var eventOwnerId: String?=null
    private var eventStatus: String?=null
    private var eventPlace: String?=null
    private var eventDateStart: String?=null
    private var eventDateEnd: String?=null
    private var eventNumberOfAttendees: String?=null

    /** Akcja podejmowana podczas tworzenia aktywności*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        eventId = intent.extras.getString("event_id")
        eventName = intent.extras.getString("event_name")
        eventOwner = intent.extras.getString("event_owner_name")
        eventOwnerId = intent.extras.getString("event_owner_id")
        eventStatus = intent.extras.getString("event_status")
        eventPlace = intent.extras.getString("event_place_name")
        eventDateStart = intent.extras.getString("event_date_start")
        eventDateEnd = intent.extras.getString("event_date_end")
        eventNumberOfAttendees = intent.extras.getString("event_number_attendees")

        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance()
        currentUserId = mAuth!!.currentUser!!.uid
        eventAttendeesDatabaseReference = mDatabase!!.reference.child("event_attendees").child(eventId.toString())
//        attendee[currentUserId] = currentUserId
        attendee.put("user_id", currentUserId)


        eventNameTv.text = eventName
        eventOwnerTv.text = "Organizator: " + eventOwner
        eventStatusTv.text = "Wkrótce"
//        eventStatusTv.text = eventStatus
        eventNumberOfAttendeesTv.text = "Liczba chętnych: " + eventNumberOfAttendees
        eventPlaceTv.text = eventPlace
        eventDateStartTv.text = "Kiedy? " + eventDateStart
        eventDateEndTv.text = eventDateEnd

        eventAttendeesDatabaseReference.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(data: DataSnapshot) {
                var numberOfAttendees: Long = data.childrenCount
                eventNumberOfAttendeesTv.text = numberOfAttendees.toString()
            }

        })

        eventAttendeesDatabaseReference.orderByChild("user_id").equalTo(currentUserId).addListenerForSingleValueEvent(
            object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(data: DataSnapshot) {
                    Log.d("taggg", "${data.childrenCount} ${data.child("user_id").value.toString()} ${data.key}")
                    if (data.childrenCount.toInt() == 0){
                        disJoinToEvent.visibility = View.GONE
                        joinToEvent.visibility = View.VISIBLE
                    }else{
                        joinToEvent.visibility = View.GONE
                        disJoinToEvent.visibility = View.VISIBLE
                    }
                }

            }
        )

        joinToEvent.setOnClickListener {
            joinToEvent(currentUserId, eventAttendeesDatabaseReference)

        }
        disJoinToEvent.setOnClickListener {
            leaveEvent(currentUserId,eventAttendeesDatabaseReference)

        }
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

    /** Funkcja umożliwiająca dołączenie do wydarzenia
     * @param uid: ID użytkownika
     * @param eventAttendeesDatabaseReference: Referencja do bazy danych
     * */

    fun joinToEvent(uid: String, eventAttendeesDatabaseReference: DatabaseReference){
        val attendee = HashMap<String,String>()
        attendee.put("user_id", uid)
        eventAttendeesDatabaseReference.push().setValue(attendee).addOnCompleteListener{
            task: Task<Void> ->
            if(task.isSuccessful){
                joinToEvent.visibility = View.GONE
                disJoinToEvent.visibility = View.VISIBLE
            }
        }
    }

    /** Funkcja umożliwiająca upuszczenie wydarzenia
     * @param uid: ID użytkownika
     * @param eventAttendeesDatabaseReference: Referencja do bazy danych
     * */
    fun leaveEvent(uid: String, eventAttendeesDatabaseReference: DatabaseReference){
        eventAttendeesDatabaseReference.orderByChild("user_id").equalTo(uid).addListenerForSingleValueEvent(
            object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(data: DataSnapshot) {
                    var nodeShot: DataSnapshot = data.children.iterator().next()
                    var key: String = nodeShot.key!!
                    eventAttendeesDatabaseReference.child(key).removeValue().addOnCompleteListener {
                        task: Task<Void> ->
                        if(task.isSuccessful){
                            disJoinToEvent.visibility = View.GONE
                            joinToEvent.visibility = View.VISIBLE
                        }
                    }
                }

            }
        )
    }
}
