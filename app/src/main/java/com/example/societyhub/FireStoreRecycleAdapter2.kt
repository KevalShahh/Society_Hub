package com.example.societyhub

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.textfield.TextInputLayout

class FireStoreRecycleAdapter2(var context: Context, rvoptions: FirestoreRecyclerOptions<UserModel1>): FirestoreRecyclerAdapter<UserModel1, ChairmenViewHolder>(rvoptions) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChairmenViewHolder {
        var view:View=LayoutInflater.from(context).inflate(R.layout.chairmen_custom_layout,parent,false)
        return ChairmenViewHolder(view)
    }
    override fun onBindViewHolder(holder: ChairmenViewHolder, position: Int, model: UserModel1) {
        holder.menu.setOnClickListener {
            var popupMenu=PopupMenu(context,it)
            popupMenu.menuInflater.inflate(R.menu.chairmen_block_menu,popupMenu.menu)
            popupMenu.show()
        }
        holder.textView.text=model.chairmanfname+" "+model.chairmanlname
        holder.textView1.text=model.chairmanemail
        holder.textView2.text=model.chairmanmobile
        holder.textView3.text="CHAIRMAN"
        holder.textInputLayout.setOnClickListener {
            val intent=Intent(context,SocietyChairmanPaymentInfo::class.java)
            intent.putExtra("name",model.chairmanfname+model.chairmanlname)
            intent.putExtra("email",model.chairmanemail)
            intent.putExtra("mobile",model.chairmanmobile)
            intent.putExtra("type","CHAIRMAN")
            intent.putExtra("societyname",model.flat)
            intent.putExtra("societyaddress",model.area+","+model.city+","+model.state+","+model.country+"-"+model.pincode)
            context.startActivity(intent)
        }
    }
}
class ChairmenViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
    var textInputLayout:TextInputLayout=itemView.findViewById(R.id.til_society)
    var menu:ImageView=itemView.findViewById(R.id.iv_admin_chairmen_menu)
    var textView:TextView=itemView.findViewById(R.id.tv_name_chairmen)
    var textView1:TextView=itemView.findViewById(R.id.tv_email_chairmen)
    var textView2:TextView=itemView.findViewById(R.id.tv_mobile_chairmen)
    var textView3:TextView=itemView.findViewById(R.id.tv_type_chairmen)

}
