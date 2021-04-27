package com.example.societyhub

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.societyhub.databinding.ActivitySocietyInformationBinding

class Society_Information : AppCompatActivity() {
    lateinit var viewBinding:ActivitySocietyInformationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivitySocietyInformationBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        viewBinding.tvHouseNumberSociety.setText(intent.getStringExtra("house"))
        viewBinding.tvSocietyNameSociety.setText((intent.getStringExtra("society_name")))
        viewBinding.tvAddressSociety.setText(intent.getStringExtra("address"))
        viewBinding.tvNameChairman.setText(intent.getStringExtra("chairman_name"))
        viewBinding.tvMobileChairman.setText(intent.getStringExtra("chairman_mobile"))
        viewBinding.tvEmailChairman.setText(intent.getStringExtra("chairman_email"))
    }
}