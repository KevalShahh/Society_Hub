package com.example.societyhub

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.societyhub.databinding.ActivityChairmanBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Chairman : AppCompatActivity() {
    lateinit var viewBinding:ActivityChairmanBinding
    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityChairmanBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        preferences=getSharedPreferences("user", MODE_PRIVATE)
        editor=preferences.edit()
        var fireuser= FirebaseAuth.getInstance().currentUser
        var user= fireuser?.email
        Log.d("TAG", "onCreate: "+user)
        user?.let {
            FirebaseFirestore.getInstance().collection("Users").document(it).get().addOnSuccessListener {
                if (it.exists()) {
                    var model = it.toObject(UserModel1::class.java)
                    var soc= model?.flat
                    viewBinding.btnActiveBlock.setOnCheckedChangeListener { buttonView, isChecked ->
                        if (isChecked) {
                            if (model != null) {
                                Toast.makeText(this, "Activated", Toast.LENGTH_SHORT).show()
                                model.societystatus = "active"
                                Log.d("TAG", "onCreate: " + model.societystatus)
                                var map = HashMap<String, String>()
                                map.put("societystatus", model.societystatus)
                                FirebaseFirestore.getInstance().collection("Users").document(user).update(map as Map<String, Any>)
                                if (soc != null) {
                                    FirebaseFirestore.getInstance().collection("Society").document(soc).update(map as Map<String, Any>)
                                }
                            }
                        } else {
                            if (model != null) {
                                Toast.makeText(this, "Blocked", Toast.LENGTH_SHORT).show()
                                model.societystatus = "block"
                                Log.d("TAG", "onCreate: " + model.societystatus)
                                var map = HashMap<String, String>()
                                map.put("societystatus", model.societystatus)
                                FirebaseFirestore.getInstance().collection("Users").document(user).update(map as Map<String, Any>)
                                if (soc != null) {
                                    FirebaseFirestore.getInstance().collection("Society").document(soc).update(map as Map<String, Any>)
                                }
                            }
                        }
                    }
                }
            }
        }

        viewBinding.tilMyProfileChairmanSide.setOnClickListener {
            startActivity(Intent(this,ChairmanMyProfile::class.java))
        }
        viewBinding.tilMembersChairmanSide.setOnClickListener {
            startActivity(Intent(this,MemberListChairmanSide::class.java))
        }
        viewBinding.tilMaintenancChairmanSide.setOnClickListener {
            startActivity(Intent(this,MaintenanceListChairmanSide::class.java))
        }
        viewBinding.tilEventsChairmanSide.setOnClickListener {
            startActivity(Intent(this,EventsChairmanSide::class.java))
        }
        viewBinding.tilNoticeChairmanSide.setOnClickListener {
            startActivity(Intent(this,NoticiesChairmanSide::class.java))
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.logout_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btn_logout -> {
                editor.clear()
                editor.apply()
                Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}