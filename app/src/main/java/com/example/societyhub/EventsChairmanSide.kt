package com.example.societyhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.societyhub.databinding.ActivityEventsChairmanSideBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
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

        query=FirebaseFirestore.getInstance().collection("Events")
        var rvoptions=FirestoreRecyclerOptions.Builder<EventModel>().setQuery(query,EventModel::class.java).build()
        firebaseRecyclerAdapter=FirebaseRecyclerAdapter(this, rvoptions)
        
        viewBinding.rvEventsChairmanSide.adapter=firebaseRecyclerAdapter
        viewBinding.rvEventsChairmanSide.layoutManager=LinearLayoutManager(this)
        
        viewBinding.fbCreateEvents.setOnClickListener {
            startActivity(Intent(this,CreateEventsChairmanSide::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        firebaseRecyclerAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        firebaseRecyclerAdapter.stopListening()
    }
}