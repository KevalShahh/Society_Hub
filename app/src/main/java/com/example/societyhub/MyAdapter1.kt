package com.example.societyhub

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyAdapter1(var context: Context, supportFragmentManager: FragmentManager, var tabCount: Int) : FragmentPagerAdapter(supportFragmentManager) {
    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
       return when(position){
            0->{
                return NoticesSentFragmentChairmanSide()
            }
            1->{
                return NoticesReceivedFragmentChairmanSide()
            }
            else->{
                return null!!
            }
        }
    }

}
