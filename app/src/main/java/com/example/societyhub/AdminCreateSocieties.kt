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
import java.util.regex.Pattern

class AdminCreateSocieties : AppCompatActivity() {
    lateinit var viewBinding1: ActivityAdminCreateSocietiesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding1 = ActivityAdminCreateSocietiesBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding1.root)
        var regex="^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}\$"
        var pattern= Pattern.compile(regex)
        var arrayList = ArrayList<String>()

        var regex1="^[\\w-_\\.+]*[\\w-\\.]\\.(chairman)\\@([\\w]+\\.)+[\\w]+[\\w]$"
        var pattern1=Pattern.compile(regex1)

        var regex2="^[1-9]{1}[0-9]{2}\\s{0,1}[0-9]{3}$"
        var pattern2=Pattern.compile(regex2)
        viewBinding1.adminCreateSocietyBtnCreate.setOnClickListener {
            var a=true
            if (viewBinding1.etAdminSocietyInformationFlat.text?.isEmpty() == true){
                a=false
                viewBinding1.etAdminSocietyInformationFlat.error="Enter Society Name"
            }
            if (viewBinding1.etAdminSocietyInformationArea.text?.isEmpty() == true){
                a=false
                viewBinding1.etAdminSocietyInformationArea.error="Enter Society Area"
            }
            if (viewBinding1.etAdminSocietyInformationCity.text?.isEmpty() == true){
                a=false
                viewBinding1.etAdminSocietyInformationCity.error="Enter Society City"
            }
            if (viewBinding1.etAdminSocietyInformationState.text?.isEmpty() == true){
                a=false
                viewBinding1.etAdminSocietyInformationState.error="Enter Society State"
            }
            if (viewBinding1.etAdminSocietyInformationCountry.text?.isEmpty() == true){
                a=false
                viewBinding1.etAdminSocietyInformationCountry.error="Enter Society Country"
            }
            if (viewBinding1.etAdminSocietyInformationPincode.text?.isEmpty() == true){
                a=false
                viewBinding1.etAdminSocietyInformationPincode.error="Enter Society Pincode"
            }
            if (!pattern2.matcher(viewBinding1.etAdminSocietyInformationPincode.text).matches()){
                a=false
                viewBinding1.etAdminSocietyInformationPincode.error="Enter Valid Pincode"
            }
            if (viewBinding1.etAdminCreateSocietyChairmanFname.text?.isEmpty() == true){
                a=false
                viewBinding1.etAdminCreateSocietyChairmanFname.error="Enter First Name"
            }
            if (viewBinding1.etAdminCreateSocietyChairmanLname.text?.isEmpty() == true){
                a=false
                viewBinding1.etAdminCreateSocietyChairmanLname.error="Enter Last Name"
            }
            if (viewBinding1.etAdminCreateSocietyChairmanEmail.text?.isEmpty() == true){
                a=false
                viewBinding1.etAdminCreateSocietyChairmanEmail.error="Enter Email Id"
            }
            if (!pattern1.matcher(viewBinding1.etAdminCreateSocietyChairmanEmail.text).matches()){
                a=false
                viewBinding1.etAdminCreateSocietyChairmanEmail.error="Enter Valid Email Id"
            }
            if (viewBinding1.etAdminCreateSocietyChairmanMobile.text?.isEmpty() == true){
                a=false
                viewBinding1.etAdminCreateSocietyChairmanMobile.error="Enter Mobile Number"
            }
            if (!pattern.matcher(viewBinding1.etAdminCreateSocietyChairmanMobile.text).matches() || (viewBinding1.etAdminCreateSocietyChairmanMobile.text?.toString()?.length!! <10) || (viewBinding1.etAdminCreateSocietyChairmanMobile.text!!.toString().length>10)){
                a=false
                viewBinding1.etAdminCreateSocietyChairmanMobile.error="Enter valid Mobile Number"
            }
            if (viewBinding1.etAdminCreateSocietyChairmanFlate.text?.isEmpty() == true){
                a=false
                viewBinding1.etAdminCreateSocietyChairmanFlate.error="Enter House Number"
            }
            if (viewBinding1.etAdminCreateSocietyChairmanPass.text?.isEmpty() == true){
                a=false
                viewBinding1.etAdminCreateSocietyChairmanPass.error="Enter Password"
            }
            if ((viewBinding1.etAdminCreateSocietyChairmanPass.text.toString()) != (viewBinding1.etAdminCreateSocietyChairmanConfirmPass.text.toString())){
                a=false
                viewBinding1.etAdminCreateSocietyChairmanConfirmPass.error="Password Doesn't Match"
            }
            if (a) {
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
            var status = "active"
            val userModel1 = UserModel1(flat, area, city, state, country, pincode, chairmanfname, chairmanlname, chairmanemail, chairmanmobile, chairmanflat, chairmanpass, chairmanconfirmpass, status)
            arrayList.add(flat)
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(chairmanemail, chairmanpass).addOnCompleteListener {
                if (it.isSuccessful) {
                    FirebaseFirestore.getInstance().collection("Users").document(chairmanemail).set(userModel1).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(this, "Chairman Created", Toast.LENGTH_SHORT).show()
                        }
                    }
                    /* startActivity(Intent(this@RegisterActivity, MainActivity::class.java))*/
                } else Toast.makeText(this, "${it.exception}", Toast.LENGTH_SHORT).show()
            }
            FirebaseFirestore.getInstance().collection("Society").document(flat).set(userModel1).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Society Create Successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, Societies::class.java))

                }
            }

        }
    }
    }
}