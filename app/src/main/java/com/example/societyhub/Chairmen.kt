package com.example.societyhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.societyhub.databinding.ActivityChairmenBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query


class Chairmen : AppCompatActivity() {
    lateinit var viewBinding:ActivityChairmenBinding
    lateinit var query: Query
    lateinit var fireStoreRecyclerAdapter:FireStoreRecycleAdapter2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityChairmenBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        query=FirebaseFirestore.getInstance().collection("Society")
        var rvoptions=FirestoreRecyclerOptions.Builder<UserModel1>().setQuery(query,UserModel1::class.java).build()
        fireStoreRecyclerAdapter=FireStoreRecycleAdapter2(this,rvoptions)
        viewBinding.rvChairmen.adapter=fireStoreRecyclerAdapter
        viewBinding.rvChairmen.layoutManager=LinearLayoutManager(this)
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
        var item:MenuItem= menu!!.findItem(R.id.item_search)
        var searchView:SearchView= item.actionView as SearchView
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                processSearch(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                processSearch(newText)
                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun processSearch(newText: String?) {
        var rvOptions=FirestoreRecyclerOptions.Builder<UserModel1>().setQuery(FirebaseFirestore.getInstance().collection("Society").orderBy("chairmanfname").startAt(newText).endAt(newText+"\uf8ff"),UserModel1::class.java).build()
        fireStoreRecyclerAdapter= FireStoreRecycleAdapter2(this,rvOptions)
        fireStoreRecyclerAdapter.startListening()
        viewBinding.rvChairmen.adapter=fireStoreRecyclerAdapter

    }
}