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
//    private lateinit var

    private lateinit var queryEventsFromFlanki: Query
    private lateinit var optionsOfFlanki: FirebaseRecyclerOptions<MyEvent>
    private lateinit var adapterOfFlanki: EventsAdapter

    private lateinit var queryEventsFromBeerHouse: Query
    private lateinit var optionsOfBeerHouse: FirebaseRecyclerOptions<MyEvent>
    private lateinit var adapterOfBeerHouse: EventsAdapter

    private lateinit var queryEventsFromSport: Query
    private lateinit var optionsOfSport: FirebaseRecyclerOptions<MyEvent>
    private lateinit var adapterOfSport: EventsAdapter

    private lateinit var queryEventsFromGuitar: Query
    private lateinit var optionsOfGuitar: FirebaseRecyclerOptions<MyEvent>
    private lateinit var adapterOfGuitar: EventsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        queryEventsFromFlanki = FirebaseDatabase.getInstance().reference.child("location_events").child("flanki")
        optionsOfFlanki = FirebaseRecyclerOptions.Builder<MyEvent>().setQuery(queryEventsFromFlanki,MyEvent::class.java).setLifecycleOwner(this).build()
        adapterOfFlanki = EventsAdapter(optionsOfFlanki)

        queryEventsFromSport = FirebaseDatabase.getInstance().reference.child("location_events").child("koszary")
        optionsOfSport = FirebaseRecyclerOptions.Builder<MyEvent>().setQuery(queryEventsFromSport,MyEvent::class.java).setLifecycleOwner(this).build()
        adapterOfSport = EventsAdapter(optionsOfSport)

        queryEventsFromBeerHouse= FirebaseDatabase.getInstance().reference.child("location_events").child("piwna_siedziba")
        optionsOfBeerHouse= FirebaseRecyclerOptions.Builder<MyEvent>().setQuery(queryEventsFromBeerHouse,MyEvent::class.java).setLifecycleOwner(this).build()
        adapterOfBeerHouse= EventsAdapter(optionsOfBeerHouse)

        queryEventsFromGuitar= FirebaseDatabase.getInstance().reference.child("location_events").child("gitary")
        optionsOfGuitar= FirebaseRecyclerOptions.Builder<MyEvent>().setQuery(queryEventsFromGuitar,MyEvent::class.java).setLifecycleOwner(this).build()
        adapterOfGuitar= EventsAdapter(optionsOfGuitar)

        return inflater!!.inflate(R.layout.fragment_all_events, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        var linearLayoutManagerSport = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        var linearLayoutManagerBeerHouse = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        var linearLayoutManagerGuitar = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

        tableFlankiRecyclerView.setHasFixedSize(true)
        tableFlankiRecyclerView.layoutManager = linearLayoutManager
        tableFlankiRecyclerView.adapter = adapterOfFlanki
        adapterOfFlanki.notifyDataSetChanged()

        tableSportRecyclerView.setHasFixedSize(true)
        tableSportRecyclerView.layoutManager = linearLayoutManagerSport
        tableSportRecyclerView.adapter = adapterOfSport

        tableBeerHouseRecyclerView.setHasFixedSize(true)
        tableBeerHouseRecyclerView.layoutManager = linearLayoutManagerBeerHouse
        tableBeerHouseRecyclerView.adapter = adapterOfBeerHouse

        tableGuitarRecyclerView.setHasFixedSize(true)
        tableGuitarRecyclerView.layoutManager = linearLayoutManagerGuitar
        tableGuitarRecyclerView.adapter = adapterOfGuitar


        tableFlankiBtn.setOnClickListener {
//            Toast.makeText(context, "Click!",Toast.LENGTH_LONG).show()
            if(tableFlankiRecyclerView.visibility.equals(View.GONE) ){
                tableFlankiRecyclerView.visibility= View.VISIBLE
            }else{
                tableFlankiRecyclerView.visibility= View.GONE
            }
        }

        tableSportBtn.setOnClickListener {
            if(tableSportRecyclerView.visibility.equals(View.GONE)){
                tableSportRecyclerView.visibility = View.VISIBLE
            }else{
                tableSportRecyclerView.visibility = View.GONE
            }
        }

        tableBeerHouseBtn.setOnClickListener {
            if(tableBeerHouseRecyclerView.visibility.equals(View.GONE)){
                tableBeerHouseRecyclerView.visibility = View.VISIBLE
            }else{
                tableBeerHouseRecyclerView.visibility = View.GONE
            }
        }

        tableGuitarBtn.setOnClickListener {
            if(tableGuitarRecyclerView.visibility.equals(View.GONE)){
                tableGuitarRecyclerView.visibility = View.VISIBLE
            }else{
                tableGuitarRecyclerView.visibility = View.GONE
            }

        }

    }
}
