package com.example.needadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.needadmin.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteBinding
    private lateinit var databaseReference: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deleteButton.setOnClickListener {
            val name=binding.deleteName.text.toString()
            if (name.isNotEmpty()){
                deleteData(name)
            }else{
                Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show()
            }
        }
    }
    //connect firebase
    private fun deleteData(name: String){
        databaseReference=FirebaseDatabase.getInstance().getReference("Need Items")
        databaseReference.child(name).removeValue().addOnSuccessListener {
            binding.deleteName.text.clear()
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
        }.addOnSuccessListener {
            Toast.makeText(this, "Unable to delete", Toast.LENGTH_SHORT).show()
        }

    }
}