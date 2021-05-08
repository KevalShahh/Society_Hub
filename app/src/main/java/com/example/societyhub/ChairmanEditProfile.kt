package com.example.societyhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.societyhub.databinding.ActivityChairmanEditProfileBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.util.HashMap
import java.util.regex.Pattern

class ChairmanEditProfile : AppCompatActivity() {
    lateinit var viewBinding:ActivityChairmanEditProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityChairmanEditProfileBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        var regex="^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}\$"
        var pattern= Pattern.compile(regex)
        var cemail=intent.getStringExtra("chairmanemail")
        var flat=intent.getStringExtra("flat")
        viewBinding.chairmanFirstNameMyProfile.setText(intent.getStringExtra("chairmanfname"))
        viewBinding.chairmanLastNameMyProfile.setText(intent.getStringExtra("chairmanlname"))
        viewBinding.chairmanEmailMyProfile.setText(intent.getStringExtra("chairmanemail"))
        viewBinding.chairmanMobileMyProfile.setText(intent.getStringExtra("chairmanmobile"))
        viewBinding.chairmanHouseNumMyProfile.setText(intent.getStringExtra("chairmanhouseno"))

        viewBinding.chairmanUpdateMyProfile.setOnClickListener {
            var a=true
            if(viewBinding.chairmanFirstNameMyProfile.text?.isEmpty() == true){
                a=false
                viewBinding.chairmanFirstNameMyProfile.error="Enter First Name"
            }
            if(viewBinding.chairmanLastNameMyProfile.text?.isEmpty() == true){
                a=false
                viewBinding.chairmanLastNameMyProfile.error="Enter Last Name"
            }
            if(viewBinding.chairmanMobileMyProfile.text?.isEmpty() == true){
                a=false
                viewBinding.chairmanMobileMyProfile.error="Enter Mobile Number"
            }
            if(!pattern.matcher(viewBinding.chairmanMobileMyProfile.text).matches() || (viewBinding.chairmanMobileMyProfile.text!!.length<10) || (viewBinding.chairmanMobileMyProfile.text!!.length>10)){
                a=false
                viewBinding.chairmanMobileMyProfile.error="Enter Valid Mobile Number"
            }
            if(viewBinding.chairmanHouseNumMyProfile.text?.isEmpty() == true){
                a=false
                viewBinding.chairmanHouseNumMyProfile.error="Enter House Number"
            }
            if(a) {
                var map1 = HashMap<String, String>()
                map1.put("chairmanfname", viewBinding.chairmanFirstNameMyProfile.getText().toString())
                map1.put("chairmanlname", viewBinding.chairmanLastNameMyProfile.text.toString())
                map1.put("chairmanemail", viewBinding.chairmanEmailMyProfile.text.toString())
                map1.put("chairmanmobile", viewBinding.chairmanMobileMyProfile.getText().toString())
                map1.put("chairmanhouseno", viewBinding.chairmanHouseNumMyProfile.text.toString())
                if (cemail != null) {
                    FirebaseFirestore.getInstance().collection("Users").document(cemail).update(map1 as Map<String, Any>).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(this, "Chairman Updated", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, ChairmanMyProfile::class.java))
                        }
                    }
                    if (flat != null) {
                        FirebaseFirestore.getInstance().collection("Society").document(flat).update(map1 as Map<String, Any>).addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(this, "Chairman Updated", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }

    }
}