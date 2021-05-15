package com.example.societyhub

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.societyhub.databinding.ActivityAdminBinding

class Admin : AppCompatActivity()  {
    private lateinit var viewBinding: ActivityAdminBinding
    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding=ActivityAdminBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        preferences=getSharedPreferences("user", MODE_PRIVATE)
        editor=preferences.edit()

        viewBinding.adminSocieties.setOnClickListener {
            startActivity(Intent(this,Societies::class.java))
        }
        viewBinding.adminChairmen.setOnClickListener {
            startActivity(Intent(this,Chairmen::class.java))
        }
        viewBinding.Notice.setOnClickListener {
            startActivity(Intent(this,AdminNotice::class.java))
        }
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
        AlertDialog.Builder(this@Admin)
                .setTitle(R.string.app_name)
                .setMessage("Are you sure you want to Logout?")
                .setPositiveButton("Yes") { _, _ ->
                    editor.clear()
                    editor.apply()
                    startActivity(Intent(this@Admin, MainActivity::class.java))
                    Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .setNegativeButton("No", null)
                .show()
    }
}