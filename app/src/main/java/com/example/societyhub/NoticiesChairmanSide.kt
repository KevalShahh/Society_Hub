package com.example.societyhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.societyhub.databinding.ActivityNoticiesChairmanSideBinding

class NoticiesChairmanSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityNoticiesChairmanSideBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityNoticiesChairmanSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.fbCreateNoticeChairmanSide.setOnClickListener {
            startActivity(Intent(this,CreateNoticeChairmanSide::class.java))
        }
    }
}