package com.wavelus.miasteczko.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.wavelus.miasteczko.fragments.AllEventsFragment
import com.wavelus.miasteczko.fragments.AGHEvents
import com.wavelus.miasteczko.fragments.PolytechnicEvents
/**
 * Klasa odpowiedzialna za zarządzanie fragmentami
 * @see Fragment*/
class SectionPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    /**Zwraca odpowiedni fragment*/
    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> {return AllEventsFragment()}
            1 -> {return AGHEvents()}
            2 -> {return PolytechnicEvents()}
            }
        return null!!

    }
    /**
     * Zwraca liczbę dostępnych fragmentów*/
    override fun getCount(): Int {
        return 3
    }

    /**Zwraca nazwę fragmentu*/
    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0-> return "Wszystkie".capitalize()
            1-> return "Miasteczko AGH".capitalize()
            2-> return "Politechnika".capitalize()
        }
        return null!!
    }
}