package com.example.societyhub

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.societyhub.databinding.ActivityMaintenanceUserSideBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class MaintenanceUserSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityMaintenanceUserSideBinding
    lateinit var firestorerecycleadapter:FirebaseRecyclerAdapter2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityMaintenanceUserSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.tilSelectMonthUserSide.setOnClickListener {
            monthPicker()
        }
        var currentuser= FirebaseAuth.getInstance().currentUser
        var email= currentuser?.email
      //  FirebaseFirestore.getInstance().collection("maintenance").document().collection("sentTo").whereEqualTo("useremail",email)
        query= email?.let { FirebaseFirestore.getInstance().collection("Members").document(it).collection("received maintenance") }!!
        //query= FirebaseFirestore.getInstance().collection("Maintenance").whereEqualTo("useremail",email)
        val rvoptions= FirestoreRecyclerOptions.Builder<MaintenanceModel>().setQuery(query,MaintenanceModel::class.java).build()
        firestorerecycleadapter= FirebaseRecyclerAdapter2(this,rvoptions)
        viewBinding.rvUserMaintenanceList.adapter= firestorerecycleadapter
        viewBinding.rvUserMaintenanceList.layoutManager= LinearLayoutManager(this)
    }

    private fun monthPicker() {
        var c = Calendar.getInstance()
        var year = c.get(Calendar.YEAR)
        var month = c.get(Calendar.MONTH)
        var day = c.get(Calendar.DAY_OF_MONTH)

        var dialog = DatePickerDialog(this,
                AlertDialog.THEME_HOLO_LIGHT,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    val cal = Calendar.getInstance()
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, month)
                    viewBinding.tilSelectMonthUserSide.setText(SimpleDateFormat("MMMM YYYY").format(cal.time).toString())
                    getMaintenanceList(viewBinding.tilSelectMonthUserSide.text.toString())
                }, year, month, day)

        (dialog.datePicker as ViewGroup).findViewById<ViewGroup>(
                Resources.getSystem().getIdentifier("day", "id", "android")
        ).visibility = View.GONE

        dialog.show()

    }

    private fun getMaintenanceList(maintenanceMonth: String) {
        val options: FirestoreRecyclerOptions<MaintenanceModel>
        options = FirestoreRecyclerOptions.Builder<MaintenanceModel>()
                .setQuery(FirebaseFirestore.getInstance()
                        .collection("Maintenance")
                        .orderBy("maintenanceMonth")
                        .startAt(maintenanceMonth)
                        .endAt(maintenanceMonth + "\uf8ff"), MaintenanceModel::class.java)
                .build()
        firebaseRecyclerAdapter = FirebaseRecyclerAdapter2(this, options)
        firebaseRecyclerAdapter.startListening()
        viewBinding.rvUserMaintenanceList.adapter = firebaseRecyclerAdapter
        firebaseRecyclerAdapter.notifyDataSetChanged()
    }

    override fun onStart() {
        super.onStart()
        firestorerecycleadapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        firestorerecycleadapter.stopListening()
    }
}