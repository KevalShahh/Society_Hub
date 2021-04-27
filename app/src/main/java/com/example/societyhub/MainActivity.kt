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

        viewBinding.btnLogin.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val email = viewBinding.edtEmail.text.toString()
        val pass = viewBinding.edtPassword.text.toString()
        val regex="^[\\w-_\\.+]*[\\w-\\.]\\.(chairman)\\@([\\w]+\\.)+[\\w]+[\\w]$"
        var pattern=Pattern.compile(regex)
      //  var matcher=pattern.matcher(email)
       /* if (matcher.matches().toString().equals("true"))
        {
            Toast.makeText(this, "logged in", Toast.LENGTH_SHORT).show()
            Log.d("TAG", "loginUser: " + matcher.matches().toString())
        }
        else{
            Log.d("TAG", "loginUser: "+"false")
        }
*/

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                if (it.isSuccessful) {
                    if (email=="admin@gmail.com") {
                        Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, Admin::class.java))
                      //  Log.d("TAG", "loginUser: " + matcher.matches().toString())
                    }
                    else if (pattern.matcher(email).matches()){
                        Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show()
                           // Log.d("TAG", "loginUser: " + matcher.matches().toString())
                        startActivity(Intent(this, Chairman::class.java))
                    }
                }
        }

    }
}

