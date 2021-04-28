package com.example.societyhub

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.societyhub.databinding.ActivityCreateEventsChairmanSideBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class CreateEventsChairmanSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityCreateEventsChairmanSideBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityCreateEventsChairmanSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        viewBinding.eventStartDateChairmanSide.setOnClickListener {
            var c= Calendar.getInstance()
            var year=c.get(Calendar.YEAR)
            var month=c.get(Calendar.MONTH)
            var day=c.get(Calendar.DAY_OF_MONTH)

            var dialog= DatePickerDialog(this,
                    AlertDialog.THEME_HOLO_DARK,
                    DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        val cal = Calendar.getInstance()
                        cal.set(Calendar.YEAR, year)
                        cal.set(Calendar.MONTH, month)
                        viewBinding.eventStartDateChairmanSide.setText(SimpleDateFormat("MMMM YYYY").format(cal.time).toString())
                    },year,month,day)
            dialog.show()
        }
        viewBinding.eventEndDateChairmanSide.setOnClickListener {
            var c= Calendar.getInstance()
            var year=c.get(Calendar.YEAR)
            var month=c.get(Calendar.MONTH)
            var day=c.get(Calendar.DAY_OF_MONTH)

            var dialog= DatePickerDialog(this,
                    AlertDialog.THEME_HOLO_DARK,
                    DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        val cal = Calendar.getInstance()
                        cal.set(Calendar.YEAR, year)
                        cal.set(Calendar.MONTH, month)
                        viewBinding.eventEndDateChairmanSide.setText(SimpleDateFormat("MMMM YYYY").format(cal.time).toString())
                    },year,month,day)
            dialog.show()
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