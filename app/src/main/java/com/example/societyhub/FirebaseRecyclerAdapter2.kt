package com.example.societyhub

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class FirebaseRecyclerAdapter2(var context: Context, rvoptions: FirestoreRecyclerOptions<MaintenanceModel>): FirestoreRecyclerAdapter<MaintenanceModel, MaintenanceViewHolder>(rvoptions) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaintenanceViewHolder {
//        TODO("Not yet implemented")
        val view=LayoutInflater.from(context).inflate(R.layout.activity_custom_maintenance_chairman_details,parent,false)
        return MaintenanceViewHolder(view)
    }

    override fun onBindViewHolder(holder: MaintenanceViewHolder, position: Int, model: MaintenanceModel) {
//        TODO("Not yet implemented")
        holder.textView1.text=model.MaintenanceMonth
        holder.textView2.text=model.DueDate
        holder.textView3.text=model.Amount
        holder.textView4.text=model.getCreatedDateFormat()
    }

}

class MaintenanceViewHolder(view:View):RecyclerView.ViewHolder(view) {
    val textView1:TextView=view.findViewById(R.id.chairman_maintenance_month_year)
    val textView2:TextView=view.findViewById(R.id.chairman_maintenance_due_date)
    val textView3:TextView=view.findViewById(R.id.chairman_maintenance_amount)
    val textView4:TextView=view.findViewById(R.id.chairman_maintenance_requested_date_and_time)
}
