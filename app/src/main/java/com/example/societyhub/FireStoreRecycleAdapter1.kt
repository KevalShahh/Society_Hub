package com.example.societyhub

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import java.util.*


class FireStoreRecycleAdapter1(val context: Context, options: FirestoreRecyclerOptions<UserModel1>): FirestoreRecyclerAdapter<UserModel1, SearchViewHolder>(options) {
    lateinit var name:List<String>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.custom_layout_searchview, parent,false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int, model: UserModel1) {
        holder.textView.setText(model.flat)
    }

    fun updateList(temp: ArrayList<String>) {
//        TODO("Not yet implemented")
        this.name = temp
        notifyDataSetChanged()
    }
}

class SearchViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    var textView:TextView=itemView.findViewById(R.id.tv_admin_societies_searchview)

}