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
            var EventTitle=viewBinding.eventTitleChairmanSide.text.toString()
            var EventDescription=viewBinding.eventDescriptionChairmanSide.text.toString()
            var EventStartDate=viewBinding.eventStartDateChairmanSide.text.toString()
            var EventStartTime=viewBinding.eventStartTimeChairmanSide.text.toString()
            var EventEndDate=viewBinding.eventEndDateChairmanSide.text.toString()
            var EventEndTime=viewBinding.eventEndTimeChairmanSide.text.toString()
            var EventAmount=viewBinding.eventAmountChairmanSide.text.toString()

            var eventModel=EventModel(EventTitle, EventDescription, EventStartDate, EventStartTime, EventEndDate, EventEndTime, EventAmount)
            FirebaseFirestore.getInstance().collection("Events").document(EventTitle).set(eventModel).addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this, "Event Created", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, EventsChairmanSide::class.java))
                }
            }

        }
    }
}