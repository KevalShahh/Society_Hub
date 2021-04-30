package com.example.societyhub

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.societyhub.databinding.ActivityRegisterBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.tvLogin.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
        }
        viewBinding.btnRegister.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        var email = viewBinding.etEmail.text.toString()
        var pass = viewBinding.etPass.text.toString()
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Registered", Toast.LENGTH_SHORT).show()
                val user = UserModel(email = email, password = pass)
                storeUser(user)
            } else Toast.makeText(this, "${it.exception}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun storeUser(user: UserModel) {
        var fname=viewBinding.etFname.text.toString()
        var lname=viewBinding.etLname.text.toString()
        var email=viewBinding.etEmail.text.toString()
        var mobile=viewBinding.etMob.text.toString()
        var flat=viewBinding.etHouse.text.toString()
        var password=viewBinding.etPass.text.toString()
        var user=UserModel(fname,lname,email,mobile,flat,password)
        FirebaseFirestore.getInstance().collection("Users").document(email).set(user).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "User Stored", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(this, "" + it.exception, Toast.LENGTH_SHORT).show()
        }

    }


}