package com.example.societyhub

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class FirebaseRecyclerAdapter4(var context: Context, rvoptions: FirestoreRecyclerOptions<MaintenanceModel>) : FirestoreRecyclerAdapter<MaintenanceModel, MaintenanceViewHolder>(rvoptions) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaintenanceViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.activity_custom_maintenance_chairman_details,parent,false)
        return MaintenanceViewHolder(view)
    }

    override fun onBindViewHolder(holder: MaintenanceViewHolder, position: Int, model: MaintenanceModel) {
        holder.textView1.text=model.MaintenanceMonth
        holder.textView2.text=model.DueDate
        holder.textView3.text=model.Amount
        holder.textView4.text=model.getCreatedDateFormat()
        if (model.paid=="Yes"){
            holder.textView5.text="Paid"
        }else holder.textView5.text="Not Paid"
        holder.textInputLayout.setOnClickListener {
            if (holder.textView5.text=="Not Paid") {
                var intent = Intent(context, MaintenanceNotPaidUserSide::class.java)
                intent.putExtra("MaintenanceMonth", model.MaintenanceMonth)
                intent.putExtra("AmountDue", model.Amount)
                intent.putExtra("DueDate", model.DueDate)
                intent.putExtra("LateCharges", model.LateCharges)
                context.startActivity(intent)
            }
            else{
                var intent = Intent(context, MaintenancePaidUserSide::class.java)
                intent.putExtra("MaintenanceMonth", model.MaintenanceMonth)
                intent.putExtra("AmountDue", model.Amount)
                intent.putExtra("paidAt",model.getPaidDateFormat())
                context.startActivity(intent)
            }
        }
    }

}
