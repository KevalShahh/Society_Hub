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
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.societyhub.databinding.ActivityMaintenanceListChairmanSideBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.text.SimpleDateFormat
import java.util.*

class MaintenanceListChairmanSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityMaintenanceListChairmanSideBinding
    lateinit var query:Query
    lateinit var firebaseRecyclerAdapter:FirebaseRecyclerAdapter2
    lateinit var searchView:SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityMaintenanceListChairmanSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        query= FirebaseFirestore.getInstance().collection("Maintenance")
        var rvoptions= FirestoreRecyclerOptions.Builder<MaintenanceModel>().setQuery(query,MaintenanceModel::class.java).build()
        firebaseRecyclerAdapter=FirebaseRecyclerAdapter2(this, rvoptions)
        viewBinding.rvChairmanMaintenanceView.adapter=firebaseRecyclerAdapter
        viewBinding.rvChairmanMaintenanceView.layoutManager= LinearLayoutManager(this)

        viewBinding.fbCreateMaintenance.setOnClickListener {
            startActivity(Intent(this,CreateMaintenanceChairmanSide::class.java))
        }
        viewBinding.tilEdtMonthMaintenance.setOnClickListener {
            monthPicker()
        }

    }

    override fun onStart() {
        super.onStart()
        firebaseRecyclerAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        firebaseRecyclerAdapter.stopListening()
    }
    private fun processSearch(newText: String?) {
        val options:FirestoreRecyclerOptions<MaintenanceModel>
        options=FirestoreRecyclerOptions.Builder<MaintenanceModel>().setQuery(FirebaseFirestore.getInstance().collection("Maintenance")
                . orderBy("maintenanceMonth").startAt(newText).endAt(newText+"\uf8ff"),MaintenanceModel::class.java).build()
        firebaseRecyclerAdapter= FirebaseRecyclerAdapter2(this,options)
        firebaseRecyclerAdapter.startListening()
        viewBinding.rvChairmanMaintenanceView.adapter=firebaseRecyclerAdapter
    }

    private fun monthPicker() {
        var c=Calendar.getInstance()
        var year=c.get(Calendar.YEAR)
        var month=c.get(Calendar.MONTH)
        var day=c.get(Calendar.DAY_OF_MONTH)

        var dialog=DatePickerDialog(this,
                AlertDialog.THEME_HOLO_DARK,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    val cal = Calendar.getInstance()
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, month)
                    viewBinding.tilEdtMonthMaintenance.setText(SimpleDateFormat("MMMM YYYY").format(cal.time).toString())
                    
                    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            processSearch(query)
                            return false
                        }
                        override fun onQueryTextChange(newText: String?): Boolean {
                            processSearch(newText)
                            return false
                        }
                    })
                },year,month,day)

        (dialog.datePicker as ViewGroup).findViewById<ViewGroup>(
                Resources.getSystem().getIdentifier("day", "id", "android")
        ).visibility = View.GONE

        dialog.show()

    }
}