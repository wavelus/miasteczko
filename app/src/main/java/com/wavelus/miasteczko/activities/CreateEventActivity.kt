package com.wavelus.miasteczko.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.wavelus.miasteczko.EventStatus
import com.wavelus.miasteczko.R
import kotlinx.android.synthetic.main.activity_create_event.*
import com.wavelus.miasteczko.models.MyTable
import java.text.SimpleDateFormat
import java.util.*


/**
 *  Klasa odpowiedzialna za dodawania wydarzeń do bazy.
 *  @author Łukasz Wolski
 */
class CreateEventActivity : AppCompatActivity() {
    /** Punkt wejścia pakietu SDK Firebase Authenticaion*/
    var mAuth: FirebaseAuth? = null
    /** Referencja do bazy danych*/
    var mDatabase: FirebaseDatabase? = null

    private var eventPlaces = arrayOf("Flankowy Zaułek","Sportowe Kosz'ary","Piwna Siedziba","Śpiewające Gitary")
    /** Miejsce wydarzenia wybrane przez użytkownika*/
    var eventPlace = "Place"
    private lateinit var spinner: Spinner
    /** Akcja podejmowana pod utworzeniu aktywności*/
    private  var sdf = SimpleDateFormat("yyy/MM/dd")
    private  var stf = SimpleDateFormat("HH:mm")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_event)

        eventDateStartEt.setText(sdf.format(Date()))
        eventDateEndEt.setText(stf.format(Date()))
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
            }

        }

        createEventBtn.setOnClickListener {
            /** ProgressBar w celu uniknięcie utworzenia dwóch takich samych wydarzeń w tym samym czasie*/
            createEventProgressBarId.visibility= View.VISIBLE

            var eventTownName = intent.extras.getString("townName").toString()

            var eventName = eventNameEt.text.toString().trim()
            var eventDateStart = eventDateStartEt.text.toString().trim()
            var eventDateEnd = eventDateEndEt.text.toString().trim()
            var threeTags: ArrayList<String> = takeThreeTagsFromString(tagsCreateEventEt.text.toString().trim())
            if (!TextUtils.isEmpty(eventName)&&!TextUtils.isEmpty(eventPlace)&&!TextUtils.isEmpty(eventDateStart)&&!TextUtils.isEmpty(eventDateEnd)){
                createEvent(eventName,eventPlace,eventTownName,eventDateStart,eventDateEnd, threeTags)
            }else{
                Toast.makeText(this,"Tworzenie wydarzenia nie powiodło się", Toast.LENGTH_LONG).show()
            }

            createEventProgressBarId.visibility= View.INVISIBLE
        }

    }

    private fun takeThreeTagsFromString(tagString: String): ArrayList<String>{
        var tagStringArray = tagString.trim().split(" ")
        var arrayLenght = tagStringArray.size
        var threeTags: ArrayList<String> = ArrayList()
        for(i in 0..2){
            if(i<arrayLenght){
                threeTags.add(tagStringArray[i])
            }else{
                threeTags.add("")
            }
        }
        return threeTags
    }

    /**
     * @param eventName: Nazwa wydarzenia
     * @param eventPlace: Miejsce wydarzenia
     * @param eventDateStart: Data rozpoczęcia
     * @param eventDateEnd: Data zakończenia
     *
     * Funkcja tworzy obiekt wydarzenie i umieszcza je w bazie danych
     */
    fun createEvent(eventName: String, eventPlace: String, eventTownName: String, eventDateStart:String, eventDateEnd:String, tags:ArrayList<String>){
        var currentUserId = mAuth!!.currentUser!!.uid
        var eventsDatabaseRef = mDatabase!!.reference.child("events")
        var pushedEventsDatabaseRef = mDatabase!!.reference.child("location_events").child(eventPlace).push()
        var eventAttendeesDatabaseRef = mDatabase!!.reference.child("event_attendees")
        var pushedeventsInTownDatabaseRef = mDatabase!!.reference.child(MyTable.getTownId(eventTownName)).child(eventPlace)
        var event_key: String = pushedEventsDatabaseRef.key.toString()
        val attendee = HashMap<String,String>()

        var eventObject = HashMap<String,Any>()
        eventObject.put("event_id", event_key)
        eventObject.put("event_name", eventName)
        eventObject.put("event_owner_id", currentUserId)
        eventObject.put("event_place_id", eventPlace)
        eventObject.put("event_date_start", eventDateStart)
        eventObject.put("event_date_end", eventDateEnd)
        eventObject.put("event_status", EventStatus.SOON)
        eventObject.put("event_town_name", eventTownName)
        eventObject.put("first_tag", tags[0])
        eventObject.put("second_tag", tags[1])
        eventObject.put("third_tag", tags[2])

        attendee.put("user_id", currentUserId)
        pushedEventsDatabaseRef.setValue(eventObject).addOnCompleteListener {
            task: Task<Void> ->
            if (task.isSuccessful){
                pushedeventsInTownDatabaseRef.child(event_key).setValue(eventObject)
                eventsDatabaseRef.child(event_key).setValue(eventObject)
                eventAttendeesDatabaseRef.child(event_key).push().setValue(attendee)
                finish()
            }else{
                Toast.makeText(this, "Nie udało się utworzyć wydarzenia", Toast.LENGTH_LONG).show()
            }
        }

    }
}
