package com.example.shyariapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shyariapp.adapter.AllShyariAdapter
import com.example.shyariapp.databinding.ActivityAllShyariBinding
import com.example.shyariapp.model.ShyariModel
import com.google.firebase.firestore.FirebaseFirestore

class AllShyariActivity : AppCompatActivity() {
    lateinit var binding: ActivityAllShyariBinding
    lateinit var db:FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllShyariBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val name = intent.getStringExtra("name")
        val id = intent.getStringExtra("id")



        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.catName.text = name.toString()


        db = FirebaseFirestore.getInstance()
        db.collection("shyari").document(id!!).collection("all")
            .addSnapshotListener { value, error ->

            val shyariList = arrayListOf<ShyariModel>()
            val data = value?.toObjects(ShyariModel::class.java)
            shyariList.addAll(data!!)

            binding.rcvAllShayari.layoutManager = LinearLayoutManager(this)
            binding.rcvAllShayari.adapter =
                AllShyariAdapter(this, shyariList)
        }
    }
}