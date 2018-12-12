package com.wavelus.miasteczko.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.wavelus.miasteczko.R
import com.wavelus.miasteczko.R.id.*
import com.wavelus.miasteczko.activities.EventActivity
import com.wavelus.miasteczko.models.MyEvent

class EventsAdapter(option: FirebaseRecyclerOptions<MyEvent>):
    FirebaseRecyclerAdapter<MyEvent,EventsAdapter.ViewHolder>(option)
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsAdapter.ViewHolder {
        return EventsAdapter.ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.events_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: MyEvent) {
        holder.bindItem(model)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var eventName = itemView.findViewById<TextView>(eventNameRow)
        var eventOwner = itemView.findViewById<TextView>(eventOwnerRow)
        var eventStatus = itemView.findViewById<TextView>(eventStatusRow)
        var eventPlace = itemView.findViewById<TextView>(eventPlaceRow)
        var eventStartTime = itemView.findViewById<TextView>(eventStartRow)
        var eventEndTime = itemView.findViewById<TextView>(eventEndRow)

        fun bindItem(event: MyEvent){
            eventName.text = event.event_name
            eventOwner.text = event.event_owner_id
            eventStatus.text = event.event_status
            eventPlace.text = event.event_place_id
//            eventStartTime.text = event.event_date_start
//            eventEndTime.text = event.event_date_end


            itemView.setOnClickListener {
                var myEventIntent = Intent(itemView.context, EventActivity::class.java)
                myEventIntent.putExtra("event_name", event.event_name)
                myEventIntent.putExtra("event_owner_id", event.event_owner_id)
                myEventIntent.putExtra("event_status", event.event_status)
                myEventIntent.putExtra("event_place_id", event.event_place_id)
                myEventIntent.putExtra("event_date_start", event.event_date_start)
                myEventIntent.putExtra("event_date_end", event.event_date_end)
                itemView.context.startActivity(myEventIntent)
            }
        }
    }

}