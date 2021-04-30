package com.example.societyhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.societyhub.databinding.ActivityAttendEventNotRegisteredChairmanSideBinding

class AttendEventNotRegisteredChairmanSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityAttendEventNotRegisteredChairmanSideBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityAttendEventNotRegisteredChairmanSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        viewBinding.chairmanSideAttendEventNameNotRegistered.text=intent.getStringExtra("eventTitle")
        viewBinding.chairmanSideAttendEventInstructionNotRegistered.text=intent.getStringExtra("eventDescription")
        viewBinding.chairmanSideAttendEventStartDateNotRegistered.text=intent.getStringExtra("eventStartDate")
        viewBinding.chairmanSideAttendEventStartTimeNotRegistered.text=intent.getStringExtra("eventStartTime")
        viewBinding.chairmanSideAttendEventEndDateNotRegistered.text=intent.getStringExtra("eventEndDate")
        viewBinding.chairmanSideAttendEventEndTimeNotRegistered.text=intent.getStringExtra("eventEndTime")
        viewBinding.chairmanSideAttendEventChargesNotRegistered.text=intent.getStringExtra("eventCharges")
        viewBinding.chairmanSideTvTotalChargesAttendEventNotRegistered.text=intent.getStringExtra("eventCharges")
    }
}