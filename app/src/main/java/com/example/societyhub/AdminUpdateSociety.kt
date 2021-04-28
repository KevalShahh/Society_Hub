package com.example.societyhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.example.societyhub.databinding.ActivityAdminUpdateSocietyBinding
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.HashMap

class AdminUpdateSociety : AppCompatActivity() {
    lateinit var viewBinding: ActivityAdminUpdateSocietyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityAdminUpdateSocietyBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        val flat = intent.getStringExtra("flat")
        viewBinding.edtAdminUpdateSocietyFlat.setText(intent.getStringExtra("flat"))
        viewBinding.edtAdminUpdateSocietyArea.setText(intent.getStringExtra("area"))
        viewBinding.edtAdminUpdateSocietyCity.setText(intent.getStringExtra("city"))
        viewBinding.edtAdminUpdateSocietyState.setText(intent.getStringExtra("state"))
        viewBinding.edtAdminUpdateSocietyCountry.setText(intent.getStringExtra("country"))
        viewBinding.edtAdminUpdateSocietyPincode.setText(intent.getStringExtra("pincode"))
        viewBinding.btnAdminUpdateSociety.setOnClickListener {
            val map:Map<String,String>
            map=HashMap()
            map.put("flat",viewBinding.edtAdminUpdateSocietyFlat.text.toString())
            map.put("area",viewBinding.edtAdminUpdateSocietyArea.text.toString())
            map.put("city",viewBinding.edtAdminUpdateSocietyCity.text.toString())
            map.put("state",viewBinding.edtAdminUpdateSocietyState.text.toString())
            map.put("country",viewBinding.edtAdminUpdateSocietyCountry.text.toString())
            map.put("pincode",viewBinding.edtAdminUpdateSocietyPincode.text.toString())
            FirebaseFirestore.getInstance().collection("Society").document(flat.toString()).update(map).addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this, "Society Updated Successfully", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}