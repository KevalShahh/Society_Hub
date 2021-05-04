package com.example.societyhub

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NoticesReceivedFragmentChairmanSide.newInstance] factory method to
 * create an instance of this fragment.
 */
class NoticesReceivedFragmentChairmanSide : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var query: Query
    private lateinit var firestorerecycleadapter:FireStoreRecycleAdapter4
    private lateinit var recycleView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var currentuser=FirebaseAuth.getInstance().currentUser
        var email= currentuser?.email

        recycleView=view.findViewById(R.id.rv_notices_received_chairman_side)
        query=FirebaseFirestore.getInstance().collection("Notice").whereEqualTo("chairmanemailid",email)
        val rvoptions= FirestoreRecyclerOptions.Builder<AdminNoticeModel>().setQuery(query,AdminNoticeModel::class.java).build()
        firestorerecycleadapter= context?.let { FireStoreRecycleAdapter4(it,rvoptions) }!!
        recycleView.adapter= firestorerecycleadapter
        recycleView.layoutManager=LinearLayoutManager(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notices_received_chairman_side, container, false)


    }

    override fun onStart() {
        super.onStart()
        firestorerecycleadapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        firestorerecycleadapter.stopListening()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NoticesReceivedFragmentChairmanSide.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                NoticesReceivedFragmentChairmanSide().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}