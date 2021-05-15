package com.example.societyhub

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyAdapter5(var context: Context?, childFragmentManager: FragmentManager, var tabCount: Int) : FragmentPagerAdapter(childFragmentManager) {
    override fun getItem(position: Int): Fragment {
        when(position){
            0->{
                return HistoryEventFragment()
            }
            1->{
                return HistoryMaintenanceFragment()
            }
            else->{
                return null!!
            }
        }
    }

    override fun getCount(): Int {
      return tabCount
    }

}
