package com.example.societyhub

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.societyhub.databinding.ActivitySocietiesBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.util.*
import kotlin.collections.ArrayList as ArrayList1

class Societies : AppCompatActivity() {
    lateinit var viewBinding: ActivitySocietiesBinding
    lateinit var query: Query
    lateinit var fireStoreRecyclerAdapter: FireStoreRecycleAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding=ActivitySocietiesBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        query=FirebaseFirestore.getInstance().collection("Society")
        var rvOptions=FirestoreRecyclerOptions.Builder<UserModel1>().setQuery(query,UserModel1::class.java).build()
        fireStoreRecyclerAdapter=FireStoreRecycleAdapter(this,rvOptions)
        viewBinding.rvSocieties.adapter=fireStoreRecyclerAdapter
        viewBinding.rvSocieties.layoutManager=LinearLayoutManager(this)
        viewBinding.adminCreateSocieties.setOnClickListener {
            startActivity(Intent(this, AdminCreateSocieties::class.java))
        }
    }
    override fun onStart() {
        super.onStart()
        fireStoreRecyclerAdapter.startListening()
    }
    override fun onStop() {
        super.onStop()
        fireStoreRecyclerAdapter.stopListening()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu,menu)
        val item:MenuItem= menu!!.findItem(R.id.item_search)
        val searchView:SearchView= item.actionView as SearchView
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                processSearch(query)
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                processSearch(newText)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun processSearch(newText: String?) {
        val options:FirestoreRecyclerOptions<UserModel1>
        options=FirestoreRecyclerOptions.Builder<UserModel1>().setQuery(FirebaseFirestore.getInstance().collection("Society")
                . orderBy("flat").startAt(newText).endAt(newText+"\uf8ff"),UserModel1::class.java).build()
        fireStoreRecyclerAdapter= FireStoreRecycleAdapter(this,options)
        fireStoreRecyclerAdapter.startListening()
        viewBinding.rvSocieties.adapter=fireStoreRecyclerAdapter
    }
}