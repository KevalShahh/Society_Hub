package com.example.societyhub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class FirebaseRecyclerAdapter(val context: EventsChairmanSide, options: FirestoreRecyclerOptions<EventModel>) : FirestoreRecyclerAdapter<EventModel, EventViewHolder>(options) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.activity_custom_event_chairman,parent,false)
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
    }

}

class EventViewHolder(view: View) : RecyclerView.ViewHolder(view){
    var textview1:TextView=view.findViewById(R.id.chairman_event_name)
    var textview2:TextView=view.findViewById(R.id.event_instruction)
    var textview3:TextView=view.findViewById(R.id.event_start_date)
    var textview4:TextView=view.findViewById(R.id.event_end_date)
    var textview5:TextView=view.findViewById(R.id.event_start_time)
    var textview6:TextView=view.findViewById(R.id.event_end_time)
    var textview7:TextView=view.findViewById(R.id.event_charge)
}
