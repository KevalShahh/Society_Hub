package com.example.societyhub

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class FireStoreRecycleAdapter12(var context: Context, rvoptions: FirestoreRecyclerOptions<UserModel>): FirestoreRecyclerAdapter<UserModel, societymemberviewholder>(rvoptions) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): societymemberviewholder {
    var view=LayoutInflater.from(context).inflate(R.layout.activity_custom_society_member_admin,parent,false)
        return societymemberviewholder((view))
    }

    override fun onBindViewHolder(holder: societymemberviewholder, position: Int, model: UserModel) {
        holder.textView1.text=" "+model.firstName+" "+model.lastName
        holder.textView2.text=" "+model.flatHouseNo+","+model.society
        holder.textView3.text=model.mobile
        holder.textView3.setOnClickListener {
            var intent: Intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:"+model.mobile))
            context.startActivity(intent)
        }
    }

}

class societymemberviewholder (view:View):RecyclerView.ViewHolder(view){
    var textView1:TextView=view.findViewById(R.id.admin_rv_create_notice_name_et)
    var textView2:TextView=view.findViewById(R.id.admin_rv_create_notice_address_et)
    var textView3:TextView=view.findViewById(R.id.admin_rv_create_notice_phone_et)
}
