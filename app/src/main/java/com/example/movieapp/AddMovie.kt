package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.movieapp.Cache.MySharedPreference
import com.example.movieapp.Models.Movie
import com.example.movieapp.databinding.ActivityAddMovieBinding

class AddMovie : AppCompatActivity() {
    private lateinit var binding: ActivityAddMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MySharedPreference.init(this)

        binding.btnSave.setOnClickListener {
            val arrayMovie = MySharedPreference.stringOb

            val name = binding.edtMoviesName.text.toString().trim()
            val authors = binding.edtAuthorsMovie.text.toString().trim()
            val about = binding.edtAboutMovies.text.toString().trim()
            val date = binding.edtMovieDate.text.toString().trim()

            if (name != "" && authors != "" && about != "" && date != "") {
                arrayMovie.add(Movie(name, authors, about, date))
                MySharedPreference.stringOb = arrayMovie
                finish()
            } else {
                Toast.makeText(this, "Enter the information", Toast.LENGTH_SHORT).show()
            }
        }
    }
}