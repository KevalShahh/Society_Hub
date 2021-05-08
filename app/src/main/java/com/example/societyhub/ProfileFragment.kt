package com.example.societyhub

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.card.MaterialCardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
lateinit var imageView:ImageView
lateinit var cardView: MaterialCardView
/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var firebaseUser= FirebaseAuth.getInstance().currentUser
    var userEmail= firebaseUser?.email
    lateinit var membername:TextView
    lateinit var society:TextView
    lateinit var email:TextView
    lateinit var mobile:TextView
    lateinit var address:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageView=view.findViewById(R.id.edit_profile_user_side)
        cardView=view.findViewById(R.id.cardview)
        membername=view.findViewById(R.id.user_name_profile)
        society=view.findViewById(R.id.society_name_my_profile_user)
        email=view.findViewById(R.id.email_my_profile_user)
        mobile=view.findViewById(R.id.mobile_my_profile_user)
        address=view.findViewById(R.id.society_address_my_profile_user)

        imageView.setOnClickListener {

            userEmail?.let { FirebaseFirestore.getInstance().collection("Members").document(it).get().addOnSuccessListener {
                if(it.exists()){
                    var s=it.toObject(UserModel::class.java)
                    if(s!=null){
                        var intent=Intent(context,UserEditProfile::class.java)
                        intent.putExtra("firstName",s.firstName)
                        intent.putExtra("lastName",s.lastName)
                        intent.putExtra("email",s.email)
                        intent.putExtra("mobile",s.mobile)
                        intent.putExtra("flatHouseNo",s.flatHouseNo)
                        startActivity(intent)
                    }
                }
            } }

        }
        userEmail?.let { FirebaseFirestore.getInstance().collection("Members").document(it).get().addOnSuccessListener {
            if(it.exists()){
                var s=it.toObject(UserModel::class.java)
                if(s!=null){
                    membername.text=s.firstName+" "+s.lastName
                    society.text=s.society
                    email.text=s.email
                    mobile.text=s.mobile
                    address.text=s.flatHouseNo
                }
            }
        } }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ProfileFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}