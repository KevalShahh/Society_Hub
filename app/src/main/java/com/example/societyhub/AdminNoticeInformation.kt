package com.example.societyhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.societyhub.databinding.ActivityAdminNoticeInformationBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore

class AdminNoticeInformation : AppCompatActivity() {
    lateinit var viewBinding:ActivityAdminNoticeInformationBinding
    lateinit var firestorerecycleradapter:FireStoreRecycleAdapter13
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityAdminNoticeInformationBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewBinding.adminNoticeTitle.text=intent.getStringExtra("title1")
        viewBinding.adminNoticeDescription.text=intent.getStringExtra("description1")
        viewBinding.adminNoticeCreatedAt.text=intent.getStringExtra("createdAt1")

        var title=intent.getStringExtra("title1")
        Log.d("TAG", "onCreate: "+title)
       /* FirebaseFirestore.getInstance().collection("Users").addSnapshotListener { value, error ->
            if (value != null) {
                for (i in 0..(value.size()-1)){
                    var arrayList=ArrayList<String>()
                    arrayList.add(value.documents.get(i).id.toString())
                    arrayList.forEach {
                        var query=FirebaseFirestore.getInstance().collection("Users")
                                //.document(it.toString()).collection("recieved notice").whereEqualTo("title",title)
                        var rvoptions=FirestoreRecyclerOptions.Builder<UserModel1>().setQuery(query,UserModel1::class.java).build()
                        firestorerecycleradapter=FireStoreRecycleAdapter13(this,rvoptions)
                        viewBinding.rvAdminNoticeInformation.adapter=firestorerecycleradapter
                        viewBinding.rvAdminNoticeInformation.layoutManager=LinearLayoutManager(this)
                        firestorerecycleradapter.startListening()
                        firestorerecycleradapter.notifyDataSetChanged()
                    }
                }

            }
        }*/
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}