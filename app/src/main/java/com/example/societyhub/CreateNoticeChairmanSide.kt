package com.example.societyhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.societyhub.databinding.ActivityCreateNoticeChairmanSideBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class CreateNoticeChairmanSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityCreateNoticeChairmanSideBinding
    lateinit var query:Query
    lateinit var firestorerecyclerAdapter:FireStoreRecycleAdapter9
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityCreateNoticeChairmanSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        query=FirebaseFirestore.getInstance().collection("Members")
        var rvoptions=FirestoreRecyclerOptions.Builder<UserModel>().setQuery(query,UserModel::class.java).build()
        firestorerecyclerAdapter=FireStoreRecycleAdapter9(this,rvoptions)
        viewBinding.rvChairmanCreateNotice.adapter=firestorerecyclerAdapter
        viewBinding.rvChairmanCreateNotice.layoutManager=LinearLayoutManager(this)

        viewBinding.chairmanCreateNoticeCheckbox.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if(isChecked){
                    for(i in 0 until viewBinding.rvChairmanCreateNotice.childCount){
                        viewBinding.rvChairmanCreateNotice.getChildAt(i).findViewById<CheckBox>(R.id.rv_create_notice_checkbox).isChecked= true
                    }
                }
                else{
                    for(i in 0 until viewBinding.rvChairmanCreateNotice.childCount){
                        viewBinding.rvChairmanCreateNotice.getChildAt(i).findViewById<CheckBox>(R.id.rv_create_notice_checkbox).isChecked= false
                    }
                }
            }
        })

        viewBinding.chairmanCreateNoticeSendNotice.setOnClickListener {
            var title = viewBinding.chairmanCreateNoticeTitle.text.toString()
            var description = viewBinding.chairmanCreateNoticeDescription.text.toString()
            var timestamp = Timestamp.now()
            firestorerecyclerAdapter.arraylist.forEach {
            var chairmanNoticeModel = ChairmanNoticeModel(title, description, it)
            FirebaseFirestore.getInstance().collection("Notice").document(it+"_"+timestamp.seconds).set(chairmanNoticeModel).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Notice Sent Successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, AdminNotice::class.java))
                }
            }
        }
        }

    }
    override fun onStart() {
        super.onStart()
        firestorerecyclerAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        firestorerecyclerAdapter.stopListening()
    }
}