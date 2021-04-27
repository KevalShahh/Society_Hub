package com.example.societyhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.societyhub.databinding.ActivityChairmanMyProfileBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.*

class ChairmanMyProfile : AppCompatActivity() {
    lateinit var viewBinding:ActivityChairmanMyProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityChairmanMyProfileBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        var firebaseUser=FirebaseAuth.getInstance().currentUser
        var userEmail= firebaseUser?.email
        if (userEmail != null) {
            FirebaseFirestore.getInstance().collection("Users").document(userEmail).get().addOnSuccessListener {
                if(it.exists()){
                    var userModel1=it.toObject(UserModel1::class.java)
                    if (userModel1 != null) {
                        viewBinding.chairmanNameProfile.text=(userModel1.chairmanfname+userModel1.chairmanlname)
                        viewBinding.societyNameMyProfileChairman.text=userModel1.flat
                        viewBinding.emailMyProfileChairman.text=userModel1.chairmanemail
                        viewBinding.mobileMyProfileChairman.text=userModel1.chairmanmobile
                        viewBinding.societyAddressMyProfileChairman.text=(userModel1.area+","+userModel1.city+","+userModel1.country+","+userModel1.pincode)
                    }
                }
            }
            FirebaseFirestore.getInstance().collection("Users").document(userEmail)
        }
        viewBinding.chairmanEditProfile.setOnClickListener {
            if (userEmail != null) {
                FirebaseFirestore.getInstance().collection("Users").document(userEmail).get().addOnSuccessListener {
                    var userModel1=it.toObject(UserModel1::class.java)
                    if (userModel1 != null) {
                        var intent=Intent(this,ChairmanEditProfile::class.java)
                        intent.putExtra("chairmanfname",userModel1.chairmanfname)
                        intent.putExtra("chairmanlname",userModel1.chairmanlname)
                        intent.putExtra("chairmanemail",userModel1.chairmanemail)
                        intent.putExtra("chairmanmobile",userModel1.chairmanmobile)
                        intent.putExtra("chairmanhouse",userModel1.chairmanhouseno)
                        intent.putExtra("flat",userModel1.flat)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}


