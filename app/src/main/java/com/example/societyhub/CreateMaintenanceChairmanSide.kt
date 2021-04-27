package com.example.societyhub

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.societyhub.databinding.ActivityCreateMaintenanceChairmanSideBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class CreateMaintenanceChairmanSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityCreateMaintenanceChairmanSideBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityCreateMaintenanceChairmanSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.sendMaintenanceChairmanSide.setOnClickListener {
            var month11=viewBinding.maintenanceMonthChairmanSide.text.toString()
            var duedate11=viewBinding.maintenanceDueDateChairmanSide.text.toString()
            var amount11=viewBinding.maintenanceAmountChairmanSide.text.toString()
            var latecharges11=viewBinding.maintenanceLateChargesChairmanSide.text.toString()
            var description111=viewBinding.maintenanceDescriptionChairmanSide.text.toString()

            var maintenanceModel=MaintenanceModel(month11,duedate11,amount11,latecharges11,description111)
            FirebaseFirestore.getInstance().collection("Maintenance").document(month11).set(maintenanceModel).addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this, "Maintenance created", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewBinding.maintenanceMonthChairmanSide.setOnClickListener {
            monthPicker()
        }
    }

    private fun monthPicker() {
       // TODO("Not yet implemented")
        var c= Calendar.getInstance()
        var year=c.get(Calendar.YEAR)
        var month=c.get(Calendar.MONTH)
        var day=c.get(Calendar.DAY_OF_MONTH)

        var dialog= DatePickerDialog(this,
                AlertDialog.THEME_HOLO_DARK,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    val cal = Calendar.getInstance()
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, month)
                    viewBinding.maintenanceMonthChairmanSide.setText(SimpleDateFormat("MMMM YYYY").format(cal.time).toString())
                },year,month,day)

        (dialog.datePicker as ViewGroup).findViewById<ViewGroup>(
                Resources.getSystem().getIdentifier("day", "id", "android")
        ).visibility = View.GONE

        dialog.show()
    }
}