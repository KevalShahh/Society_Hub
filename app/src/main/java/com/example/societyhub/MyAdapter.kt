package com.example.societyhub

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter

@Suppress("DEPRECATION")
class MyAdapter(val context: Context, supportFragmentManager: FragmentManager, var tabCount: Int) : FragmentPagerAdapter(supportFragmentManager) {
    override fun getCount(): Int {
//        TODO("Not yet implemented")
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
//        TODO("Not yet implemented")
        when(position){
            0->{
                return EventFragment()
            }
            1->{
                return MaintenanceFragment()
            }
            else->return EventFragment()
        }
    }
}
