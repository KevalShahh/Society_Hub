package com.example.societyhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.societyhub.databinding.ActivityEventRegisteredChairmanSideBinding

class EventRegisteredChairmanSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityEventRegisteredChairmanSideBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityEventRegisteredChairmanSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewBinding.chairmanSideEventNameRegistered.setText(intent.getStringExtra("eventTitle"))
        viewBinding.chairmanSideEventInstructionRegistered.setText(intent.getStringExtra("eventDescription"))
        viewBinding.chairmanSideEventStartDateRegistered.setText(intent.getStringExtra("eventStartDate"))
        viewBinding.chairmanSideEventStartTimeRegistered.setText(intent.getStringExtra("eventStartTime"))
        viewBinding.chairmanSideEventEndDateRegistered.setText(intent.getStringExtra("eventEndDate"))
        viewBinding.chairmanSideEventEndTimeRegistered.setText(intent.getStringExtra("eventEndTime"))
        viewBinding.chairmanSideEventChargesRegistered.setText(intent.getStringExtra("eventCharges"))
        viewBinding.chairmanSideEventRegisteredDateAndTime.setText(intent.getStringExtra("eventRegisteredAt"))
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}