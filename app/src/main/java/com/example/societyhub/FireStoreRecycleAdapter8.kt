package com.example.societyhub

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class FireStoreRecycleAdapter8(var context: Context, rvoptions: FirestoreRecyclerOptions<UserModel>):FirestoreRecyclerAdapter<UserModel,MaintenanceSendViewHolder>(rvoptions) {

    var arraylist=ArrayList<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaintenanceSendViewHolder {
       var view=LayoutInflater.from(context).inflate(R.layout.activity_custom_create_maintenance,parent,false)
        return MaintenanceSendViewHolder(view)
    }

    override fun onBindViewHolder(holder: MaintenanceSendViewHolder, position: Int, model: UserModel) {
        holder.textView.text=" "+model.firstName+" "+model.lastName
        holder.textView1.text=" "+model.flatHouseNo+","+model.society
        holder.textView2.text=model.mobile

        holder.checkbox.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if(isChecked){
                    arraylist.add(model.email)
                }
                else{
                    arraylist.remove(model.email)
                }
            }
        })

        holder.textView2.setOnClickListener {
            var intent: Intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:"+model.mobile))
            context.startActivity(intent)
//            makephonecall(model.chairmanmobile)
        }
    }

}

class MaintenanceSendViewHolder(view: View):RecyclerView.ViewHolder(view) {
    var textView: TextView =itemView.findViewById(R.id.rv_create_maintenance_name_et)
    var textView1: TextView =itemView.findViewById(R.id.rv_create_maintenance_address_et)
    var textView2: TextView =itemView.findViewById(R.id.rv_create_maintenance_phone_et)
    var checkbox: CheckBox =itemView.findViewById(R.id.rv_create_maintenance_checkbox_chairman_side)
}
