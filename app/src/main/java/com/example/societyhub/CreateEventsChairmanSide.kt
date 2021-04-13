package com.example.societyhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.societyhub.databinding.ActivityCreateEventsChairmanSideBinding
import com.google.firebase.firestore.FirebaseFirestore

class CreateEventsChairmanSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityCreateEventsChairmanSideBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityCreateEventsChairmanSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.btnCreateEvent.setOnClickListener {
            var EventTitle=viewBinding.eventTitleChairmanSide.text.toString()
            var EventDescription=viewBinding.eventDescriptionChairmanSide.text.toString()
            var EventStartDate=viewBinding.eventStartDateChairmanSide.text.toString()
            var EventStartTime=viewBinding.eventStartTimeChairmanSide.text.toString()
            var EventEndDate=viewBinding.eventEndDateChairmanSide.text.toString()
            var EventEndTime=viewBinding.eventEndTimeChairmanSide.text.toString()
            var EventAmount=viewBinding.eventAmountChairmanSide.text.toString()

            var eventModel=EventModel(EventTitle,EventDescription,EventStartDate,EventStartTime,EventEndDate,EventEndTime,EventAmount)
            FirebaseFirestore.getInstance().collection("Events").document(EventTitle).set(eventModel).addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this, "Event Created", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,EventsChairmanSide::class.java))
                }
            }
        }
    }
}