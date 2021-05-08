package com.example.societyhub

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.societyhub.databinding.ActivityForgetPasswordBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class ForgetPassword : AppCompatActivity() {
    lateinit var viewBinding:ActivityForgetPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityForgetPasswordBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        val regex="^[\\w-_\\.+]*[\\w-\\.]\\.(chairman)\\@([\\w]+\\.)+[\\w]+[\\w]$"
        var pattern= Pattern.compile(regex)
        viewBinding.tvLogin.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
        var email=viewBinding.etEmailForgotPassword.text
        viewBinding.btnSendEmail.setOnClickListener {
            var a=true
            if (viewBinding.etEmailForgotPassword.text?.isEmpty() == true){
                a=false
                viewBinding.etEmailForgotPassword.error="Enter Email Id"
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(viewBinding.etEmailForgotPassword.text).matches() && !pattern.matcher(viewBinding.etEmailForgotPassword.text).matches()){
                a=false
                viewBinding.etEmailForgotPassword.error="Enter Valid Email Id"
            }
            if (a) {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Email Sent Successfully", Toast.LENGTH_SHORT).show()
                    } else Toast.makeText(this, "" + it.exception, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}