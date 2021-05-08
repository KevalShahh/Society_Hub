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

class FireStoreRecycleAdapter13(var context: Context, rvoptions: FirestoreRecyclerOptions<UserModel1>) : FirestoreRecyclerAdapter<UserModel1,noticesenttoviewholder>(rvoptions){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): noticesenttoviewholder {
        var view=LayoutInflater.from(context).inflate(R.layout.activity_custom_society_member_admin,parent,false)
        return noticesenttoviewholder(view)
    }

    override fun onBindViewHolder(holder: noticesenttoviewholder, position: Int, model: UserModel1) {
        holder.textView1.text=" "+model.chairmanfname+" "+model.chairmanlname
        holder.textView2.text=" "+model.chairmanhouseno+","+model.flat
        holder.textView3.text=model.chairmanmobile
        holder.textView3.setOnClickListener {
            var intent: Intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:"+model.chairmanmobile))
            context.startActivity(intent)
        }
    }

}

class noticesenttoviewholder(view:View):RecyclerView.ViewHolder(view) {
    var textView1: TextView =view.findViewById(R.id.admin_rv_create_notice_name_et)
    var textView2: TextView =view.findViewById(R.id.admin_rv_create_notice_address_et)
    var textView3: TextView =view.findViewById(R.id.admin_rv_create_notice_phone_et)
}
