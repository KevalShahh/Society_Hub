package com.example.societyhub

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.core.View

class FireStoreRecycleAdapter3(var context: Context , rvoptions: FirestoreRecyclerOptions<UserModel1>) : FirestoreRecyclerAdapter<UserModel1, NoticeSendViewHolder>(rvoptions) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeSendViewHolder {
       var view= LayoutInflater.from(context).inflate(R.layout.activity_custom_create_notice_admin,parent,false)
        return NoticeSendViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoticeSendViewHolder, position: Int, model: UserModel1) {
       holder.textView.text=" "+model.chairmanfname+" "+model.chairmanlname
       holder.textView1.text=" "+model.chairmanhouseno+","+model.flat
        holder.textView2.text=model.chairmanmobile
        holder.textView2.setOnClickListener {
            var intent:Intent= Intent(Intent.ACTION_CALL,Uri.parse("tel:"+model.chairmanmobile))
            context.startActivity(intent)
//            makephonecall(model.chairmanmobile)
        }
    }

   /* private fun makephonecall(chairmanmobile: String) :Boolean{
        try {
            var intent= Intent(Intent.ACTION_CALL)
            intent.setData(Uri.parse("tel:$chairmanmobile"))
            context.startActivity(intent)
            return true
        }
        catch (e:Exception){
            e.printStackTrace()
            return false
        }
    }*/
}

class NoticeSendViewHolder(itemView:android.view.View):RecyclerView.ViewHolder(itemView) {
var textView:TextView=itemView.findViewById(R.id.rv_admin_create_notice_name_et)
var textView1:TextView=itemView.findViewById(R.id.rv_admin_create_notice_address_et)
var textView2:TextView=itemView.findViewById(R.id.rv_admin_create_notice_phone_et)
}
