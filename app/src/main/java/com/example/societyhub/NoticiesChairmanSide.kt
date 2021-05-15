package com.example.societyhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.societyhub.databinding.ActivityNoticiesChairmanSideBinding
import com.google.android.material.tabs.TabLayout

class NoticiesChairmanSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityNoticiesChairmanSideBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityNoticiesChairmanSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewBinding.tabLayoutNoticeChairmanSide.addTab(viewBinding.tabLayoutNoticeChairmanSide.newTab().setText("SENT"))
        viewBinding.tabLayoutNoticeChairmanSide.addTab(viewBinding.tabLayoutNoticeChairmanSide.newTab().setText("RECEIVED"))
        viewBinding.tabLayoutNoticeChairmanSide.tabGravity=TabLayout.GRAVITY_FILL

        val adapter=MyAdapter1(this,supportFragmentManager,viewBinding.tabLayoutNoticeChairmanSide.tabCount)
        viewBinding.viewpagerNoticeChairmanSide.adapter=adapter
        viewBinding.viewpagerNoticeChairmanSide.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(viewBinding.tabLayoutNoticeChairmanSide))

        viewBinding.fbCreateNoticeChairmanSide.setOnClickListener {
            startActivity(Intent(this,CreateNoticeChairmanSide::class.java))
        }
        viewBinding.tabLayoutNoticeChairmanSide.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewBinding.viewpagerNoticeChairmanSide.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        viewBinding.fbCreateNoticeChairmanSide.setOnClickListener {
            startActivity(Intent(this,CreateNoticeChairmanSide::class.java))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}