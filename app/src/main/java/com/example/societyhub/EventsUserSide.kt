package com.example.societyhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.societyhub.databinding.ActivityEventsUserSideBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class EventsUserSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityEventsUserSideBinding
    lateinit var query: Query
    lateinit var firebaseRecyclerAdapter:FirebaseRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityEventsUserSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        query= FirebaseFirestore.getInstance().collection("Events")
        var rvoptions= FirestoreRecyclerOptions.Builder<EventModel>().setQuery(query,EventModel::class.java).build()
        firebaseRecyclerAdapter=FirebaseRecyclerAdapter(this, rvoptions)
        viewBinding.eventsUserSide.adapter=firebaseRecyclerAdapter
        viewBinding.eventsUserSide.layoutManager=LinearLayoutManager(this)
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