package com.example.societyhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
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
        query=FirebaseFirestore.getInstance().collection("Society")
        var rvoptions=FirestoreRecyclerOptions.Builder<UserModel1>().setQuery(query,UserModel1::class.java).build()
        firestorerecyclerAdapter=FireStoreRecycleAdapter3(this,rvoptions)
        viewBinding.rvAdminCreateNotice.adapter=firestorerecyclerAdapter
        viewBinding.rvAdminCreateNotice.layoutManager=LinearLayoutManager(this)


        viewBinding.adminCreateNoticeSendNotice.setOnClickListener {
            var title=viewBinding.adminCreateNoticeTitle.text.toString()
            var description=viewBinding.adminCreateNoticeDescription.text.toString()
            var timestamp=Timestamp.now()
//            Log.d("TAG", "onCreate: "+firestorerecyclerAdapter.arraylist)
            firestorerecyclerAdapter.arraylist.forEach {
                var adminNoticeModel=AdminNoticeModel(title,description,it)
                FirebaseFirestore.getInstance().collection("Notice").document(it+"_"+timestamp.seconds).set(adminNoticeModel).addOnCompleteListener {
                    if (it.isSuccessful)
                    {
                        Toast.makeText(this, "Notice Sent Successfully", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,AdminNotice::class.java))
                    }
                }
            }



        }

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