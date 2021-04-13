package com.example.societyhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.societyhub.databinding.ActivityChairmanBinding
import com.google.firebase.firestore.FirebaseFirestore

class Chairman : AppCompatActivity() {
    lateinit var viewBinding:ActivityChairmanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityChairmanBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.tilMyProfileChairmanSide.setOnClickListener {
            startActivity(Intent(this,ChairmanMyProfile::class.java))
        }
        viewBinding.tilMembersChairmanSide.setOnClickListener {
            startActivity(Intent(this,MemberListChairmanSide::class.java))
        }
        viewBinding.tilMaintenancChairmanSide.setOnClickListener {
            startActivity(Intent(this,MaintenanceListChairmanSide::class.java))
        }
        viewBinding.tilEventsChairmanSide.setOnClickListener {
            startActivity(Intent(this,EventsChairmanSide::class.java))
        }
        viewBinding.tilNoticeChairmanSide.setOnClickListener {
            startActivity(Intent(this,NoticiesChairmanSide::class.java))
        }
    }
}