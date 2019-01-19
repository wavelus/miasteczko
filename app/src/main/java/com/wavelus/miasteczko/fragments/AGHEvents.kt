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
import kotlinx.android.synthetic.main.fragment_all_events.*
import kotlinx.android.synthetic.main.fragment_observed_events.*

/**@see SectionPagerAdapter
 * Fragment wyświetlający wydarzeniu na Miasteczku AGH*/
class AGHEvents : Fragment() {
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

        queryEventsFromFlanki = FirebaseDatabase.getInstance().reference.child("town_agh").child("flanki")
        optionsOfFlanki = FirebaseRecyclerOptions.Builder<MyEvent>().setQuery(queryEventsFromFlanki, MyEvent::class.java).setLifecycleOwner(this).build()
        adapterOfFlanki = EventsAdapter(optionsOfFlanki)

        queryEventsFromSport = FirebaseDatabase.getInstance().reference.child("town_agh").child("koszary")
        optionsOfSport = FirebaseRecyclerOptions.Builder<MyEvent>().setQuery(queryEventsFromSport, MyEvent::class.java).setLifecycleOwner(this).build()
        adapterOfSport = EventsAdapter(optionsOfSport)

        queryEventsFromBeerHouse= FirebaseDatabase.getInstance().reference.child("town_agh").child("piwna_siedziba")
        optionsOfBeerHouse= FirebaseRecyclerOptions.Builder<MyEvent>().setQuery(queryEventsFromBeerHouse, MyEvent::class.java).setLifecycleOwner(this).build()
        adapterOfBeerHouse= EventsAdapter(optionsOfBeerHouse)

        queryEventsFromGuitar= FirebaseDatabase.getInstance().reference.child("town_agh").child("gitary")
        optionsOfGuitar= FirebaseRecyclerOptions.Builder<MyEvent>().setQuery(queryEventsFromGuitar, MyEvent::class.java).setLifecycleOwner(this).build()
        adapterOfGuitar= EventsAdapter(optionsOfGuitar)

        return inflater!!.inflate(R.layout.fragment_observed_events, container, false)
    }


    /** Akcja podejmowana po utworzeniu widoku*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        var linearLayoutManagerSport = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        var linearLayoutManagerBeerHouse = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        var linearLayoutManagerGuitar = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

        tableAGHFlankiRecyclerView.setHasFixedSize(true)
        tableAGHFlankiRecyclerView.layoutManager = linearLayoutManager
        tableAGHFlankiRecyclerView.adapter = adapterOfFlanki
        adapterOfFlanki.notifyDataSetChanged()

        tableAGHSportRecyclerView.setHasFixedSize(true)
        tableAGHSportRecyclerView.layoutManager = linearLayoutManagerSport
        tableAGHSportRecyclerView.adapter = adapterOfSport

        tableAGHBeerHouseRecyclerView.setHasFixedSize(true)
        tableAGHBeerHouseRecyclerView.layoutManager = linearLayoutManagerBeerHouse
        tableAGHBeerHouseRecyclerView.adapter = adapterOfBeerHouse

        tableAGHGuitarRecyclerView.setHasFixedSize(true)
        tableAGHGuitarRecyclerView.layoutManager = linearLayoutManagerGuitar
        tableAGHGuitarRecyclerView.adapter = adapterOfGuitar


        tableAGHFlankiBtn.setOnClickListener {
            //            Toast.makeText(context, "Click!",Toast.LENGTH_LONG).show()
            if(tableAGHFlankiRecyclerView.visibility.equals(View.GONE) ){
                tableAGHFlankiRecyclerView.visibility= View.VISIBLE
            }else{
                tableAGHFlankiRecyclerView.visibility= View.GONE
            }
        }

        tableAGHSportBtn.setOnClickListener {
            if(tableAGHSportRecyclerView.visibility.equals(View.GONE)){
                tableAGHSportRecyclerView.visibility = View.VISIBLE
            }else{
                tableAGHSportRecyclerView.visibility = View.GONE
            }
        }

        tableAGHBeerHouseBtn.setOnClickListener {
            if(tableAGHBeerHouseRecyclerView.visibility.equals(View.GONE)){
                tableAGHBeerHouseRecyclerView.visibility = View.VISIBLE
            }else{
                tableAGHBeerHouseRecyclerView.visibility = View.GONE
            }
        }

        tableAGHGuitarBtn.setOnClickListener {
            if(tableAGHGuitarRecyclerView.visibility.equals(View.GONE)){
                tableAGHGuitarRecyclerView.visibility = View.VISIBLE
            }else{
                tableAGHGuitarRecyclerView.visibility = View.GONE
            }

        }

    }

}
