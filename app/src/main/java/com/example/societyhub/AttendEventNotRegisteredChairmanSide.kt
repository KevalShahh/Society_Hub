package com.example.societyhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.example.societyhub.databinding.ActivityAttendEventNotRegisteredChairmanSideBinding

class AttendEventNotRegisteredChairmanSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityAttendEventNotRegisteredChairmanSideBinding
     var qnt=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityAttendEventNotRegisteredChairmanSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
        viewBinding.chairmanSideAttendEventPayNowBtn.setOnClickListener {
            var intent=Intent(this,PaymentActivity::class.java)
            var a=viewBinding.chairmanSideTvTotalChargesAttendEventNotRegistered.text.toString()
            Log.d("TAG", "onCreate: "+a)
            intent.putExtra("amount",a)
            intent.putExtra("eventname",viewBinding.chairmanSideAttendEventNameNotRegistered.text.toString())
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}