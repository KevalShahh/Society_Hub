package com.example.societyhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.societyhub.databinding.ActivityContactChairmanUserSideBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ContactChairmanUserSide : AppCompatActivity() {
    lateinit var viewBinding:ActivityContactChairmanUserSideBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityContactChairmanUserSideBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        var fireuser=FirebaseAuth.getInstance().currentUser
        var useremail= fireuser?.email
        if (useremail != null) {
            FirebaseFirestore.getInstance().collection("Members").document(useremail).get().addOnSuccessListener {
                if (it.exists()){
                    var model=it.toObject(UserModel::class.java)
                    var s= model?.society
                    FirebaseFirestore.getInstance().collection("Users").whereEqualTo("flat",s).addSnapshotListener { value, error ->
                        if (value!=null && !value.isEmpty){
                            for (i in 0..(value.size()-1)) {
                                viewBinding.chairmanNameContactChairman.text = (value.documents.get(i).get("chairmanfname").toString()) +" "+ (value.documents.get(i).get("chairmanlname").toString())
                                viewBinding.chairmanEmailContactChairman.text=value.documents.get(i).get("chairmanemail").toString()
                                viewBinding.chairmanMobileContactChairman.text=value.documents.get(i).get("chairmanmobile").toString()
                                viewBinding.chairmanHouseNumContactChairman.text=value.documents.get(i).get("chairmanhouseno").toString()
                            }
                        }
                    }
                }
            }
        }
    }
}