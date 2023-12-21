package com.example.needadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.needadmin.databinding.ActivityUpdateBinding
import com.example.needadmin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {

    private  lateinit var binding:ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateButton.setOnClickListener {
            val updateName= binding.updateName.text.toString()
            val updatePosition= binding.updatePosition.text.toString()
            val referenceNeedItems= binding.referenceNeedItems.text.toString()
            val updatePhone= binding.updatePhone.text.toString()

            updateData(updateName,updatePosition,referenceNeedItems,updatePhone)
        }
    }
     //connect firebase
    private fun updateData(name:String,position:String,needItems:String,phone:String){
        databaseReference=FirebaseDatabase.getInstance().getReference("Need Items")
        val user= mapOf<String,String>("name" to name,"position" to position,"needItems" to needItems,"phone" to phone)
        databaseReference.child(name).updateChildren(user).addOnSuccessListener {
            binding.updateName.text.clear()
            binding.updatePosition.text.clear()
            binding.referenceNeedItems.text.clear()
            binding.updatePhone.text.clear()
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this, "Unable to update ", Toast.LENGTH_SHORT).show()

        }
    }
}