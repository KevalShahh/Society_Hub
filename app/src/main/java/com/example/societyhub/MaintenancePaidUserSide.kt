package com.example.societyhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.societyhub.databinding.ActivityMaintenancePaidUserSideBinding

class MaintenancePaidUserSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityMaintenancePaidUserSideBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityMaintenancePaidUserSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewBinding.userSideMaintenancePaymentDetailsAmount.text=intent.getStringExtra("AmountDue")
        viewBinding.userSideMaintenancePaymentDetailsMonth.text=intent.getStringExtra("MaintenanceMonth")
        viewBinding.userSideMaintenancePaymentDetailsDate.text=intent.getStringExtra("paidAt")
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}