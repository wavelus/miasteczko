package com.wavelus.miasteczko.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query

import com.wavelus.miasteczko.R
import com.wavelus.miasteczko.adapters.EventsAdapter
import com.wavelus.miasteczko.models.MyEvent
import kotlinx.android.synthetic.main.fragment_observed_events.*
import kotlinx.android.synthetic.main.fragment_user_events.*

/**
 * Fragment wyświetlający wydarzeniu w parku Czyżyny Politechnika*/
class PolytechnicEvents : Fragment() {

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
    /** Akcja podejmowana podczas tworzenia widoku*/
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        queryEventsFromFlanki = FirebaseDatabase.getInstance().reference.child("politechnika").child("flanki")
        optionsOfFlanki = FirebaseRecyclerOptions.Builder<MyEvent>().setQuery(queryEventsFromFlanki, MyEvent::class.java).setLifecycleOwner(this).build()
        adapterOfFlanki = EventsAdapter(optionsOfFlanki)

        queryEventsFromSport = FirebaseDatabase.getInstance().reference.child("politechnika").child("koszary")
        optionsOfSport = FirebaseRecyclerOptions.Builder<MyEvent>().setQuery(queryEventsFromSport, MyEvent::class.java).setLifecycleOwner(this).build()
        adapterOfSport = EventsAdapter(optionsOfSport)

        queryEventsFromBeerHouse= FirebaseDatabase.getInstance().reference.child("politechnika").child("piwna_siedziba")
        optionsOfBeerHouse= FirebaseRecyclerOptions.Builder<MyEvent>().setQuery(queryEventsFromBeerHouse, MyEvent::class.java).setLifecycleOwner(this).build()
        adapterOfBeerHouse= EventsAdapter(optionsOfBeerHouse)

        queryEventsFromGuitar= FirebaseDatabase.getInstance().reference.child("politechnika").child("gitary")
        optionsOfGuitar= FirebaseRecyclerOptions.Builder<MyEvent>().setQuery(queryEventsFromGuitar, MyEvent::class.java).setLifecycleOwner(this).build()
        adapterOfGuitar= EventsAdapter(optionsOfGuitar)

        return inflater.inflate(R.layout.fragment_user_events, container, false)
    }


    /** Akcja podejmowana po utworzeniu widoku*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        var linearLayoutManagerSport = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        var linearLayoutManagerBeerHouse = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        var linearLayoutManagerGuitar = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

        tablePolyFlankiRecyclerView.setHasFixedSize(true)
        tablePolyFlankiRecyclerView.layoutManager = linearLayoutManager
        tablePolyFlankiRecyclerView.adapter = adapterOfFlanki
        adapterOfFlanki.notifyDataSetChanged()

        tablePolySportRecyclerView.setHasFixedSize(true)
        tablePolySportRecyclerView.layoutManager = linearLayoutManagerSport
        tablePolySportRecyclerView.adapter = adapterOfSport

        tablePolyBeerHouseRecyclerView.setHasFixedSize(true)
        tablePolyBeerHouseRecyclerView.layoutManager = linearLayoutManagerBeerHouse
        tablePolyBeerHouseRecyclerView.adapter = adapterOfBeerHouse

        tablePolyGuitarRecyclerView.setHasFixedSize(true)
        tablePolyGuitarRecyclerView.layoutManager = linearLayoutManagerGuitar
        tablePolyGuitarRecyclerView.adapter = adapterOfGuitar


        tablePolyFlankiBtn.setOnClickListener {
            //            Toast.makeText(context, "Click!",Toast.LENGTH_LONG).show()
            if(tablePolyFlankiRecyclerView.visibility.equals(View.GONE) ){
                tablePolyFlankiRecyclerView.visibility= View.VISIBLE
            }else{
                tablePolyFlankiRecyclerView.visibility= View.GONE
            }
        }

        tablePolySportBtn.setOnClickListener {
            if(tablePolySportRecyclerView.visibility.equals(View.GONE)){
                tablePolySportRecyclerView.visibility = View.VISIBLE
            }else{
                tablePolySportRecyclerView.visibility = View.GONE
            }
        }

        tablePolyBeerHouseBtn.setOnClickListener {
            if(tablePolyBeerHouseRecyclerView.visibility.equals(View.GONE)){
                tablePolyBeerHouseRecyclerView.visibility = View.VISIBLE
            }else{
                tablePolyBeerHouseRecyclerView.visibility = View.GONE
            }
        }

        tablePolyGuitarBtn.setOnClickListener {
            if(tablePolyGuitarRecyclerView.visibility.equals(View.GONE)){
                tablePolyGuitarRecyclerView.visibility = View.VISIBLE
            }else{
                tablePolyGuitarRecyclerView.visibility = View.GONE
            }

        }

    }


}
