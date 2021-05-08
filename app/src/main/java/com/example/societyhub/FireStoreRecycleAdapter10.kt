package com.example.societyhub

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.textfield.TextInputLayout

class FireStoreRecycleAdapter10(var context: Context, rvoptions: FirestoreRecyclerOptions<ChairmanNoticeModel>):FirestoreRecyclerAdapter<ChairmanNoticeModel,noticelistusersideviewholder> (rvoptions){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): noticelistusersideviewholder {
        var view=LayoutInflater.from(context).inflate(R.layout.activity_custom_notice,parent,false)
        return noticelistusersideviewholder(view)
    }
    override fun onBindViewHolder(holder: noticelistusersideviewholder, position: Int, model: ChairmanNoticeModel) {

        holder.textview1.text=model.title
        holder.textview2.text=model.description
        holder.textview3.text=model.getCreatedDateFormat()
        holder.textView4.setOnClickListener {
            var intent= Intent(context,ChairmanNoticeInformation::class.java)
            intent.putExtra("title1",model.title)
            intent.putExtra("description1",model.description)
            intent.putExtra("createdAt1",model.getCreatedDateFormat())
            context.startActivity(intent)
        }
    }
}
class noticelistusersideviewholder(view:View):RecyclerView.ViewHolder(view) {
var textview1:TextView=view.findViewById(R.id.admin_custom_notice_title)
var textview2:TextView=view.findViewById(R.id.admin_custom_notice_description)
var textview3:TextView=view.findViewById(R.id.admin_custom_notice_created_at)
    var textView4: TextInputLayout =itemView.findViewById(R.id.til_admin_notice)
}
