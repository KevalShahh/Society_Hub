package com.example.societyhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.societyhub.databinding.ActivityEventNotRegisteredChairmanSideBinding

class EventNotRegisteredChairmanSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityEventNotRegisteredChairmanSideBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityEventNotRegisteredChairmanSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        viewBinding.chairmanSideEventNameNotRegistered.text=intent.getStringExtra("eventTitle")
        viewBinding.chairmanSideEventInstructionNotRegistered.text=intent.getStringExtra("eventDescription")
        viewBinding.chairmanSideEventStartDateNotRegistered.text=intent.getStringExtra("eventStartDate")
        viewBinding.chairmanSideEventStartTimeNotRegistered.text=intent.getStringExtra("eventStartTime")
        viewBinding.chairmanSideEventEndDateNotRegistered.text=intent.getStringExtra("eventEndDate")
        viewBinding.chairmanSideEventEndTimeNotRegistered.text=intent.getStringExtra("eventEndTime")
        viewBinding.chairmanSideEventChargesNotRegistered.text=intent.getStringExtra("eventCharges")
        viewBinding.btnAttendEventChairmanSide.setOnClickListener {
//            startActivity(Intent(this,))
        }
    }
}