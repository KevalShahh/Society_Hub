package com.example.societyhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.societyhub.databinding.ActivityAttendEventNotRegisteredChairmanSideBinding

class AttendEventNotRegisteredChairmanSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityAttendEventNotRegisteredChairmanSideBinding
     var qnt=1
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

        var c= intent.getStringExtra("eventCharges")?.toInt()
        viewBinding.incrementInteger.setOnClickListener {
            qnt=qnt+1
            viewBinding.chairmanSideTvIncDecIntegerAttendEventNotRegistered.text=qnt.toString()
            viewBinding.chairmanSideTvTotalChargesAttendEventNotRegistered.text=(qnt* c!!).toString()
        }
        viewBinding.decrementInteger.setOnClickListener {
           if (qnt>1) {
               qnt = qnt - 1
               viewBinding.chairmanSideTvIncDecIntegerAttendEventNotRegistered.text=qnt.toString()
               viewBinding.chairmanSideTvTotalChargesAttendEventNotRegistered.text=(qnt* c!!).toString()
           }
        }
    }
}