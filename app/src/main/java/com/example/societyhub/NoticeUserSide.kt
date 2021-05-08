package com.example.societyhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.societyhub.databinding.ActivityNoticeUserSideBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class NoticeUserSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityNoticeUserSideBinding
    private lateinit var query: Query
    private lateinit var firestorerecycleadapter:FireStoreRecycleAdapter10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityNoticeUserSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        var currentuser= FirebaseAuth.getInstance().currentUser
        var email= currentuser?.email

      //  query= FirebaseFirestore.getInstance().collection("Notice").whereEqualTo("useremail",email)
        query= email?.let { FirebaseFirestore.getInstance().collection("Members").document(it).collection("received notice") }!!
        val rvoptions= FirestoreRecyclerOptions.Builder<ChairmanNoticeModel>().setQuery(query,ChairmanNoticeModel::class.java).build()
        firestorerecycleadapter= FireStoreRecycleAdapter10(this,rvoptions)
        viewBinding.rvNoticeUserSide.adapter= firestorerecycleadapter
        viewBinding.rvNoticeUserSide.layoutManager= LinearLayoutManager(this)
    }
    override fun onStart() {
        super.onStart()
        firestorerecycleadapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        firestorerecycleadapter.stopListening()
    }
}