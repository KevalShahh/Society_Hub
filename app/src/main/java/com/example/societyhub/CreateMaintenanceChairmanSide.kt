package com.example.societyhub

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.societyhub.databinding.ActivityCreateMaintenanceChairmanSideBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.text.SimpleDateFormat
import java.util.*

class CreateMaintenanceChairmanSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityCreateMaintenanceChairmanSideBinding
    lateinit var query: Query
    lateinit var firestorerecyclerAdapter:FireStoreRecycleAdapter8
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityCreateMaintenanceChairmanSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        query=FirebaseFirestore.getInstance().collection("Members")
        var rvoptions= FirestoreRecyclerOptions.Builder<UserModel>().setQuery(query,UserModel::class.java).build()
        firestorerecyclerAdapter=FireStoreRecycleAdapter8(this,rvoptions)
        viewBinding.rvMemberListMaintenanceChairmanSide.adapter=firestorerecyclerAdapter
        viewBinding.rvMemberListMaintenanceChairmanSide.layoutManager= LinearLayoutManager(this)

        viewBinding.createMaintenanceCheckbox.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if(isChecked){
                    for(i in 0 until viewBinding.rvMemberListMaintenanceChairmanSide.childCount){
                        viewBinding.rvMemberListMaintenanceChairmanSide.getChildAt(i).findViewById<CheckBox>(R.id.rv_create_maintenance_checkbox_chairman_side).isChecked= true
                    }
                }
                else{
                    for(i in 0 until viewBinding.rvMemberListMaintenanceChairmanSide.childCount){
                        viewBinding.rvMemberListMaintenanceChairmanSide.getChildAt(i).findViewById<CheckBox>(R.id.rv_create_maintenance_checkbox_chairman_side).isChecked= false
                    }
                }
            }
        })
        viewBinding.sendMaintenanceChairmanSide.setOnClickListener {
            var month11=viewBinding.maintenanceMonthChairmanSide.text.toString()
            var duedate11=viewBinding.maintenanceDueDateChairmanSide.text.toString()
            var amount11=viewBinding.maintenanceAmountChairmanSide.text.toString()
            var latecharges11=viewBinding.maintenanceLateChargesChairmanSide.text.toString()
            var description111=viewBinding.maintenanceDescriptionChairmanSide.text.toString()
            var timestamp= Timestamp.now()
            firestorerecyclerAdapter.arraylist.forEach{
                var maintenanceModel=MaintenanceModel(month11,duedate11,amount11,latecharges11,description111,it)
                FirebaseFirestore.getInstance().collection("Maintenance").document(it+"_"+timestamp.seconds).set(maintenanceModel).addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(this, "maintenance created", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,MaintenanceListChairmanSide::class.java))
                    }
                }
               /* var maintenanceModel=MaintenanceModel(month11,duedate11,amount11,latecharges11,description111)
                FirebaseFirestore.getInstance().collection("Maintenance").document(month11).set(maintenanceModel).addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(this, "Maintenance created", Toast.LENGTH_SHORT).show()
                    }
                }
                var maintenanceModel1=MaintenanceModel(month11,duedate11,amount11,latecharges11,description111,it)
                FirebaseFirestore.getInstance().collection("Maintenance").document(month11).collection("Sendto").document(it).set(maintenanceModel1).addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(this, "Maintenance created", Toast.LENGTH_SHORT).show()
                    }
                }*/
            }
        }

        viewBinding.maintenanceMonthChairmanSide.setOnClickListener {
            var c= Calendar.getInstance()
            var year=c.get(Calendar.YEAR)
            var month=c.get(Calendar.MONTH)
            var day=c.get(Calendar.DAY_OF_MONTH)

            var dialog= DatePickerDialog(this,
                    AlertDialog.THEME_HOLO_LIGHT,
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
        viewBinding.maintenanceDueDateChairmanSide.setOnClickListener {
            var c= Calendar.getInstance()
            var year=c.get(Calendar.YEAR)
            var month=c.get(Calendar.MONTH)
            var date=c.get(Calendar.DATE)

            var dialog= DatePickerDialog(this,
                    AlertDialog.THEME_HOLO_LIGHT,
                    DatePickerDialog.OnDateSetListener { view, year, month, date ->
                        val cal = Calendar.getInstance()
                        cal.set(Calendar.DATE,date)
                        cal.set(Calendar.YEAR, year)
                        cal.set(Calendar.MONTH, month)
                        viewBinding.maintenanceDueDateChairmanSide.setText(SimpleDateFormat("d/MM/YYYY").format(cal.time).toString())
                    },year,month,date)
            dialog.show()
        }
    }
    override fun onStart() {
        super.onStart()
        firestorerecyclerAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        firestorerecyclerAdapter.stopListening()
    }
}