package com.example.societyhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.societyhub.databinding.ActivityEventNotRegisteredUserSide1Binding

class EventNotRegisteredUserSide1 : AppCompatActivity() {
    lateinit var viewBinding:ActivityEventNotRegisteredUserSide1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityEventNotRegisteredUserSide1Binding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewBinding.userSideEventNameNotRegistered2.text=intent.getStringExtra("eventTitle")
        viewBinding.userSideEventInstructionNotRegistered1.text=intent.getStringExtra("eventDescription")
        viewBinding.userSideEventStartDateNotRegistered1.text=intent.getStringExtra("eventStartDate")
        viewBinding.userSideEventStartTimeNotRegistered1.text=intent.getStringExtra("eventStartTime")
        viewBinding.userSideEventEndDateNotRegistered1.text=intent.getStringExtra("eventEndDate")
        viewBinding.userSideEventEndTimeNotRegistered1.text=intent.getStringExtra("eventEndTime")
        viewBinding.userSideEventChargesNotRegistered1.text=intent.getStringExtra("eventCharges")
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}