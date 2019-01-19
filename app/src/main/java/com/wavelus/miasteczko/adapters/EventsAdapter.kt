package com.wavelus.miasteczko.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*
import com.wavelus.miasteczko.R
import com.wavelus.miasteczko.R.id.*
import com.wavelus.miasteczko.activities.EventActivity
import com.wavelus.miasteczko.models.MyEvent
import com.wavelus.miasteczko.models.MyTable
/**Klasa wykorzystująca wzorzec Adapter przy użyciu FirebaseRecyclerAdapter. */
class EventsAdapter(option: FirebaseRecyclerOptions<MyEvent>):
    FirebaseRecyclerAdapter<MyEvent,EventsAdapter.ViewHolder>(option) {
    /**Referencja do bazy danych*/
    lateinit var eventUserDatabaseReference: DatabaseReference

    /**Akcja podejmowana podczas tworzenia widoku*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsAdapter.ViewHolder {
        return EventsAdapter.ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.events_row, parent, false))
    }

    /**Podpinanie konkretnych elementów do widoku*/
    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: MyEvent) {
        holder.bindItem(model)
        eventUserDatabaseReference = FirebaseDatabase.getInstance().reference.child("users").child(model.event_owner_id.toString())
        var eventAttendeesDatabaseReference = FirebaseDatabase.getInstance().reference.child("event_attendees").child(model.event_id.toString())
//        Log.d("taggg", model.event_id)

        holder.myEventIntent.putExtra("event_id", model.event_id.toString())
        holder.myEventIntent.putExtra("event_owner_id",model.event_owner_id.toString())
        getDataFromOtherCollections(eventUserDatabaseReference, holder)
        getNumberElementsInCollection(eventAttendeesDatabaseReference, holder)
    }

    /**
     * Funkcja zwraca liczbę elementów w kolekcji wykorzystane do liczenia osób w wydarzeniu*/
    private fun getNumberElementsInCollection(databaseReference: DatabaseReference, holderInOuterFunctionGetData: EventsAdapter.ViewHolder){
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(data: DataSnapshot) {
//                Log.d("taggg", data.childrenCount.toString())
                var numberOfAttendees = data.childrenCount.toString()
//                Log.d("taggg", numberOfAttendees)
                holderInOuterFunctionGetData.myEventIntent.putExtra("event_number_attendees", numberOfAttendees)
            }
        })
    }

    private fun getDataFromOtherCollections(databaseReference: DatabaseReference, holderInOuterFunctionGetData: EventsAdapter.ViewHolder){
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(data: DataSnapshot) {
                var eventOwnerName = data.child("display_name").value.toString()
                holderInOuterFunctionGetData.eventOwner.text = eventOwnerName
                holderInOuterFunctionGetData.myEventIntent.putExtra("event_owner_name", eventOwnerName)
            }
        })
    }

    /**
     * Klasa zagnieżdzona wykorzystana do wykonania RecyclerViewAdapter*/
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /**Zmienna przechowująca informacje o elemencie*/
        var myEventIntent = Intent(itemView.context, EventActivity::class.java)
        /**Zmienna przechowująca odnośnik do elementu "Nazwa wydarzenia" w widoku*/
        var eventName = itemView.findViewById<TextView>(eventNameRow)
        /**Zmienna przechowująca odnośnik do elementu "Nazwa organizatora" w widoku*/
        var eventOwner = itemView.findViewById<TextView>(eventOwnerRow)
        /**Zmienna przechowująca odnośnik do elementu "Status wydarzenia" w widoku*/
        var eventStatus = itemView.findViewById<TextView>(eventStatusRow)
        /**Zmienna przechowująca odnośnik do elementu "Miejsce wydarzenia" w widoku*/
        var eventPlace = itemView.findViewById<TextView>(eventPlaceRow)
        /**Zmienna przechowująca odnośnik do elementu "Czas rozpoczęcia wydarzenia" w widoku*/
        var eventStartTime = itemView.findViewById<TextView>(eventStartRow)
        /**Zmienna przechowująca odnośnik do elementu "Czas zakończenia wydarzenia" w widoku*/
        var eventEndTime = itemView.findViewById<TextView>(eventEndRow)
        /**Zmienna przechowująca odnośnik do elementu "Nazwa miasta" w widoku*/
        var eventPlaceName:String? = null

        /**Podpinanie elementów do widoku*/
        fun bindItem(event: MyEvent){
            eventPlaceName = MyTable.getTableName(event.event_place_id.toString())

            eventName.text = event.event_name
            eventStatus.text = "Wkrótce"
//            eventStatus.text = event.event_status
            eventPlace.text = eventPlaceName
            eventStartTime.text = event.event_date_start
            eventEndTime.text = event.event_date_end

            itemView.setOnClickListener {
                myEventIntent.putExtra("event_name", event.event_name)
                myEventIntent.putExtra("event_status", event.event_status)
                myEventIntent.putExtra("event_place_name", eventPlaceName)
                myEventIntent.putExtra("event_place_id", event.event_place_id)
                myEventIntent.putExtra("event_date_start", event.event_date_start)
                myEventIntent.putExtra("event_date_end", event.event_date_end)
                myEventIntent.putExtra("event_town_name", event.event_town_name)
                itemView.context.startActivity(myEventIntent)
            }
        }
    }
}