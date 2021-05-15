package com.example.societyhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.societyhub.databinding.ActivityEventNotRegisteredUserSideBinding

class EventNotRegisteredUserSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityEventNotRegisteredUserSideBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityEventNotRegisteredUserSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewBinding.userSideEventNameNotRegistered1.text=intent.getStringExtra("eventTitle")
        viewBinding.userSideEventInstructionNotRegistered.text=intent.getStringExtra("eventDescription")
        viewBinding.userSideEventStartDateNotRegistered.text=intent.getStringExtra("eventStartDate")
        viewBinding.userSideEventStartTimeNotRegistered.text=intent.getStringExtra("eventStartTime")
        viewBinding.userSideEventEndDateNotRegistered.text=intent.getStringExtra("eventEndDate")
        viewBinding.userSideEventEndTimeNotRegistered.text=intent.getStringExtra("eventEndTime")
        viewBinding.userSideEventChargesNotRegistered.text=intent.getStringExtra("eventCharges")
        viewBinding.btnAttendEventUserSide.setOnClickListener {
            var intent= Intent(this,AttendEventNotRegisteredUserSide::class.java)
            intent.putExtra("eventTitle",viewBinding.userSideEventNameNotRegistered1.text)
            intent.putExtra("eventDescription",viewBinding.userSideEventInstructionNotRegistered.text)
            intent.putExtra("eventStartDate",viewBinding.userSideEventStartDateNotRegistered.text)
            intent.putExtra("eventStartTime",viewBinding.userSideEventStartTimeNotRegistered.text)
            intent.putExtra("eventEndDate", viewBinding.userSideEventEndDateNotRegistered.text)
            intent.putExtra("eventEndTime", viewBinding.userSideEventEndTimeNotRegistered.text)
            intent.putExtra("eventCharges",viewBinding.userSideEventChargesNotRegistered.text)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}