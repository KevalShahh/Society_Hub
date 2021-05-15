package com.example.societyhub

import android.content.Intent
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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewBinding.chairmanSideMaintenanceNpPaymentDetailsMonth.text=intent.getStringExtra("MaintenanceMonth")
        viewBinding.chairmanSideMaintenanceNpPaymentDetailsAmount.text=intent.getStringExtra("AmountDue")
        viewBinding.chairmanSideMaintenanceNpPaymentDetailsDate.text=intent.getStringExtra("DueDate")
        viewBinding.chairmanSideMaintenanceNpPaymentDetailsLateCharges.text=intent.getStringExtra("LateCharges")
        viewBinding.btnChairmanSideMaintenanceNpPaymentDetailsPayNow.setOnClickListener {
            var intent=Intent(this,ChairmanMaintenancePaymentActivity::class.java)
            intent.putExtra("amount",viewBinding.chairmanSideMaintenanceNpPaymentDetailsAmount.text)
            intent.putExtra("maintenanceMonth",viewBinding.chairmanSideMaintenanceNpPaymentDetailsMonth.text)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}