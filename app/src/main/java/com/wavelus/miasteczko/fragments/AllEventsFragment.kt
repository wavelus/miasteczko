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



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        queryEventsFromFlanki = FirebaseDatabase.getInstance().reference.child("location_events").child("flanki")
        optionsOfFlanki = FirebaseRecyclerOptions.Builder<MyEvent>().setQuery(queryEventsFromFlanki,MyEvent::class.java).setLifecycleOwner(this).build()
        adapterOfFlanki = EventsAdapter(optionsOfFlanki)

        queryEventsFromBeerHouse= FirebaseDatabase.getInstance().reference.child("location_events").child("piwna_siedziba")
        optionsOfBeerHouse= FirebaseRecyclerOptions.Builder<MyEvent>().setQuery(queryEventsFromBeerHouse,MyEvent::class.java).setLifecycleOwner(this).build()
        adapterOfBeerHouse= EventsAdapter(optionsOfBeerHouse)

        return inflater!!.inflate(R.layout.fragment_all_events, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        var linearLayoutManagerBeerHouse = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        tableFlankiRecyclerView.setHasFixedSize(true)
        tableFlankiRecyclerView.layoutManager = linearLayoutManager
        tableFlankiRecyclerView.adapter = adapterOfFlanki
        adapterOfFlanki.notifyDataSetChanged()

        tableBeerHouseRecyclerView.setHasFixedSize(true)
        tableBeerHouseRecyclerView.layoutManager = linearLayoutManagerBeerHouse
        tableBeerHouseRecyclerView.adapter = adapterOfBeerHouse


        tableFlankiBtn.setOnClickListener {
            Toast.makeText(context, "Click!",Toast.LENGTH_LONG).show()
            if(tableFlankiRecyclerView.visibility.equals(View.GONE) ){
                tableFlankiRecyclerView.visibility= View.VISIBLE
            }else{
                tableFlankiRecyclerView.visibility= View.GONE
            }
        }

        tableBeerHouseBtn.setOnClickListener {
            if(tableBeerHouseRecyclerView.visibility.equals(View.GONE)){
                tableBeerHouseRecyclerView.visibility = View.VISIBLE
            }else{
                tableBeerHouseRecyclerView.visibility = View.GONE
            }
        }
    }
}
