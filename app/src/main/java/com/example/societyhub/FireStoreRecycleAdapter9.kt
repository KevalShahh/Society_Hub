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
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class FireStoreRecycleAdapter9(var context: Context, rvoptions: FirestoreRecyclerOptions<UserModel>):FirestoreRecyclerAdapter<UserModel,chairmannoticeviewholder>(rvoptions){
    var arraylist=ArrayList<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): chairmannoticeviewholder {
        var view=LayoutInflater.from(context).inflate(R.layout.activity_custom_member,parent,false)
        return chairmannoticeviewholder(view)
    }

    override fun onBindViewHolder(holder: chairmannoticeviewholder, position: Int, model: UserModel) {
        holder.textView1.text=" "+model.firstName+" "+model.lastName
        holder.textView2.text=" "+model.flatHouseNo+","+model.society
        holder.textView3.text=model.mobile

        holder.checkBox.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if(isChecked){
                    arraylist.add(model.email)
                }
                else{
                    arraylist.remove(model.email)
                }
            }
        })

        holder.textView3.setOnClickListener {
            var intent: Intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:"+model.mobile))
            context.startActivity(intent)
//            makephonecall(model.chairmanmobile)
        }
    }
}

class chairmannoticeviewholder(view:View):RecyclerView.ViewHolder(view) {
    var textView1:TextView=view.findViewById(R.id.rv_create_notice_name_et)
    var textView2:TextView=view.findViewById(R.id.rv_create_notice_address_et)
    var textView3:TextView=view.findViewById(R.id.rv_create_notice_phone_et)
    var checkBox:CheckBox=view.findViewById(R.id.rv_create_notice_checkbox)
}
