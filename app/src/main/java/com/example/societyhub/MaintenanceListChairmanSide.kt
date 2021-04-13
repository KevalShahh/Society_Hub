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
import com.example.societyhub.databinding.ActivityMaintenanceListChairmanSideBinding
import java.text.SimpleDateFormat
import java.util.*

class MaintenanceListChairmanSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityMaintenanceListChairmanSideBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityMaintenanceListChairmanSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.fbCreateMaintenance.setOnClickListener {
            startActivity(Intent(this,CreateMaintenanceChairmanSide::class.java))
        }
        viewBinding.tilEdtMonthMaintenance.setOnClickListener {
            monthPicker()
        }
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
                },year,month,day)

        (dialog.datePicker as ViewGroup).findViewById<ViewGroup>(
                Resources.getSystem().getIdentifier("day", "id", "android")
        ).visibility = View.GONE

        dialog.show()
    }
}