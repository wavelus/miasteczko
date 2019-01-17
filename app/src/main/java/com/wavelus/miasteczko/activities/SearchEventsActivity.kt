package com.wavelus.miasteczko.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*
import com.wavelus.miasteczko.R
import com.wavelus.miasteczko.adapters.EventsAdapter
import com.wavelus.miasteczko.models.MyEvent
import kotlinx.android.synthetic.main.activity_search_events.*

/** Aktywność, w której za pomocą tagu, możemy wyszukać interesujące nas wydarzenie*/
class SearchEventsActivity : AppCompatActivity() {

    private lateinit var queryTagEventInAGH: Query
    private lateinit var optionsOfTagEventAGH: FirebaseRecyclerOptions<MyEvent>
    private lateinit var adapterOfTagEventAGH: EventsAdapter

    private lateinit var queryTagEventInPoly: Query
    private lateinit var optionsOfTagEventPoly: FirebaseRecyclerOptions<MyEvent>
    private lateinit var adapterOfTagEventPoly: EventsAdapter

    /** Akcja podejmowana podczas tworzenia widoku*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_events)

        var linearLayoutManagerAGH = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        var linearLayoutManagerPoly = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        searchByTagBtn.setOnClickListener {
            queryTagEventInAGH = FirebaseDatabase.getInstance().reference.child("events").orderByChild("first_tag").equalTo(searchByTagET.text.trim().split(" ")[0])
            optionsOfTagEventAGH = FirebaseRecyclerOptions.Builder<MyEvent>().setQuery(queryTagEventInAGH,MyEvent::class.java).setLifecycleOwner(this).build()
            adapterOfTagEventAGH = EventsAdapter(optionsOfTagEventAGH)


            searchMiasteczkoRV.setHasFixedSize(true)
            searchMiasteczkoRV.layoutManager = linearLayoutManagerAGH
            searchMiasteczkoRV.adapter = adapterOfTagEventAGH
            adapterOfTagEventAGH.notifyDataSetChanged()

            queryTagEventInPoly = FirebaseDatabase.getInstance().reference.child("events").orderByChild("second_tag").equalTo(searchByTagET.text.trim().split(" ")[0])
            optionsOfTagEventPoly = FirebaseRecyclerOptions.Builder<MyEvent>().setQuery(queryTagEventInPoly,MyEvent::class.java).setLifecycleOwner(this).build()
            adapterOfTagEventPoly = EventsAdapter(optionsOfTagEventPoly)


            searchPoliRV.setHasFixedSize(true)
            searchPoliRV.layoutManager = linearLayoutManagerPoly
            searchPoliRV.adapter = adapterOfTagEventPoly
            adapterOfTagEventPoly.notifyDataSetChanged()
        }

        searchMiasteczkoBtn.setOnClickListener {
            if(searchMiasteczkoRV.visibility.equals(View.GONE) ){
                searchMiasteczkoRV.visibility= View.VISIBLE
            }else{
                searchMiasteczkoRV.visibility= View.GONE
            }
        }
        searchPoliBtn.setOnClickListener {
            if(searchPoliRV.visibility.equals(View.GONE) ){
                searchPoliRV.visibility= View.VISIBLE
            }else{
                searchPoliRV.visibility= View.GONE
            }
        }
    }
}
