package com.example.societyhub

import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.textfield.TextInputLayout
import java.security.Timestamp
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class FireStoreRecycleAdapter4(var context:Context, rvoptions: FirestoreRecyclerOptions<AdminNoticeModel>) : FirestoreRecyclerAdapter<AdminNoticeModel, AdminNoticeViewHolder>(rvoptions) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminNoticeViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.activity_custom_notice,parent,false)
        return AdminNoticeViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdminNoticeViewHolder, position: Int, model: AdminNoticeModel) {
       /* var calendar:Calendar= Calendar.getInstance()*/
        holder.textView.text=model.title
        holder.textView1.text=model.description
        holder.textView2.text= model.getCreatedDateFormat()
        holder.textView3.setOnClickListener {
            var intent=Intent(context,AdminNoticeInformation::class.java)
            intent.putExtra("title1",model.title)
            intent.putExtra("description1",model.description)
            intent.putExtra("createdAt1",model.getCreatedDateFormat())
            context.startActivity(intent)
        }
    }
}
class AdminNoticeViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView){
   var textView:TextView=itemView.findViewById(R.id.admin_custom_notice_title)
   var textView1:TextView=itemView.findViewById(R.id.admin_custom_notice_description)
   var textView2:TextView=itemView.findViewById(R.id.admin_custom_notice_created_at)
    var textView3:TextInputLayout=itemView.findViewById(R.id.til_admin_notice)
}
