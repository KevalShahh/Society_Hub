package com.example.societyhub

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*


class MaintenanceModel(
        var MaintenanceMonth: String="",
        var DueDate: String="",
        var Amount: String="",
        var LateCharges: String="",
        var Description1: String="",
        var useremail: String ="",
        var createdAt: Timestamp = Timestamp.now(),
        var paid:String="",
        var paidAt:Timestamp= Timestamp.now(),
        )
{
        fun getCreatedDateFormat(): String {
                return SimpleDateFormat("hh:mm a (dd/MM/yyyy)", Locale.ENGLISH).format(createdAt.toDate())
        }
        fun getPaidDateFormat():String{
                return SimpleDateFormat("hh:mm a (dd/MM/yyyy)",Locale.ENGLISH).format(paidAt.toDate())
        }
}

