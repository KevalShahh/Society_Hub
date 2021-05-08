package com.example.societyhub

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.societyhub.databinding.ActivityAdminBinding

class Admin : AppCompatActivity()  {
    private lateinit var viewBinding: ActivityAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding=ActivityAdminBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.adminSocieties.setOnClickListener {
            startActivity(Intent(this,Societies::class.java))
        }
        viewBinding.adminChairmen.setOnClickListener {
            startActivity(Intent(this,Chairmen::class.java))
        }
        viewBinding.Notice.setOnClickListener {
            startActivity(Intent(this,AdminNotice::class.java))
        }

    }
}