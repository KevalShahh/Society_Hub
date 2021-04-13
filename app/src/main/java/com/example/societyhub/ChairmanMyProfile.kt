package com.example.societyhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.societyhub.databinding.ActivityChairmanMyProfileBinding

class ChairmanMyProfile : AppCompatActivity() {
    lateinit var viewBinding:ActivityChairmanMyProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityChairmanMyProfileBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.chairmanEditProfile.setOnClickListener {
            startActivity(Intent(this,ChairmanEditProfile::class.java))
        }
    }
}