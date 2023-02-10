package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapp.Cache.MySharedPreference
import com.example.movieapp.databinding.ActivityAvengersBinding

class Avengers : AppCompatActivity() {
    private lateinit var binding: ActivityAvengersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAvengersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val position = intent.getIntExtra("position",0)
        MySharedPreference.init(this)
        val array = MySharedPreference.stringOb

        val name = array[position].name
        val authors = array[position].author
        val about = array[position].about
        val date = array[position].date

        binding.txtName.text = "${binding.txtName.text} $name"
        binding.txtAuthors.text = "${binding.txtAuthors.text} $authors"
        binding.txtAbout.text = "${binding.txtAbout.text} $about"
        binding.txtData.text = "${binding.txtData.text} $date"

        binding.btnClose.setOnClickListener {
            finish()
        }
    }
}