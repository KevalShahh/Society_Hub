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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class CreateNoticeChairmanSide : AppCompatActivity() {
    lateinit var viewBinding: ActivityCreateNoticeChairmanSideBinding
    lateinit var query: Query
    lateinit var firestorerecyclerAdapter: FireStoreRecycleAdapter9
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityCreateNoticeChairmanSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        var firebaseUser = FirebaseAuth.getInstance().currentUser
        var userEmail = firebaseUser?.email
        if (userEmail != null) {
            FirebaseFirestore.getInstance().collection("Users").document(userEmail).get().addOnSuccessListener {
                if (it.exists()) {
                    var userModel1 = it.toObject(UserModel1::class.java)
                    var s = userModel1?.flat
                    query = FirebaseFirestore.getInstance().collection("Members").whereEqualTo("society", s)
                    var rvoptions = FirestoreRecyclerOptions.Builder<UserModel>().setQuery(query, UserModel::class.java).build()
                    firestorerecyclerAdapter = FireStoreRecycleAdapter9(this, rvoptions)
                    viewBinding.rvChairmanCreateNotice.adapter = firestorerecyclerAdapter
                    viewBinding.rvChairmanCreateNotice.layoutManager = LinearLayoutManager(this)
                    firestorerecyclerAdapter.startListening()
                    firestorerecyclerAdapter.notifyDataSetChanged()
                }
            }
        }


        viewBinding.chairmanCreateNoticeCheckbox.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked) {
                    for (i in 0 until viewBinding.rvChairmanCreateNotice.childCount) {
                        viewBinding.rvChairmanCreateNotice.getChildAt(i).findViewById<CheckBox>(R.id.rv_create_notice_checkbox).isChecked = true
                    }
                } else {
                    for (i in 0 until viewBinding.rvChairmanCreateNotice.childCount) {
                        viewBinding.rvChairmanCreateNotice.getChildAt(i).findViewById<CheckBox>(R.id.rv_create_notice_checkbox).isChecked = false
                    }
                }
            }
        })

        viewBinding.chairmanCreateNoticeSendNotice.setOnClickListener {
            var a = true
            if (viewBinding.chairmanCreateNoticeTitle.text.isEmpty()) {
                a = false
                viewBinding.chairmanCreateNoticeTitle.error = "Enter Title"
            }
            if (viewBinding.chairmanCreateNoticeDescription.text.isEmpty()) {
                a = false
                viewBinding.chairmanCreateNoticeDescription.error = "Enter Description"
            }
            if (firestorerecyclerAdapter.arraylist.isEmpty()) {
                a = false
                Toast.makeText(this, "Please Select Atleast One User", Toast.LENGTH_SHORT).show()
            }

            if (a) {
                var title = viewBinding.chairmanCreateNoticeTitle.text.toString()
                var description = viewBinding.chairmanCreateNoticeDescription.text.toString()
                var timestamp = Timestamp.now()
                firestorerecyclerAdapter.arraylist.forEach {
                    var chairmanNoticeModel = ChairmanNoticeModel(title, description, it)
                    /* FirebaseFirestore.getInstance().collection("Notice").document(it+"_"+timestamp.seconds).set(chairmanNoticeModel).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Notice Sent Successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, AdminNotice::class.java))
                }
            }*/
                    FirebaseFirestore.getInstance().collection("Members").document(it).collection("received notice").document(title).set(chairmanNoticeModel).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(this, "Notice Sent Successfully", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                var chairmanNoticeModel1 = userEmail?.let { it1 -> ChairmanNoticeModel(title, description, it1) }
                if (chairmanNoticeModel1 != null) {
                    FirebaseFirestore.getInstance().collection("Notice Chairman").document(title).set(chairmanNoticeModel1).addOnCompleteListener {
                        if (it.isSuccessful) {
                            startActivity(Intent(this, NoticiesChairmanSide::class.java))
                        }
                    }
                }
            }

        }

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}