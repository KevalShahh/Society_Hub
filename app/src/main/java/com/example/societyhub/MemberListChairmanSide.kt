package com.example.societyhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.societyhub.databinding.ActivityMemberListChairmanSideBinding
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class MemberListChairmanSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityMemberListChairmanSideBinding
    lateinit var query:Query
    lateinit var firestorerecyclerAdapter:FireStoreRecycleAdapter7
    lateinit var s:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityMemberListChairmanSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        var firebaseUser=FirebaseAuth.getInstance().currentUser
        var userEmail= firebaseUser?.email
        Log.d("TAG", "onCreate: "+userEmail)
        if (userEmail != null) {
            FirebaseFirestore.getInstance().collection("Users").document(userEmail).get().addOnSuccessListener {
                if (it.exists()){
                    var userModel1=it.toObject(UserModel1::class.java)
                    if (userModel1!=null){
                        s=userModel1.flat
                        Log.d("TAG", "onCreate: "+s)
                       /* query= FirebaseFirestore.getInstance().collection("Members").whereEqualTo("society",s)
                        var rvoptions= FirestoreRecyclerOptions.Builder<UserModel>().setQuery(query,UserModel::class.java).build()
                        firestorerecyclerAdapter=FireStoreRecycleAdapter7(this,rvoptions)
                        viewBinding.rvMemberListChairmanSide.adapter=firestorerecyclerAdapter
                        viewBinding.rvMemberListChairmanSide.layoutManager= LinearLayoutManager(this)
                        Log.d("TAG", "onCreate: "+firestorerecyclerAdapter.itemCount)
                        firestorerecyclerAdapter.startListening()
                        firestorerecyclerAdapter.stopListening()
                        firestorerecyclerAdapter.notifyDataSetChanged()*/
                    }
                }
            }
        }
        query= FirebaseFirestore.getInstance().collection("Members").whereEqualTo("society","ananya")
        var rvoptions= FirestoreRecyclerOptions.Builder<UserModel>().setQuery(query,UserModel::class.java).build()
        firestorerecyclerAdapter=FireStoreRecycleAdapter7(this,rvoptions)
        viewBinding.rvMemberListChairmanSide.adapter=firestorerecyclerAdapter
        viewBinding.rvMemberListChairmanSide.layoutManager= LinearLayoutManager(this)
        Log.d("TAG", "onCreate: "+viewBinding.rvMemberListChairmanSide.childCount)
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