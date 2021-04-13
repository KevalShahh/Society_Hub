package com.example.societyhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.societyhub.databinding.ActivityUserBinding

class User : AppCompatActivity() {
    lateinit var viewBinding:ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityUserBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        initBottomNav()
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