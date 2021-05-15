package com.example.societyhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.societyhub.databinding.ActivityMaintenancePaidChairmanSideBinding

class MaintenancePaidChairmanSide : AppCompatActivity() {
    lateinit var viewBindiing:ActivityMaintenancePaidChairmanSideBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBindiing= ActivityMaintenancePaidChairmanSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBindiing.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewBindiing.chairmanSideMaintenancePaymentDetailsAmount.text=intent.getStringExtra("AmountDue")
        viewBindiing.chairmanSideMaintenancePaymentDetailsMonth.text=intent.getStringExtra("MaintenanceMonth")
        viewBindiing.chairmanSideMaintenancePaymentDetailsDate.text=intent.getStringExtra("paidAt")
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}