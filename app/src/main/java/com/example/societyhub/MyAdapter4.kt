package com.example.societyhub

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyAdapter4(var context: PendingFragment, fragmentManager: FragmentManager?, var tabCount: Int) : FragmentPagerAdapter(fragmentManager!!) {
    override fun getCount(): Int {
     return tabCount
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0->{
                return UserEventFragment()
            }
            1->{
                return UserMaintenanceFragment()
            }
            else->return null!!
        }
    }

}
