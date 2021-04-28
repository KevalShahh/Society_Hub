package com.example.societyhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.societyhub.databinding.ActivityMaintenanceNotPaidChairmanSideBinding

class MaintenanceNotPaidChairmanSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityMaintenanceNotPaidChairmanSideBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityMaintenanceNotPaidChairmanSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        viewBinding.chairmanSideMaintenanceNpPaymentDetailsMonth.text=intent.getStringExtra("MaintenanceMonth")
        viewBinding.chairmanSideMaintenanceNpPaymentDetailsAmount.text=intent.getStringExtra("AmountDue")
        viewBinding.chairmanSideMaintenanceNpPaymentDetailsDate.text=intent.getStringExtra("DueDate")
        viewBinding.chairmanSideMaintenanceNpPaymentDetailsLateCharges.text=intent.getStringExtra("LateCharges")
    }
}