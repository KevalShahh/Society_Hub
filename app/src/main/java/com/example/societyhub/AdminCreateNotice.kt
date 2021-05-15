package com.example.societyhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.societyhub.databinding.ActivityAdminCreateNoticeBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class AdminCreateNotice : AppCompatActivity() {
    lateinit var viewBinding:ActivityAdminCreateNoticeBinding
    lateinit var query: Query
    lateinit var firestorerecyclerAdapter:FireStoreRecycleAdapter3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityAdminCreateNoticeBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        query=FirebaseFirestore.getInstance().collection("Society")
        var rvoptions=FirestoreRecyclerOptions.Builder<UserModel1>().setQuery(query,UserModel1::class.java).build()
        firestorerecyclerAdapter=FireStoreRecycleAdapter3(this,rvoptions)
        viewBinding.rvAdminCreateNotice.adapter=firestorerecyclerAdapter
        viewBinding.rvAdminCreateNotice.layoutManager=LinearLayoutManager(this)

        viewBinding.adminCreateNoticeCheckbox.setOnCheckedChangeListener(object :CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if(isChecked){
                    for(i in 0 until viewBinding.rvAdminCreateNotice.childCount){
                        viewBinding.rvAdminCreateNotice.getChildAt(i).findViewById<CheckBox>(R.id.rv_admin_create_notice_checkbox).isChecked= true
                    }
                }
                else{
                    for(i in 0 until viewBinding.rvAdminCreateNotice.childCount){
                        viewBinding.rvAdminCreateNotice.getChildAt(i).findViewById<CheckBox>(R.id.rv_admin_create_notice_checkbox).isChecked= false
                    }
                }
            }
        })

        viewBinding.adminCreateNoticeSendNotice.setOnClickListener {
            var a=true
            if (viewBinding.adminCreateNoticeTitle.text.isEmpty()){
                a=false
                viewBinding.adminCreateNoticeTitle.error="Enter Title"
            }
            if (viewBinding.adminCreateNoticeDescription.text.isEmpty()){
                a=false
                viewBinding.adminCreateNoticeDescription.error="Enter Description"
            }
            if (firestorerecyclerAdapter.arraylist.isEmpty()){
                a=false
                Toast.makeText(this, "Please Select Atleast One Chairman", Toast.LENGTH_SHORT).show()
            }
            if (a){
            var title = viewBinding.adminCreateNoticeTitle.text.toString()
            var description = viewBinding.adminCreateNoticeDescription.text.toString()
            var timestamp = Timestamp.now()
            firestorerecyclerAdapter.arraylist.forEach {
                var adminNoticeModel = AdminNoticeModel(title, description, it)
                /* FirebaseFirestore.getInstance().collection("Notice").document(it+"_"+timestamp.seconds).set(adminNoticeModel).addOnCompleteListener {
                    if (it.isSuccessful)
                    {
                        Toast.makeText(this, "Notice Sent Successfully", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,AdminNotice::class.java))
                    }
                }*/
                FirebaseFirestore.getInstance().collection("Users").document(it).collection("recieved notice").document(title).set(adminNoticeModel).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Notice Sent Successfully", Toast.LENGTH_SHORT).show()
                    }
                }
                /*  var adminNoticeModel1=AdminNoticeModel(title,description)
                FirebaseFirestore.getInstance().collection("Notice").document(title).set(adminNoticeModel1).addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(this, "notice created ", Toast.LENGTH_SHORT).show()
                    }
                }
                FirebaseFirestore.getInstance().collection("Notice").document(title).collection("sentTo").document(it).set(adminNoticeModel).addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(this, "notice sent ", Toast.LENGTH_SHORT).show()
                    }
                }*/
            }
            var adminNoticeModel1 = AdminNoticeModel(title, description)
            FirebaseFirestore.getInstance().collection("Notice Admin").document(title).set(adminNoticeModel1).addOnCompleteListener {
                if (it.isSuccessful) {
                    startActivity(Intent(this, AdminNotice::class.java))
                }
            }
        }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
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