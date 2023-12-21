package com.example.needadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.needadmin.databinding.ActivityMainBinding
import com.example.needadmin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.ref.Reference

class UploadActivity : AppCompatActivity() {

    private lateinit var binding:ActivityUploadBinding
    private lateinit var databaseReference:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener{
            val name = binding.uploadName.text.toString()
            val position = binding.uploadPosition.text.toString()
            val needItems = binding.uploadNeedItems.text.toString()
            val phone = binding.uploadPhone.text.toString()

         //connect firebase
            databaseReference =FirebaseDatabase.getInstance().getReference("Need Items")
            val users =UserData(name, position, needItems, phone)
            databaseReference.child(name).setValue(users).addOnSuccessListener {
                binding.uploadName.text.clear()
                binding.uploadPosition.text.clear()
                binding.uploadNeedItems.text.clear()
                binding.uploadPhone.text.clear()

                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@UploadActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener{
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }


        }




    }
}