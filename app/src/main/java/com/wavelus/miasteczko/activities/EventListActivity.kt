package com.wavelus.miasteczko.activities

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.wavelus.miasteczko.R
import com.wavelus.miasteczko.adapters.SectionPagerAdapter
import kotlinx.android.synthetic.main.activity_event_list.*


class EventListActivity : AppCompatActivity() {
//    var mCurrentUser = FirebaseAuth.getInstance().currentUser!!.uid


    override fun onCreate(savedInstanceState: Bundle?) {
        var sectionAdapter: SectionPagerAdapter? = null
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_list)

        supportActionBar!!.title = "Dashboard"

        sectionAdapter = SectionPagerAdapter(supportFragmentManager)
        dashViewPagerId.adapter = sectionAdapter
        mainTabs.setupWithViewPager(dashViewPagerId)
        mainTabs.setTabTextColors(Color.WHITE, Color.GREEN)

    }
}
