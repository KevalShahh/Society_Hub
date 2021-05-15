package com.example.societyhub

import android.os.Bundle
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
 * Use the [UserEventFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserEventFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var query: Query
    lateinit var firebaseRecyclerAdapter:FirebaseRecyclerAdapter6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var fireuser= FirebaseAuth.getInstance().currentUser
        var user= fireuser?.email
        var eventsUserSide=view.findViewById<RecyclerView>(R.id.events_user_side_fragment)
        if (user != null) {
            FirebaseFirestore.getInstance().collection("Members").document(user).get().addOnSuccessListener {
                if (it.exists()){
                    var model=it.toObject(UserModel::class.java)
                    var s= model?.society
                  //  query= s?.let { it1 -> FirebaseFirestore.getInstance().collection("Society").document(it1).collection("Events") }!!
                    query=FirebaseFirestore.getInstance().collection("Members").document(user).collection("received events").whereEqualTo("paid","")
                    var rvoptions= FirestoreRecyclerOptions.Builder<EventModel>().setQuery(query,EventModel::class.java).build()
                    firebaseRecyclerAdapter=FirebaseRecyclerAdapter6(view.context, rvoptions)
                    eventsUserSide.adapter=firebaseRecyclerAdapter
                    eventsUserSide.layoutManager= LinearLayoutManager(view.context)
                    firebaseRecyclerAdapter.startListening()
                    firebaseRecyclerAdapter.notifyDataSetChanged()
                }
            }
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_event, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UserEventFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                UserEventFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}