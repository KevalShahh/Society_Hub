package com.example.societyhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.societyhub.databinding.ActivityChairmanEventDetailsNotRegisteredBinding

class ChairmanEventDetailsNotRegistered : AppCompatActivity() {
    lateinit var viewBinding:ActivityChairmanEventDetailsNotRegisteredBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityChairmanEventDetailsNotRegisteredBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        viewBinding.chairmanEventNameNotRegistered.text=intent.getStringExtra("eventTitle")
        viewBinding.chairmanEventInstructionNotRegistered.text=intent.getStringExtra("eventDescription")
        viewBinding.chairmanEventStartDateNotRegistered.text=intent.getStringExtra("eventStartDate")
        viewBinding.chairmanEventStartTimeNotRegistered.text=intent.getStringExtra("eventStartTime")
        viewBinding.chairmanEventEndDateNotRegistered.text=intent.getStringExtra("eventEndDate")
        viewBinding.chairmanEventEndTimeNotRegistered.text=intent.getStringExtra("eventEndTime")
        viewBinding.chairmanEventChargesNotRegistered.text=intent.getStringExtra("eventCharges")
    }
}