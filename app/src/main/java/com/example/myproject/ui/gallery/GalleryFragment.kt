package com.example.myproject.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myproject.MainActivity
import com.example.myproject.R
import com.example.myproject.model.Report
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.core.Context

class GalleryFragment : Fragment() {

    //For Insert
    lateinit var editTextPhone: EditText
    lateinit var editTextContext: EditText
    lateinit var buttonSend: Button
    lateinit var ref: DatabaseReference

    private lateinit var galleryViewModel: GalleryViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {


        val root = inflater.inflate(R.layout.fragment_gallery, container, false)

        editTextPhone = root.findViewById(R.id.value_report_name)
        editTextContext=root.findViewById(R.id.report_context)
        buttonSend=root.findViewById(R.id.btn_send)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ref = FirebaseDatabase.getInstance().getReference("Report")
        //For Insert
        buttonSend.setOnClickListener {
            saveReport()
        }
    }
    fun saveReport(){
        val phone = editTextPhone.text.toString().trim()
        val context=editTextContext.text.toString().trim()
        if (phone.isEmpty()) {
            editTextPhone.error = "Please enter a name (or) phone"
            return
        }
        if (context.isEmpty()) {
            editTextContext.error = "Please enter a Context"
            return
        }
        val reportID = ref.push().key.toString()
        val report = Report(reportID, phone, context)

        ref.child(reportID).setValue(report).addOnCompleteListener {
            Toast.makeText(activity, "ပေးပို့ပြီးပါ", Toast.LENGTH_LONG).show()
        }
    }

}