
package com.example.societyhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.societyhub.databinding.ActivityUserEditProfileBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.util.HashMap
import java.util.regex.Pattern

class UserEditProfile : AppCompatActivity() {
    lateinit var viewBinding:ActivityUserEditProfileBinding
    var regex="^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}\$"
    var pattern= Pattern.compile(regex)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityUserEditProfileBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        var uemail=intent.getStringExtra("email")
        viewBinding.userFirstName.setText(intent.getStringExtra("firstName"))
        viewBinding.userLastName.setText(intent.getStringExtra("lastName"))
        viewBinding.userEmail.setText(intent.getStringExtra("email"))
        viewBinding.userMobile.setText(intent.getStringExtra("mobile"))
        viewBinding.userHouseNumber.setText(intent.getStringExtra("flatHouseNo"))
        viewBinding.btnUserUpdate.setOnClickListener {
            var a = true
            if(viewBinding.userFirstName.text?.isEmpty() == true){
                a=false
                viewBinding.userFirstName.error="Enter First Name"
            }
            if(viewBinding.userLastName.text?.isEmpty() == true){
                a=false
                viewBinding.userLastName.error="Enter Last Name"
            }
            if(viewBinding.userMobile.text?.isEmpty() == true){
                a=false
                viewBinding.userMobile.error="Enter Number"
            }
            if(!pattern.matcher(viewBinding.userMobile.text).matches() || viewBinding.userMobile.text.toString().length!!<10||viewBinding.userMobile.text.toString().length!!>10){
                a=false
                viewBinding.userMobile.error="Enter 10 Digit Mobile Number"
            }

            if(viewBinding.userHouseNumber.text?.isEmpty() == true){
                a=false
                viewBinding.userHouseNumber.error="Enter House Number"
            }
            if (a) {
                var map1 = HashMap<String, String>()
                map1.put("firstName", viewBinding.userFirstName.getText().toString())
                map1.put("lastName", viewBinding.userLastName.text.toString())
                map1.put("email", viewBinding.userEmail.text.toString())
                map1.put("mobile", viewBinding.userMobile.getText().toString())
                map1.put("flatHouseNo", viewBinding.userHouseNumber.text.toString())
                if (uemail != null) {
                    FirebaseFirestore.getInstance().collection("Members").document(uemail).update(map1 as Map<String, Any>).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(this, "Member Updated", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, User::class.java))
                        }
                    }
                }
            }
        }
    }
}