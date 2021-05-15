package com.example.societyhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.societyhub.databinding.ActivityEventsUserSideBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class EventsUserSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityEventsUserSideBinding
    lateinit var query: Query
    lateinit var firebaseRecyclerAdapter:FirebaseRecyclerAdapter5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityEventsUserSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        var fireuser=FirebaseAuth.getInstance().currentUser
        var user= fireuser?.email
        if (user != null) {
            FirebaseFirestore.getInstance().collection("Members").document(user).get().addOnSuccessListener {
                if (it.exists()){
                    var model=it.toObject(UserModel::class.java)
                    var s= model?.society
                    //query= s?.let { it1 -> FirebaseFirestore.getInstance().collection("Society").document(it1).collection("Events") }!!
                    query=FirebaseFirestore.getInstance().collection("Members").document(user).collection("received events")
                    var rvoptions= FirestoreRecyclerOptions.Builder<EventModel>().setQuery(query,EventModel::class.java).build()
                    firebaseRecyclerAdapter=FirebaseRecyclerAdapter5(this, rvoptions)
                    viewBinding.eventsUserSide.adapter=firebaseRecyclerAdapter
                    viewBinding.eventsUserSide.layoutManager=LinearLayoutManager(this)
                    firebaseRecyclerAdapter.startListening()
                    firebaseRecyclerAdapter.notifyDataSetChanged()
                }
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}