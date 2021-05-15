package com.example.societyhub

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.textfield.TextInputLayout

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
        if (model.paid=="Yes"){
            holder.textView5.text="Paid"
        }else holder.textView5.text="Not Paid"
        holder.textInputLayout.setOnClickListener {
            if (holder.textView5.text=="Not Paid") {
                var intent = Intent(context, MaintenanceNotPaidChairmanSide::class.java)
                intent.putExtra("MaintenanceMonth", model.MaintenanceMonth)
                intent.putExtra("AmountDue", model.Amount)
                intent.putExtra("DueDate", model.DueDate)
                intent.putExtra("LateCharges", model.LateCharges)
                context.startActivity(intent)
            }
            else{
                var intent = Intent(context, MaintenancePaidChairmanSide::class.java)
                intent.putExtra("MaintenanceMonth", model.MaintenanceMonth)
                intent.putExtra("AmountDue", model.Amount)
                intent.putExtra("paidAt",model.getPaidDateFormat())
                context.startActivity(intent)
            }
        }
    }

}

class MaintenanceViewHolder(view:View):RecyclerView.ViewHolder(view) {
    val textView1:TextView=view.findViewById(R.id.chairman_maintenance_month_year)
    val textView2:TextView=view.findViewById(R.id.chairman_maintenance_due_date)
    val textView3:TextView=view.findViewById(R.id.chairman_maintenance_amount)
    val textView4:TextView=view.findViewById(R.id.chairman_maintenance_requested_date_and_time)
    var textInputLayout:TextInputLayout=view.findViewById(R.id.til_custom_maintenance_details_chairman_side)
    var textView5:TextView=view.findViewById(R.id.chairman_maintenance_paid_or_not)
}
