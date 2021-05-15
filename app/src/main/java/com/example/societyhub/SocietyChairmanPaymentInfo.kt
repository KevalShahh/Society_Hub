package com.example.societyhub

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.societyhub.databinding.ActivitySocietyChairmanPaymentInfoBinding

class SocietyChairmanPaymentInfo : AppCompatActivity() {
    lateinit var viewBinding:ActivitySocietyChairmanPaymentInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivitySocietyChairmanPaymentInfoBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewBinding.tvNameChairmanPaymentinfo.text=intent.getStringExtra("name")
        viewBinding.tvMobileChairmanPaymentinfo.text=intent.getStringExtra("mobile")
        viewBinding.tvEmailChairmanPaymentinfo.text=intent.getStringExtra("email")
        viewBinding.tvTypeChairmanPaymentinfo.text=intent.getStringExtra("type")
        viewBinding.tvNameSocietyInfo.text=intent.getStringExtra("societyname")
        viewBinding.tvAddressSocietyInfo.text=intent.getStringExtra("societyaddress")
        viewBinding.tvViewChairmanPaymentinfo.setOnClickListener {
           var intent=Intent(this,ViewChairmanPaymentInfo::class.java)
            intent.putExtra("email",viewBinding.tvEmailChairmanPaymentinfo.text)
            intent.putExtra("society",viewBinding.tvNameSocietyInfo.text)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}