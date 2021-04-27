package com.example.societyhub
import android.content.Context
import android.content.Intent
import android.view.*
import android.widget.*
import androidx.core.content.ContextCompat.startActivities
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.societyhub.databinding.ActivityAdminBinding.inflate
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.FirebaseFirestore
import com.google.protobuf.NullValue

class FireStoreRecycleAdapter(val context: Context, options: FirestoreRecyclerOptions<UserModel1>) :FirestoreRecyclerAdapter<UserModel1, SocietyViewHolder>(options) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SocietyViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.activity_custom_societies_layout,parent,false)
        return SocietyViewHolder(view)
    }
    override fun onBindViewHolder(holder: SocietyViewHolder, position: Int, model: UserModel1) {
        holder.menu.setOnClickListener {
            val popupMenu=PopupMenu(context,it)

            popupMenu.menuInflater.inflate(R.menu.custom_layout_menu,popupMenu.menu)
            popupMenu.show()
            popupMenu.menu.findItem(R.id.item_unblock).setVisible(false)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.item_update -> {
                        var intent = Intent(context, AdminUpdateSociety::class.java)
                        intent.putExtra("houseno", model.houseno)
                        intent.putExtra("flat", model.flat)
                        intent.putExtra("area", model.area)
                        intent.putExtra("city", model.city)
                        intent.putExtra("state", model.state)
                        intent.putExtra("country", model.country)
                        intent.putExtra("pincode", model.pincode)
                        context.startActivity(intent)
                    }
                    R.id.item_block->{
                        popupMenu.menu.findItem(R.id.item_update).setVisible(false)
                        popupMenu.menu.findItem(R.id.item_delete).setVisible(false)
                        popupMenu.menu.findItem(R.id.item_block).setVisible(false)
                        popupMenu.menu.findItem(R.id.item_unblock).setVisible(true)
                    }
                    R.id.item_delete->{
                        FirebaseFirestore.getInstance().collection("Society").document(model.flat).delete()
                    }

//                    R.id.item_block -> {
//                        holder.textView7.text = "BLOCKED"
//                    }
//                    R.id.item_block -> {
//                        holder.textView7.text = "BLOCKED"
//                        var block: Boolean = false.also { popupMenu.menu.findItem(R.id.item_block).isVisible = false }
//                        var update: Boolean = false.also { popupMenu.menu.findItem(R.id.item_update).isVisible = false }
//                        var unblock: Boolean = true.also { popupMenu.menu.findItem(R.id.item_unblock).isVisible = true }
//                        var delete: Boolean = true.also { popupMenu.menu.findItem(R.id.item_delete).isVisible = it }
//
////                        holder.item.isVisible = true
////                        holder.item3.isVisible = false
////                        holder.item2.isVisible = false
////                        holder.item4.setVisible(true)
//                    }
                  /*  R.id.item_unblock -> {
                        holder.textView7.text = "ACTIVE"
                        var block: Boolean = true.also { popupMenu.menu.findItem(R.id.item_block).isVisible = it }
                        var update: Boolean = true.also { popupMenu.menu.findItem(R.id.item_update).isVisible = it }
                        var unblock: Boolean = false.also { popupMenu.menu.findItem(R.id.item_unblock).isVisible = it }
//                        holder.item.isVisible = false
//                        holder.item3.isVisible = true
//                        holder.item2.isVisible = true
//                        holder.item4.setVisible(true)
                    }*/
                        //* R.id.item_delete->{
                       /* model.flat = null.toString()
                    model.area = null.toString()
                            model . city =
                        null
                        .toString()
                    model.state = null.toString()
                            model . country =
                        null
                        .toString()
                    model.pincode = null.toString()
                            model . status =
                        null
                        .toString()
                }*/

                }
                true
            })
        }
        holder.textInputLayout.setOnClickListener {
            var intent=Intent(context,Society_Information::class.java)
            intent.putExtra("house",model.houseno)
            intent.putExtra("society_name",model.flat)
            intent.putExtra("address",model.area+","+model.city+","+model.state+","+model.country+"-"+model.pincode)
            intent.putExtra("chairman_name",model.chairmanfname +""+ model.chairmanlname)
            intent.putExtra("chairman_mobile",model.chairmanmobile)
            intent.putExtra("chairman_email",model.chairmanemail)
            context.startActivity(intent)
        }
        holder.textView1.text = model.flat
        holder.textView2.text = model.area
        holder.textView3.text = model.city
        holder.textView4.text = model.state
        holder.textView5.text = model.country
        holder.textView6.text = model.pincode
        holder.textView7.text=model.status
    }
}
class SocietyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var textInputLayout: TextInputLayout=itemView.findViewById(R.id.til1_society)
    var menu : ImageView=itemView.findViewById(R.id.iv_menu)
    var textView1 : TextView =itemView.findViewById(R.id.tv_name)
    var textView2 : TextView=itemView.findViewById(R.id.tv_area)
    var textView3 : TextView=itemView.findViewById(R.id.tv_city)
    var textView4 : TextView=itemView.findViewById(R.id.tv_state)
    var textView5 : TextView=itemView.findViewById(R.id.tv_country)
    var textView6 : TextView=itemView.findViewById(R.id.tv_pincode)
    var textView7 : TextView=itemView.findViewById(R.id.tv_status)
//    var item:MenuItem=itemView.findViewById(R.id.item_unblock)
//    var item2:MenuItem=itemView.findViewById(R.id.item_update)
//    var item3:MenuItem=itemView.findViewById(R.id.item_block)
////    var item4:MenuItem=itemView.findViewById(R.id.item_delete)
}
