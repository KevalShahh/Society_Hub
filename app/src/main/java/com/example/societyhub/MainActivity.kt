package com.example.societyhub

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.societyhub.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.protobuf.StringValue
import java.util.regex.Pattern

class MainActivity : AppCompatActivity()  {
    private lateinit var viewBinding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.tvRegister.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
        viewBinding.tvForgot.setOnClickListener {
            startActivity(Intent(this,ForgetPassword::class.java))
        }
        viewBinding.btnLogin.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val email = viewBinding.edtEmail.text.toString()
        val pass = viewBinding.edtPassword.text.toString()
        val regex="^[\\w-_\\.+]*[\\w-\\.]\\.(chairman)\\@([\\w]+\\.)+[\\w]+[\\w]$"
        var pattern=Pattern.compile(regex)
        var model:UserModel1
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                if (it.isSuccessful) {
                        if (email == "admin@gmail.com") {
                            Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, Admin::class.java))
                        }
                        else if (pattern.matcher(email).matches()) {
                            Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, Chairman::class.java))
                        }
                    else{
                            Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,User::class.java))
                        }
                }
        }

    }
}

