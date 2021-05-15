package com.example.societyhub

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HistoryMaintenanceFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoryMaintenanceFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var query: Query
    lateinit var firebaseRecyclerAdapter: FirebaseRecyclerAdapter2
    lateinit var recyclerView: RecyclerView
    lateinit var textInputEditText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=view.findViewById(R.id.rv_user_maintenance)
        textInputEditText=view.findViewById(R.id.til_edt_month_member_maintenance_history)
        var e= activity?.intent?.getStringExtra("email")
        Log.d("TAG", "onViewCreated: "+e)
        var fireuser=FirebaseAuth.getInstance().currentUser
        var user= fireuser?.email
        //query = FirebaseFirestore.getInstance().collection("Maintenance").whereEqualTo("useremail",e).whereEqualTo("paid","Yes")
        query= user?.let { FirebaseFirestore.getInstance().collection("Members").document(it).collection("received maintenance").whereEqualTo("paid","Yes") }!!
        var rvoptions = FirestoreRecyclerOptions.Builder<MaintenanceModel>().setQuery(query, MaintenanceModel::class.java).build()
        firebaseRecyclerAdapter = context?.let { FirebaseRecyclerAdapter2(it, rvoptions) }!!
        recyclerView.adapter = firebaseRecyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        textInputEditText.setOnClickListener {
            monthPicker()
        }
    }

    private fun monthPicker() {
        var c = Calendar.getInstance()
        var year = c.get(Calendar.YEAR)
        var month = c.get(Calendar.MONTH)
        var day = c.get(Calendar.DAY_OF_MONTH)

        var dialog = context?.let {
            DatePickerDialog(it,
                    AlertDialog.THEME_HOLO_LIGHT,
                    DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        val cal = Calendar.getInstance()
                        cal.set(Calendar.YEAR, year)
                        cal.set(Calendar.MONTH, month)
                        textInputEditText.setText(SimpleDateFormat("MMMM YYYY").format(cal.time).toString())
                        getMaintenanceList(textInputEditText.text.toString())
                    }, year, month, day)
        }

        if (dialog != null) {
            (dialog.datePicker as ViewGroup).findViewById<ViewGroup>(
                    Resources.getSystem().getIdentifier("day", "id", "android")
            ).visibility = View.GONE
        }

        if (dialog != null) {
            dialog.show()
        }
    }
    override fun onStart() {
        super.onStart()
        firebaseRecyclerAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        firebaseRecyclerAdapter.stopListening()
    }
    private fun getMaintenanceList(maintenanceMonth: String) {
        val options: FirestoreRecyclerOptions<MaintenanceModel>
        options = FirestoreRecyclerOptions.Builder<MaintenanceModel>()
                .setQuery(FirebaseFirestore.getInstance()
                        .collection("Maintenance")
                        .orderBy("maintenanceMonth")
                        .startAt(maintenanceMonth)
                        .endAt(maintenanceMonth + "\uf8ff"), MaintenanceModel::class.java)
                .build()
        firebaseRecyclerAdapter = context?.let { FirebaseRecyclerAdapter2(it, options) }!!
        firebaseRecyclerAdapter.startListening()
        recyclerView.adapter = firebaseRecyclerAdapter
        firebaseRecyclerAdapter.notifyDataSetChanged()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_maintenance, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HistoryMaintenanceFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                HistoryMaintenanceFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}