package com.example.societyhub

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.card.MaterialCardView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
lateinit var textView:TextView
lateinit var maintenance:MaterialCardView
lateinit var event:MaterialCardView
lateinit var notice:MaterialCardView
/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView=view.findViewById(R.id.contact_chairman_user_side)
        maintenance=view.findViewById(R.id.maintenance_card_view_user_side)
        event=view.findViewById(R.id.event_card_view_user_side)
        notice=view.findViewById(R.id.notice_card_view_user_side)
        textView.setOnClickListener {
            startActivity(Intent(context,ContactChairmanUserSide::class.java))
        }
        maintenance.setOnClickListener {
            startActivity(Intent(context,MaintenanceUserSide::class.java))
        }
        event.setOnClickListener {
            startActivity(Intent(context,EventsUserSide::class.java))
        }
        notice.setOnClickListener {
            startActivity(Intent(context,NoticeUserSide::class.java))
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                HomeFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}