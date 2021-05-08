package com.example.societyhub

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class MaintenanceModel1(
        var MaintenanceMonth: String="",
        var DueDate: String="",
        var Amount: String="",
        var LateCharges: String="",
        var Description1: String="",
        var useremail: ArrayList<String>,
        var createdAt: Timestamp = Timestamp.now()
) {
    fun getCreatedDateFormat(): String {
        return SimpleDateFormat("hh:mm a (dd/MM/yyyy)", Locale.ENGLISH).format(createdAt.toDate())
    }
}
