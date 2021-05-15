package com.example.societyhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.societyhub.databinding.ActivityEventsChairmanSideBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class EventsChairmanSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityEventsChairmanSideBinding
    lateinit var query:Query
    lateinit var firebaseRecyclerAdapter:FirebaseRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityEventsChairmanSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        var fireuser=FirebaseAuth.getInstance().currentUser
        var user= fireuser?.email
        if (user != null) {
            FirebaseFirestore.getInstance().collection("Users").document(user).get().addOnSuccessListener {
                if (it.exists()){
                    var model=it.toObject(UserModel1::class.java)
                    var s= model?.flat
                    query= s?.let { it1 -> FirebaseFirestore.getInstance().collection("Society").document(it1).collection("Events") }!!
                    var rvoptions=FirestoreRecyclerOptions.Builder<EventModel>().setQuery(query,EventModel::class.java).build()
                    firebaseRecyclerAdapter=FirebaseRecyclerAdapter(this, rvoptions)
                    viewBinding.rvEventsChairmanSide.adapter=firebaseRecyclerAdapter
                    viewBinding.rvEventsChairmanSide.layoutManager=LinearLayoutManager(this)
                    firebaseRecyclerAdapter.startListening()
                    firebaseRecyclerAdapter.notifyDataSetChanged()
                }
            }
        }

        
        viewBinding.fbCreateEvents.setOnClickListener {
            startActivity(Intent(this,CreateEventsChairmanSide::class.java))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    /*override fun onStart() {
        super.onStart()
        firebaseRecyclerAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        firebaseRecyclerAdapter.stopListening()
    }*/
}