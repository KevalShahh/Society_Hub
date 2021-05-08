package com.example.societyhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.societyhub.databinding.ActivityAdminNoticeBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class AdminNotice : AppCompatActivity() {
    lateinit var viewBinding:ActivityAdminNoticeBinding
    lateinit var query: Query
    lateinit var firestorerecyclerAdapter:FireStoreRecycleAdapter4
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityAdminNoticeBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        viewBinding.floatingAddButton.setOnClickListener {
            startActivity(Intent(this,AdminCreateNotice::class.java))
        }
        query= FirebaseFirestore.getInstance().collection("Notice Admin")
        var rvoptions= FirestoreRecyclerOptions.Builder<AdminNoticeModel>().setQuery(query,AdminNoticeModel::class.java).build()
        firestorerecyclerAdapter=FireStoreRecycleAdapter4(this,rvoptions)
        viewBinding.rvAdminNotice.adapter=firestorerecyclerAdapter
        viewBinding.rvAdminNotice.layoutManager= LinearLayoutManager(this)
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