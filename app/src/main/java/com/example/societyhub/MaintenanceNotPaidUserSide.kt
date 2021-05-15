package com.example.societyhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.societyhub.databinding.ActivityMaintenanceNotPaidUserSideBinding

class MaintenanceNotPaidUserSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityMaintenanceNotPaidUserSideBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityMaintenanceNotPaidUserSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewBinding.userSideMaintenanceNpPaymentDetailsMonth.text=intent.getStringExtra("MaintenanceMonth")
        viewBinding.userSideMaintenanceNpPaymentDetailsAmount.text=intent.getStringExtra("AmountDue")
        viewBinding.userSideMaintenanceNpPaymentDetailsDate1.text=intent.getStringExtra("DueDate")
        viewBinding.userSideMaintenanceNpPaymentDetailsLateCharges.text=intent.getStringExtra("LateCharges")
        viewBinding.btnUserSideMaintenanceNpPaymentDetailsPayNow.setOnClickListener {
            var intent= Intent(this,UserMaintenancePaymentActivity::class.java)
            intent.putExtra("amount",viewBinding.userSideMaintenanceNpPaymentDetailsAmount.text)
            intent.putExtra("maintenanceMonth",viewBinding.userSideMaintenanceNpPaymentDetailsMonth.text)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}