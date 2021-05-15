package com.example.societyhub

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyAdapter2(var context: Context, supportFragmentManager: FragmentManager, var tabCount: Int) : FragmentPagerAdapter(supportFragmentManager) {
    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0->{
                return MemberEventFragment()
            }
            1->{
                return MemberMaintenanceFragment()
            }
            else->return null!!
        }
    }

}

