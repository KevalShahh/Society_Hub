package com.example.societyhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.societyhub.databinding.ActivityEventRegisteredUserSideBinding

class EventRegisteredUserSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityEventRegisteredUserSideBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityEventRegisteredUserSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewBinding.userSideEventNameRegistered.setText(intent.getStringExtra("eventTitle"))
        viewBinding.userSideEventInstructionRegistered.setText(intent.getStringExtra("eventDescription"))
        viewBinding.userSideEventStartDateRegistered.setText(intent.getStringExtra("eventStartDate"))
        viewBinding.userSideEventStartTimeRegistered.setText(intent.getStringExtra("eventStartTime"))
        viewBinding.userSideEventEndDateRegistered.setText(intent.getStringExtra("eventEndDate"))
        viewBinding.userSideEventEndTimeRegistered.setText(intent.getStringExtra("eventEndTime"))
        viewBinding.userSideEventChargesRegistered.setText(intent.getStringExtra("eventCharges"))
        viewBinding.userSideEventRegisteredDateAndTime.setText(intent.getStringExtra("eventRegisteredAt"))
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}