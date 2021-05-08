package com.example.societyhub

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.societyhub.databinding.ActivityCreateEventsChairmanSideBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

class CreateEventsChairmanSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityCreateEventsChairmanSideBinding
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityCreateEventsChairmanSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        viewBinding.eventStartDateChairmanSide.setOnClickListener {
            var c= Calendar.getInstance()
            var year=c.get(Calendar.YEAR)
            var month=c.get(Calendar.MONTH)
            var date=c.get(Calendar.DATE)

            var dialog= DatePickerDialog(this,
                    AlertDialog.THEME_HOLO_LIGHT,
                    DatePickerDialog.OnDateSetListener { view, year, month, date ->
                        val cal = Calendar.getInstance()
                        cal.set(Calendar.DATE,date)
                        cal.set(Calendar.YEAR, year)
                        cal.set(Calendar.MONTH, month)
                        viewBinding.eventStartDateChairmanSide.setText(SimpleDateFormat("d MMM YYYY").format(cal.time).toString())
                    },year,month,date)
            dialog.show()
        }
        viewBinding.eventEndDateChairmanSide.setOnClickListener {
            var c= Calendar.getInstance()
            var year=c.get(Calendar.YEAR)
            var month=c.get(Calendar.MONTH)
            var date=c.get(Calendar.DATE)

            var dialog= DatePickerDialog(this,
                    AlertDialog.THEME_HOLO_LIGHT,
                    DatePickerDialog.OnDateSetListener { view, year, month, date ->
                        val cal = Calendar.getInstance()
                        cal.set(Calendar.DATE,date)
                        cal.set(Calendar.YEAR, year)
                        cal.set(Calendar.MONTH, month)
                        viewBinding.eventEndDateChairmanSide.setText(SimpleDateFormat("d MMM YYYY").format(cal.time).toString())
                    },year,month,date)
            dialog.show()
        }
        viewBinding.eventStartTimeChairmanSide.setOnClickListener {
            var t=Calendar.getInstance()
            var hr=t.get(Calendar.HOUR)
            var min=t.get(Calendar.MINUTE)
            var sec=t.get(Calendar.SECOND)
            var timePickerDialog=TimePickerDialog(this,
            TimePickerDialog.OnTimeSetListener { view, hr, min ->
                var time=Calendar.getInstance()
                time.set(Calendar.HOUR,hr)
                time.set(Calendar.MINUTE,min)
                time.set(Calendar.SECOND,sec)
                viewBinding.eventStartTimeChairmanSide.setText(SimpleDateFormat("hh:mm aaa").format(time.time))
            },hr,min,true)
            timePickerDialog.show()
        }
        viewBinding.eventEndTimeChairmanSide.setOnClickListener {
            var t=Calendar.getInstance()
            var hr=t.get(Calendar.HOUR)
            var min=t.get(Calendar.MINUTE)
            var timePickerDialog=TimePickerDialog(this,
                    TimePickerDialog.OnTimeSetListener { view , hr, min ->
                var time=Calendar.getInstance()
                time.set(Calendar.HOUR,hr)
                time.set(Calendar.MINUTE,min)
                viewBinding.eventEndTimeChairmanSide.setText(SimpleDateFormat("hh:mm aaa").format(time.time))
            },hr,min,true)
            timePickerDialog.show()
        }
        viewBinding.btnCreateEvent.setOnClickListener {
            var a=true
            if (viewBinding.eventTitleChairmanSide.text.isEmpty()){
                a=false
                viewBinding.eventTitleChairmanSide.error="Enter Title"
            }
            if (viewBinding.eventDescriptionChairmanSide.text.isEmpty()){
                a=false
                viewBinding.eventDescriptionChairmanSide.error="Enter Description"
            }
            if (viewBinding.eventStartDateChairmanSide.text?.isEmpty() == true){
                a=false
                viewBinding.eventStartDateChairmanSide.error="Select Date"
            }
            if (viewBinding.eventStartTimeChairmanSide.text?.isEmpty() == true){
                a=false
                viewBinding.eventStartTimeChairmanSide.error="Select Time"
            }
            if (viewBinding.eventEndDateChairmanSide.text?.isEmpty() == true){
                a=false
                viewBinding.eventEndDateChairmanSide.error="Select Date"
            }
           /* if (viewBinding.eventStartDateChairmanSide.text>viewBinding.eventEndDateChairmanSide.text){
                a=false
                viewBinding.eventEndDateChairmanSide.error="Select Valid Date"
            }*/
            if (viewBinding.eventEndTimeChairmanSide.text?.isEmpty() == true){
                a=false
                viewBinding.eventEndTimeChairmanSide.error="Select Time"
            }
            if (viewBinding.eventAmountChairmanSide.text.isEmpty()){
                a=false
                viewBinding.eventAmountChairmanSide.error="Enter Amount"
            }
            if (a){
            var EventTitle = viewBinding.eventTitleChairmanSide.text.toString()
            var EventDescription = viewBinding.eventDescriptionChairmanSide.text.toString()
            var EventStartDate = viewBinding.eventStartDateChairmanSide.text.toString()
            var EventStartTime = viewBinding.eventStartTimeChairmanSide.text.toString()
            var EventEndDate = viewBinding.eventEndDateChairmanSide.text.toString()
            var EventEndTime = viewBinding.eventEndTimeChairmanSide.text.toString()
            var EventAmount = viewBinding.eventAmountChairmanSide.text.toString()

            var eventModel = EventModel(EventTitle, EventDescription, EventStartDate, EventStartTime, EventEndDate, EventEndTime, EventAmount)
            /* FirebaseFirestore.getInstance().collection("Events").document(EventTitle).set(eventModel).addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this, "Event Created", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, EventsChairmanSide::class.java))
                }
            }*/
            var fireuser = FirebaseAuth.getInstance().currentUser
            var user = fireuser?.email
            if (user != null) {
                FirebaseFirestore.getInstance().collection("Users").document(user).get().addOnSuccessListener {
                    if (it.exists()) {
                        var model = it.toObject(UserModel1::class.java)
                        var s = model?.flat
                        if (s != null) {
                            FirebaseFirestore.getInstance().collection("Society").document(s).collection("Events").document(EventTitle).set(eventModel).addOnCompleteListener {
                                if (it.isSuccessful) {
                                    Toast.makeText(this, "event created", Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this, EventsChairmanSide::class.java))
                                }
                            }
                        }
                    }
                }
            }

        }
        }
    }
}