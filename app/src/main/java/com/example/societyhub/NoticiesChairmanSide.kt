package com.example.societyhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TableLayout
import com.example.societyhub.databinding.ActivityNoticiesChairmanSideBinding
import com.google.android.material.tabs.TabLayout

class NoticiesChairmanSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityNoticiesChairmanSideBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityNoticiesChairmanSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.fbCreateNoticeChairmanSide.setOnClickListener {
            startActivity(Intent(this,CreateNoticeChairmanSide::class.java))
        }
        viewBinding.tabLayoutNoticeChairmanSide.addTab(viewBinding.tabLayoutNoticeChairmanSide.newTab().setText("Sent"))
        viewBinding.tabLayoutNoticeChairmanSide.addTab(viewBinding.tabLayoutNoticeChairmanSide.newTab().setText("Received"))
        viewBinding.tabLayoutNoticeChairmanSide.tabGravity=TabLayout.GRAVITY_FILL

        val adapter=MyAdapter1(this,supportFragmentManager,viewBinding.tabLayoutNoticeChairmanSide.tabCount)
        viewBinding.viewpagerNoticeChairmanSide.adapter=adapter
    }
}