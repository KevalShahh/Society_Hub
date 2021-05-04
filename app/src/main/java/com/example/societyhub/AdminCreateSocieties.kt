package com.example.societyhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.societyhub.databinding.ActivityAdminCreateSocietiesBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AdminCreateSocieties : AppCompatActivity() {
    lateinit var viewBinding1: ActivityAdminCreateSocietiesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding1=ActivityAdminCreateSocietiesBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding1.root)
        var arrayList=ArrayList<String>()
        viewBinding1.adminCreateSocietyBtnCreate.setOnClickListener {
            var flat = viewBinding1.etAdminSocietyInformationFlat.text.toString()
            var area = viewBinding1.etAdminSocietyInformationArea.text.toString()
            var city = viewBinding1.etAdminSocietyInformationCity.text.toString()
            var state = viewBinding1.etAdminSocietyInformationState.text.toString()
            var country = viewBinding1.etAdminSocietyInformationCountry.text.toString()
            var pincode = viewBinding1.etAdminSocietyInformationPincode.text.toString()
            var chairmanfname = viewBinding1.etAdminCreateSocietyChairmanFname.text.toString()
            var chairmanlname = viewBinding1.etAdminCreateSocietyChairmanLname.text.toString()
            var chairmanemail = viewBinding1.etAdminCreateSocietyChairmanEmail.text.toString()
            var chairmanmobile = viewBinding1.etAdminCreateSocietyChairmanMobile.text.toString()
            var chairmanflat = viewBinding1.etAdminCreateSocietyChairmanFlate.text.toString()
            var chairmanpass = viewBinding1.etAdminCreateSocietyChairmanPass.text.toString()
            var chairmanconfirmpass = viewBinding1.etAdminCreateSocietyChairmanConfirmPass.text.toString()
            var status="active"
            val userModel1 = UserModel1(flat, area, city, state, country, pincode, chairmanfname, chairmanlname, chairmanemail, chairmanmobile, chairmanflat, chairmanpass, chairmanconfirmpass,status)
            arrayList.add(flat)
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(chairmanemail, chairmanpass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        FirebaseFirestore.getInstance().collection("Users").document(chairmanemail).set(userModel1).addOnCompleteListener {
                            if (it.isSuccessful){
                                Toast.makeText(this, "Chairman Created", Toast.LENGTH_SHORT).show()
                            }
                        }
                        /* startActivity(Intent(this@RegisterActivity, MainActivity::class.java))*/
                    } else Toast.makeText(this, "${it.exception}", Toast.LENGTH_SHORT).show()
                }
            FirebaseFirestore.getInstance().collection("Society").document(flat).set(userModel1).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Society Create Successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,Societies::class.java))

                }
            }

        }
    }
}