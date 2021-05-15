package com.example.societyhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.example.societyhub.databinding.ActivityAttendEventNotRegisteredChairmanSideBinding
import com.example.societyhub.databinding.ActivityAttendEventNotRegisteredUserSideBinding

class AttendEventNotRegisteredUserSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityAttendEventNotRegisteredUserSideBinding
    var qnt=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityAttendEventNotRegisteredUserSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewBinding.userSideAttendEventNameNotRegistered.text=intent.getStringExtra("eventTitle")
        viewBinding.userSideAttendEventInstructionNotRegistered.text=intent.getStringExtra("eventDescription")
        viewBinding.userSideAttendEventStartDateNotRegistered.text=intent.getStringExtra("eventStartDate")
        viewBinding.userSideAttendEventStartTimeNotRegistered.text=intent.getStringExtra("eventStartTime")
        viewBinding.userSideAttendEventEndDateNotRegistered.text=intent.getStringExtra("eventEndDate")
        viewBinding.userSideAttendEventEndTimeNotRegistered.text=intent.getStringExtra("eventEndTime")
        viewBinding.userSideAttendEventChargesNotRegistered.text=intent.getStringExtra("eventCharges")
        viewBinding.userSideTvTotalChargesAttendEventNotRegistered.text=intent.getStringExtra("eventCharges")

        var c= intent.getStringExtra("eventCharges")?.toInt()
        viewBinding.incrementIntegerUserSide.setOnClickListener {
            qnt=qnt+1
            viewBinding.userSideTvIncDecIntegerAttendEventNotRegistered.text=qnt.toString()
            viewBinding.userSideTvTotalChargesAttendEventNotRegistered.text=(qnt* c!!).toString()
        }
        viewBinding.decrementIntegerUserSide.setOnClickListener {
            if (qnt>1) {
                qnt = qnt - 1
                viewBinding.userSideTvIncDecIntegerAttendEventNotRegistered.text=qnt.toString()
                viewBinding.userSideTvTotalChargesAttendEventNotRegistered.text=(qnt* c!!).toString()
            }
        }
        viewBinding.userSideAttendEventPayNowBtn.setOnClickListener {
            var intent= Intent(this,UserEventPaymentActivity::class.java)
            var a=viewBinding.userSideTvTotalChargesAttendEventNotRegistered.text.toString()
            Log.d("TAG", "onCreate: "+a)
            intent.putExtra("amount",a)
            intent.putExtra("eventname",viewBinding.userSideAttendEventNameNotRegistered.text.toString())
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}