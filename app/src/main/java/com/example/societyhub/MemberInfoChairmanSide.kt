package com.example.societyhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.societyhub.databinding.ActivityMemberInfoChairmanSideBinding

class MemberInfoChairmanSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityMemberInfoChairmanSideBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityMemberInfoChairmanSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.memberNameChairmanSide.text=intent.getStringExtra("MemberName")
        viewBinding.memberMobileChairmanSide.text=intent.getStringExtra("MemberMobile")
        viewBinding.memberEmailChairmanSide.text=intent.getStringExtra("MemberEmail")
        viewBinding.memberTypeChairmanSide.text="SOCIETY_MEMBER"
        viewBinding.tvViewPaymentinfo.setOnClickListener {
            var intent=Intent(this,ViewMemberPaymentInfo::class.java)
            intent.putExtra("member",viewBinding.memberEmailChairmanSide.text)
            startActivity(intent)
        }
    }
}