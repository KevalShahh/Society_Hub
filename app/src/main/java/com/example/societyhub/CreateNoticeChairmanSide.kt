package com.example.societyhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.societyhub.databinding.ActivityCreateNoticeChairmanSideBinding
import com.google.firebase.firestore.FirebaseFirestore

class CreateNoticeChairmanSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityCreateNoticeChairmanSideBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityCreateNoticeChairmanSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        viewBinding.chairmanCreateNoticeSendNotice.setOnClickListener {
            var title=viewBinding.chairmanCreateNoticeTitle.text.toString()
            var desc=viewBinding.chairmanCreateNoticeDescription.text.toString()
            var model=ChairmanNoticeModel(title,desc)
            FirebaseFirestore.getInstance().collection("NoticeChairman").document(title).set(model).addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this, "Notice Created", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}