package com.wavelus.miasteczko.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.wavelus.miasteczko.fragments.AllEventsFragment
import com.wavelus.miasteczko.fragments.ObservedEventsFragment
import com.wavelus.miasteczko.fragments.UserEventsFragment

class SectionPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> {return AllEventsFragment()}
            1 -> {return UserEventsFragment()}
            2 -> {return ObservedEventsFragment()}
            }
        return null!!

    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0-> return "Wszystkie".capitalize()
            1-> return "Utworzone".capitalize()
            2-> return "Obserwowane".capitalize()
        }
        return null!!
    }
}