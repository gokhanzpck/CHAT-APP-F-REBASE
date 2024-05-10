package com.gokhanzopcuk.appchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gokhanzopcuk.appchat.databinding.ActivityMainBinding
import com.google.firebase.Firebase

class MainActivity : AppCompatActivity() {
   private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



    }
}