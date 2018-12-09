package com.wavelus.miasteczko.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase

import com.wavelus.miasteczko.R
import kotlinx.android.synthetic.main.fragment_all_events.*
import com.google.firebase.database.Query
import com.wavelus.miasteczko.adapters.EventsAdapter
import com.wavelus.miasteczko.models.MyEvent


class AllEventsFragment : Fragment() {
    var queryEventsFromFlanki: Query = FirebaseDatabase.getInstance().reference.child("events")
    var optionsOfFlanki: FirebaseRecyclerOptions<MyEvent> = FirebaseRecyclerOptions.Builder<MyEvent>()
        .setQuery(queryEventsFromFlanki,
            MyEvent::class.java)
        .setLifecycleOwner(this)
        .build()
    var adapterOfFlanki: EventsAdapter = EventsAdapter(optionsOfFlanki)
    var adapterOfPiwnaSiedziba: EventsAdapter = EventsAdapter(optionsOfFlanki)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_all_events, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        var linearLayoutManager2 = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        tableFlankiRecyclerView.setHasFixedSize(true)
        tableFlankiRecyclerView.layoutManager = linearLayoutManager
        tableFlankiRecyclerView.adapter = adapterOfFlanki

        tablePiwnaSiedzibaRecyclerView.setHasFixedSize(true)
        tablePiwnaSiedzibaRecyclerView.layoutManager = linearLayoutManager2
        tablePiwnaSiedzibaRecyclerView.adapter = adapterOfPiwnaSiedziba

        tableFlankiBtn.setOnClickListener {
            Toast.makeText(context, "Click!",Toast.LENGTH_LONG).show()
            if(tableFlankiRecyclerView.visibility.equals(View.GONE) ){
                tableFlankiRecyclerView.visibility= View.VISIBLE
            }else{
                tableFlankiRecyclerView.visibility= View.GONE
            }
        }

        tablePiwnaSiedzibaBtn.setOnClickListener {
            if(tablePiwnaSiedzibaRecyclerView.visibility.equals(View.GONE)){
                tablePiwnaSiedzibaRecyclerView.visibility = View.VISIBLE
            }else{
                tablePiwnaSiedzibaRecyclerView.visibility = View.GONE
            }
        }
    }
}
