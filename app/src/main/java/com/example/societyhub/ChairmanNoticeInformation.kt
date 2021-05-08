package com.example.societyhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.societyhub.databinding.ActivityChairmanNoticeInformationBinding

class ChairmanNoticeInformation : AppCompatActivity() {
    lateinit var viewBinding:ActivityChairmanNoticeInformationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityChairmanNoticeInformationBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.chairmanNoticeTitleSent.text=intent.getStringExtra("title1")
        viewBinding.chairmanNoticeDescriptionSent.text=intent.getStringExtra("description1")
        viewBinding.chairmanNoticeCreatedAtSent.text=intent.getStringExtra("createdAt1")
    }
}