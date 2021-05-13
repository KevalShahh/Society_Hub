package com.example.societyhub

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyAdapter3(var context: PaymentFragment, supportFragmentManager: FragmentManager?, var tabCount: Int) : FragmentPagerAdapter(supportFragmentManager!!) {
    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0->{
                return PendingFragment()
            }
            1->{
                return HistoryFragment()
            }
            else->return null!!
        }
    }

}
