package com.example.societyhub

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.societyhub.databinding.ActivityRegisterBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityRegisterBinding
    lateinit var model:UserModel1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        var arrayList=ArrayList<String>()
        FirebaseFirestore.getInstance().collection("Society").addSnapshotListener(EventListener { value, error ->
            if (value != null && !value.isEmpty) {
                Log.d("TAG", "onCreate: "+value.size())
                for (i in 0..(value.size()-1)) {
                    arrayList.add(value.documents.get(i).get("flat").toString())
                    var adapter=ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,arrayList)
                    viewBinding.spSelect.adapter=adapter
                    Log.d("TAG", "onCreate: "+arrayList)
                    viewBinding.spSelect.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
                        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                            Toast.makeText(this@RegisterActivity, "Selected Society : "+ arrayList[position], Toast.LENGTH_SHORT).show()
                        }
                        override fun onNothingSelected(parent: AdapterView<*>?) {
                        }
                    }
                }
            }
        })
        viewBinding.tvLogin.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
        }
        viewBinding.btnRegister.setOnClickListener {
            var email = viewBinding.etEmail.text.toString()
            var pass = viewBinding.etPass.text.toString()
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Registered", Toast.LENGTH_SHORT).show()
                    var fname=viewBinding.etFname.text.toString()
                    var lname=viewBinding.etLname.text.toString()
                    var email=viewBinding.etEmail.text.toString()
                    var mobile=viewBinding.etMob.text.toString()
                    var houseno=viewBinding.etHouse.text.toString()
                    var password=viewBinding.etPass.text.toString()
                    var society=viewBinding.spSelect.selectedItem.toString()
                    var status="pending"
                    var user=UserModel(fname,lname,email,mobile,houseno,password,society,status)
                    FirebaseFirestore.getInstance().collection("Members").document(email).set(user).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(this, "User Stored", Toast.LENGTH_SHORT).show()
                        } else Toast.makeText(this, "" + it.exception, Toast.LENGTH_SHORT).show()
                    }
                   // FirebaseFirestore.getInstance().collection("Society").document(society).collection("Members").add(user)
                } else Toast.makeText(this, "${it.exception}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}