package com.example.societyhub

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class FirebaseRecyclerAdapter5(var context: Context, options: FirestoreRecyclerOptions<EventModel>) :  FirestoreRecyclerAdapter<EventModel, EventViewHolder>(options) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.activity_custom_event_chairman,parent,false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int, model: EventModel) {
        holder.textview1.text=model.eventTitle
        holder.textview2.text=model.eventDescription
        holder.textview3.text=model.eventStartDate
        holder.textview4.text=model.eventEndDate
        holder.textview5.text=model.eventStartTime
        holder.textview6.text=model.eventEndTime
        holder.textview7.text=model.eventAmount
        if (model.paid=="Yes"){
            holder.textView8.text="Registered"
        }else holder.textView8.text="Not Registered"

        holder.textInputLayout.setOnClickListener {
            if (holder.textView8.text=="Not Registered") {
                var intent = Intent(context, EventNotRegisteredUserSide::class.java)
                intent.putExtra("eventTitle", model.eventTitle)
                intent.putExtra("eventDescription", model.eventDescription)
                intent.putExtra("eventStartDate", model.eventStartDate)
                intent.putExtra("eventStartTime", model.eventStartTime)
                intent.putExtra("eventEndDate", model.eventEndDate)
                intent.putExtra("eventEndTime", model.eventEndTime)
                intent.putExtra("eventCharges", model.eventAmount)
//            intent.putExtra("eventpaymentstatus",holder.textView8.text.toString())
                context.startActivity(intent)
            }
            else{
                var intent = Intent(context, EventRegisteredUserSide::class.java)
                intent.putExtra("eventTitle", model.eventTitle)
                intent.putExtra("eventDescription", model.eventDescription)
                intent.putExtra("eventStartDate", model.eventStartDate)
                intent.putExtra("eventStartTime", model.eventStartTime)
                intent.putExtra("eventEndDate", model.eventEndDate)
                intent.putExtra("eventEndTime", model.eventEndTime)
                intent.putExtra("eventCharges", model.eventAmount)
                intent.putExtra("eventRegisteredAt",model.getCreatedDateFormat())
                context.startActivity(intent)
            }
        }
    }

}
