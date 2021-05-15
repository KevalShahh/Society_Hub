package com.example.societyhub

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.societyhub.databinding.ActivitySocietyInformationBinding
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class Society_Information : AppCompatActivity() {
    lateinit var viewBinding:ActivitySocietyInformationBinding
    lateinit var query: Query
    lateinit var firestoreRecycleadapter:FireStoreRecycleAdapter12

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivitySocietyInformationBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        var s=intent.getStringExtra("society_name")
        viewBinding.tvSocietyNameSociety.setText((intent.getStringExtra("society_name")))
        viewBinding.tvAddressSociety.setText(intent.getStringExtra("address"))
        viewBinding.tvNameChairman.setText(intent.getStringExtra("chairman_name"))
        viewBinding.tvMobileChairman.setText(intent.getStringExtra("chairman_mobile"))
        viewBinding.tvEmailChairman.setText(intent.getStringExtra("chairman_email"))

        query=FirebaseFirestore.getInstance().collection("Members").whereEqualTo("society",s)
        var rvoptions=FirestoreRecyclerOptions.Builder<UserModel>().setQuery(query,UserModel::class.java).build()
        firestoreRecycleadapter=FireStoreRecycleAdapter12(this,rvoptions)
        viewBinding.rvMembers.adapter= firestoreRecycleadapter
        viewBinding.rvMembers.layoutManager=LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()
        firestoreRecycleadapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        firestoreRecycleadapter.stopListening()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}