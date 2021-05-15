package com.example.societyhub

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.societyhub.databinding.ActivityUserBinding

class User : AppCompatActivity() {
    lateinit var viewBinding:ActivityUserBinding
    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityUserBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        preferences=getSharedPreferences("user", MODE_PRIVATE)
        editor=preferences.edit()
        initBottomNav()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.logout_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btn_logout -> {
                showAlertbox()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun showAlertbox() {
        AlertDialog.Builder(this@User)
                .setTitle(R.string.app_name)
                .setMessage("Are you sure you want to Logout?")
                .setPositiveButton("Yes") { _, _ ->
                    editor.clear()
                    editor.apply()
                    startActivity(Intent(this@User, MainActivity::class.java))
                    Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .setNegativeButton("No", null)
                .show()
    }
    private fun initBottomNav() {
        //TODO("Not yet implemented")
        supportFragmentManager.beginTransaction().replace(R.id.user_frame_layout,HomeFragment()).commit()

        viewBinding.bottomNavUserSide.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.item_home ->
                    supportFragmentManager.beginTransaction().replace(R.id.user_frame_layout,HomeFragment()).commit()
                R.id.item_payment->
                    supportFragmentManager.beginTransaction().replace(R.id.user_frame_layout,PaymentFragment()).commit()
                R.id.item_profile->
                    supportFragmentManager.beginTransaction().replace(R.id.user_frame_layout,ProfileFragment()).commit()
            }

            true
        }
    }
}