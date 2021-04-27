package com.example.societyhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class ViewChairmanPaymentInfo : AppCompatActivity() {
   lateinit var tabLayout: TabLayout
   lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_chairman_payment_info)
        tabLayout=findViewById(R.id.tablayout_viewchairman)
        viewPager=findViewById(R.id.viewpager_viewchairman)
        tabLayout.addTab(tabLayout.newTab().setText("Event"))
        tabLayout.addTab(tabLayout.newTab().setText("Maintenance"))
        tabLayout.tabGravity=TabLayout.GRAVITY_FILL
        val adapter=MyAdapter(this,supportFragmentManager,tabLayout.tabCount)
        viewPager.adapter=adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }
}