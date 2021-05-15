package com.example.societyhub

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class EventModel(
         var eventTitle: String="",
         var eventDescription: String="",
         var eventStartDate: String="",
         var eventStartTime: String="",
         var eventEndDate: String="",
         var eventEndTime: String="",
         var eventAmount: String="",
         var paid:String="",
         var registeredAt: Timestamp = Timestamp.now()
){
    fun getCreatedDateFormat(): String {
        return SimpleDateFormat("hh:mm a (dd/MM/yyyy)", Locale.ENGLISH).format(registeredAt.toDate())
    }
}
