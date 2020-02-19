package com.example.myproject.ui.post


import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.R
import com.example.myproject.adapter.NewfeedAdapter
import com.example.myproject.model.Report
import com.example.myproject.model.RequestPost
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.fragment_post.*

/**
 * A simple [Fragment] subclass.
 */
class PostFragment : Fragment() {

    lateinit var alertDialog: AlertDialog
    lateinit var storageReference: StorageReference
    //for insert
    lateinit var editTextName: EditText
    lateinit var editTextPhone: EditText
    lateinit var editTextAddress: EditText
    lateinit var editTextPrice: EditText
    lateinit var buttonSend: Button
    lateinit var ref: DatabaseReference

    companion object {
        private val PICK_IMAGE_CODE = 1000
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root=inflater.inflate(R.layout.fragment_post, container, false)

        //For Insert
        editTextName = root.findViewById(R.id.value_post_name)
        editTextPhone = root.findViewById(R.id.value_post_phone)
        editTextAddress = root.findViewById(R.id.value_post_address)
        editTextPrice = root.findViewById(R.id.value_post_price)
        buttonSend=root.findViewById(R.id.btn_request_post)

        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_CODE)
        {
            alertDialog.show()
            val uploadTask = storageReference!!.putFile(data!!.data!!)
            val task=uploadTask.continueWithTask{
                    task ->
                if (!task.isSuccessful)
                {
                    Toast.makeText(activity,"Failed", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(activity,"You are posted", Toast.LENGTH_SHORT).show()
                }
                storageReference!!.downloadUrl
            }.addOnCompleteListener { task ->
                if(task.isSuccessful){
                    val downloadUrl=task.result
                    val url=downloadUrl!!.toString()
                    Log.d("DIRECTLINK",url)
                    alertDialog.dismiss()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ref = FirebaseDatabase.getInstance().getReference("Request Post")
        //For Insert
        buttonSend.setOnClickListener {
            savePost()
        }

        //Init
        alertDialog = SpotsDialog.Builder().setContext(context).build();




        //Event
        btn_upload.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "SelectPicture"), PICK_IMAGE_CODE)
            val random=(0 until 1000).shuffled().last()
            storageReference = FirebaseStorage.getInstance().getReference("$random")
        }
    }

    //For Insert
    fun savePost(){
        val name = editTextName.text.toString().trim()
        val phone = editTextPhone.text.toString().trim()
        val address = editTextAddress.text.toString().trim()
        val price = editTextPrice.text.toString().trim()
        if (phone.isEmpty()) {
            editTextPhone.error = "Please enter a phone"
            return
        }
        if (name.isEmpty()) {
            editTextPhone.error = "Please enter a name"
            return
        }
        if (address.isEmpty()) {
            editTextPhone.error = "Please enter a address"
            return
        }
        if (price.isEmpty()) {
            editTextPhone.error = "Please enter a price"
            return
        }

        val requestReportID = ref.push().key.toString()
        val post = RequestPost(requestReportID, name, phone,address,price)

        ref.child(requestReportID).setValue(post).addOnCompleteListener {
            Toast.makeText(activity, "ပေးပို့ပြီးပါပြီ", Toast.LENGTH_LONG).show()
            view?.findNavController()?.navigate(R.id.action_postFragment_to_nav_home)
        }
    }



}


