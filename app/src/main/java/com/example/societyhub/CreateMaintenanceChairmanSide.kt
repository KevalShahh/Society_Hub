package com.example.societyhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.societyhub.databinding.ActivityCreateMaintenanceChairmanSideBinding

class CreateMaintenanceChairmanSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityCreateMaintenanceChairmanSideBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityCreateMaintenanceChairmanSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)


    }
}