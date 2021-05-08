package com.example.societyhub

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.textfield.TextInputLayout

class FireStoreRecycleAdapter11(var context: Context, rvoptions: FirestoreRecyclerOptions<ChairmanNoticeModel>):FirestoreRecyclerAdapter<ChairmanNoticeModel,ChairmanNoticeSentViewHolder>(rvoptions) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChairmanNoticeSentViewHolder {
      var view=LayoutInflater.from(context).inflate(R.layout.activity_custom_notice,parent,false)
        return ChairmanNoticeSentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChairmanNoticeSentViewHolder, position: Int, model: ChairmanNoticeModel) {
        holder.textView.text=model.title
        holder.textView1.text=model.description
        holder.textView2.text= model.getCreatedDateFormat()
        holder.textView3.setOnClickListener {
            var intent= Intent(context,ChairmanNoticeInformation::class.java)
            intent.putExtra("title1",model.title)
            intent.putExtra("description1",model.description)
            intent.putExtra("createdAt1",model.getCreatedDateFormat())
            context.startActivity(intent)
        }
    }

}

class ChairmanNoticeSentViewHolder(view:View):RecyclerView.ViewHolder(view) {
    var textView: TextView =itemView.findViewById(R.id.admin_custom_notice_title)
    var textView1: TextView =itemView.findViewById(R.id.admin_custom_notice_description)
    var textView2: TextView =itemView.findViewById(R.id.admin_custom_notice_created_at)
    var textView3: TextInputLayout =itemView.findViewById(R.id.til_admin_notice)
}
