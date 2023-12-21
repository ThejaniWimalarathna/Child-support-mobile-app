package com.example.needclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.needclient.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
      //search
        binding.searchButton.setOnClickListener {
            val searchName:String=binding.searchName.text.toString()
            if (searchName.isNotEmpty()){
                readData(searchName)
            }else{
                Toast.makeText(this,"Please enter the name",Toast.LENGTH_SHORT).show()
            }
        }
    }

    //connect firebase
     private fun readData(name:String ){
        databaseReference= FirebaseDatabase.getInstance().getReference("Need Items")
        databaseReference.child(name).get().addOnSuccessListener {
            if (it.exists()){
                val name= it.child("name").value
                val needItems= it.child("needItems").value
                val position= it.child("position").value
                val phone= it.child("phone").value

                Toast.makeText(this,"Results Found",Toast.LENGTH_SHORT).show()
                binding.searchName.text.clear()
                binding.readName.text=name.toString()
                binding.readNeedItems.text=needItems.toString()
                binding.readPosition.text=position.toString()
                binding.readPhone.text=phone.toString()
            }else{
                Toast.makeText(this,"Name does not exist",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
        }
    }


}