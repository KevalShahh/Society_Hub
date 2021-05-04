package com.example.societyhub

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class ChairmanNoticeModel (
        var title:String="",
        var description:String="",
        var createdAt: Timestamp = Timestamp.now()
) {
    fun getCreatedDateFormat(): String {
        return SimpleDateFormat("hh:mm a (dd/MM/yyyy)", Locale.ENGLISH).format(createdAt.toDate())
    }
}