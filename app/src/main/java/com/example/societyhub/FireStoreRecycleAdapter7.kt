package com.example.societyhub

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.FirebaseFirestore

class FireStoreRecycleAdapter7(var context: Context, rvoptions: FirestoreRecyclerOptions<UserModel>):FirestoreRecyclerAdapter<UserModel,UserViewHolder>(rvoptions) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        var view=LayoutInflater.from(context).inflate(R.layout.activity_custom_member_chairman_side,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int, model: UserModel) {
        holder.textView1.text=model.firstName+" "+model.lastName
        holder.textView2.text=model.email
        holder.textView3.text=model.mobile
        holder.textView4.text=model.status
        holder.textInputLayout.setOnClickListener {
            var intent=Intent(context,MemberInfoChairmanSide::class.java)
            intent.putExtra("MemberName",holder.textView1.text)
            intent.putExtra("MemberEmail",holder.textView2.text)
            intent.putExtra("MemberMobile",holder.textView3.text)
            context.startActivity(intent)
        }
        holder.imageView.setOnClickListener {
            if (model.status=="pending") {
                val popupMenu = PopupMenu(context, it)
                popupMenu.menuInflater.inflate(R.menu.chairmen_block_menu, popupMenu.menu)
                popupMenu.show()
                popupMenu.menu.findItem(R.id.item_chairmen_unblock).setVisible(false)
                popupMenu.setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.item_chairmen_active->{
                            model.status="active"
                            holder.textView4.text = model.status
                            var map=HashMap<String,String>()
                            map.put("status",model.status)
                            FirebaseFirestore.getInstance().collection("Members").document(model.email).update(map as Map<String,String>)
                        }
                        R.id.item_chairmen_block->{
                            model.status = "blocked"
                            holder.textView4.text = model.status
                            var map=HashMap<String,String>()
                            map.put("status",model.status)
                            FirebaseFirestore.getInstance().collection("Members").document(model.email).update(map as Map<String,String>)
                        }
                    }
                    true
                }
            }
            if (model.status=="active"){
                val popupMenu = PopupMenu(context, it)
                popupMenu.menuInflater.inflate(R.menu.chairmen_block_menu, popupMenu.menu)
                popupMenu.show()
                popupMenu.menu.findItem(R.id.item_chairmen_unblock).setVisible(false)
                popupMenu.menu.findItem(R.id.item_chairmen_active).setVisible(false)
                popupMenu.setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.item_chairmen_block->{
                            model.status = "blocked"
                            holder.textView4.text = model.status
                            var map=HashMap<String,String>()
                            map.put("status",model.status)
                            FirebaseFirestore.getInstance().collection("Members").document(model.email).update(map as Map<String,String>)
                        }
                    }
                    true
                }
            }
            if (model.status=="blocked"){
                val popupMenu = PopupMenu(context, it)
                popupMenu.menuInflater.inflate(R.menu.chairmen_block_menu, popupMenu.menu)
                popupMenu.show()
                popupMenu.menu.findItem(R.id.item_chairmen_unblock).setVisible(false)
                popupMenu.menu.findItem(R.id.item_chairmen_block).setVisible(false)
                popupMenu.setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.item_chairmen_active->{
                            model.status="active"
                            holder.textView4.text = model.status
                            var map=HashMap<String,String>()
                            map.put("status",model.status)
                            FirebaseFirestore.getInstance().collection("Members").document(model.email).update(map as Map<String,String>)
                        }
                    }
                    true
                }
            }
        }
    }

}

class UserViewHolder(view:View):RecyclerView.ViewHolder(view) {
    var textView1:TextView=view.findViewById(R.id.tv_member_name_chairmen)
    var textView2:TextView=view.findViewById(R.id.tv_member_email_chairmen)
    var textView3:TextView=view.findViewById(R.id.tv_member_mobile_chairmen)
    var textView4:TextView=view.findViewById(R.id.tv_member_status_chairmen)
    var imageView:ImageView=view.findViewById(R.id.iv_member_chairmen_menu)
    var textInputLayout:TextInputLayout=view.findViewById(R.id.til_society_member_chairman_side)

}
