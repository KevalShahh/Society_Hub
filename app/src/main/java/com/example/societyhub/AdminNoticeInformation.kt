package com.example.societyhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.societyhub.databinding.ActivityAdminNoticeInformationBinding

class AdminNoticeInformation : AppCompatActivity() {
    lateinit var viewBinding:ActivityAdminNoticeInformationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityAdminNoticeInformationBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        viewBinding.adminNoticeTitle.text=intent.getStringExtra("title1")
        viewBinding.adminNoticeDescription.text=intent.getStringExtra("description1")
        viewBinding.adminNoticeCreatedAt.text=intent.getStringExtra("createdAt1")

    }
}