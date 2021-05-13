package com.example.societyhub

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.societyhub.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.protobuf.StringValue
import java.util.regex.Pattern

class MainActivity : AppCompatActivity()  {
    private lateinit var viewBinding:ActivityMainBinding
    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        preferences=getSharedPreferences("user", MODE_PRIVATE)
        editor=preferences.edit()
        if(preferences.contains("adminemail")){
            startActivity(Intent(this@MainActivity,Admin::class.java))
            finish()
        }
        if(preferences.contains("chairmanemail")){
            startActivity(Intent(this@MainActivity,Chairman::class.java))
            finish()
        }
        if(preferences.contains("useremail")){
            startActivity(Intent(this@MainActivity,User::class.java))
            finish()
        }

        val regex="^[\\w-_\\.+]*[\\w-\\.]\\.(chairman)\\@([\\w]+\\.)+[\\w]+[\\w]$"
        var pattern=Pattern.compile(regex)
        viewBinding.tvRegister.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
        viewBinding.tvForgot.setOnClickListener {
            startActivity(Intent(this,ForgetPassword::class.java))
        }
        viewBinding.btnLogin.setOnClickListener {
            var a = true
            if (viewBinding.edtEmail.text?.isEmpty() == true){
                a=false
                viewBinding.edtEmail.error="Enter Email Id"
            }
            if (!pattern.matcher(viewBinding.edtEmail.text.toString()).matches() && !Patterns.EMAIL_ADDRESS.matcher(viewBinding.edtEmail.text).matches()){
                a=false
                viewBinding.edtEmail.error="Enter Valid Email Id"
            }
            if (viewBinding.edtPassword.text?.isEmpty() == true){
                a=false
                viewBinding.edtPassword.error="Enter Password"
            }
            if (!isNetworkAvailable(this)){
                a=false
                Toast.makeText(this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show()
            }
            if (a){
                loginUser()
            }
        }
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetworkInfo: NetworkInfo? = null
        activeNetworkInfo = cm.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
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
                            getadmindata(email)
                            Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, Admin::class.java))
                            finish()
                        }
                        else if (pattern.matcher(email).matches()) {
                            FirebaseFirestore.getInstance().collection("Users").whereEqualTo("chairmanemail",email).addSnapshotListener { value, error ->
                                if(value!=null && !value.isEmpty){
                                    for (i in 0..value.size()-1){
                                        var s=value.documents.get(i).get("status").toString()
                                        if (s=="active") {
                                            getchairmandata(email)
                                            Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show()
                                            startActivity(Intent(this, Chairman::class.java))
                                            finish()
                                        }
                                        else Toast.makeText(this, "Contact Admin to Activate", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }
                    else{
                            FirebaseFirestore.getInstance().collection("Members").whereEqualTo("email",email).addSnapshotListener { value, error ->
                                if(value!=null && !value.isEmpty){
                                    for (i in 0..value.size()-1){
                                        var s=value.documents.get(i).get("status").toString()
                                        if (s=="active") {
                                            getuserdata(email)
                                            Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show()
                                            startActivity(Intent(this,User::class.java))
                                            finish()
                                        }
                                        else Toast.makeText(this, "Contact Chairman To Activate", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }
                }
            else Toast.makeText(this, "Error"+it.exception, Toast.LENGTH_SHORT).show()
        }

    }
    private fun getadmindata(email: String) {
        editor.putString("adminemail","admin@gmail.com")
        editor.apply()
    }

    private fun getuserdata(email: String) {
        FirebaseFirestore.getInstance().collection("Members").whereEqualTo("email",email).addSnapshotListener { value, error ->
            if(value!=null && !value.isEmpty){
                for(i in 0..value.size()-1){
                    var model: UserModel? =value.documents.get(i).toObject(UserModel::class.java)
                    editor.putString("useremail", model?.email)
                    editor.apply()
                }
            }
            if (error!=null){
                Toast.makeText(this, ""+error.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getchairmandata(email: String) {
        FirebaseFirestore.getInstance().collection("Users").whereEqualTo("chairmanemail",email).addSnapshotListener { value, error ->
            if(value!=null && !value.isEmpty){
                for(i in 0..value.size()-1){
                    var model: UserModel1? =value.documents.get(i).toObject(UserModel1::class.java)
                    editor.putString("chairmanemail", model?.chairmanemail)
                    editor.apply()
                }

            }
            if (error!=null){
                Toast.makeText(this, ""+error.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

}

