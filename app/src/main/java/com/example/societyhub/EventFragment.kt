package com.example.societyhub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class EventFragment() : Fragment() {

     lateinit var query: Query
     lateinit var recyclerView: RecyclerView
     lateinit var firebaseRecyclerAdapter:FireStoreRecycleAdapter6

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=view.findViewById(R.id.rv_admin_event_fragment)
        query= FirebaseFirestore.getInstance().collection("Events")
        val rvoptions= FirestoreRecyclerOptions.Builder<EventModel>().setQuery(query,EventModel::class.java).build()
        firebaseRecyclerAdapter= context?.let { FireStoreRecycleAdapter6(it,rvoptions) }!!
        recyclerView.adapter=firebaseRecyclerAdapter
        recyclerView.layoutManager=LinearLayoutManager(context)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView:View=inflater.inflate(R.layout.activity_event_fragment,container,false)
        return rootView
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