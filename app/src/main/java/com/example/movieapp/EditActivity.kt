package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.movieapp.Cache.MySharedPreference
import com.example.movieapp.Models.Movie
import com.example.movieapp.databinding.ActivityAddMovieBinding

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val position = intent.getIntExtra("position",0)
        println(position)
        MySharedPreference.init(this)
        val array =MySharedPreference.stringOb
        binding.edtMoviesName.setText(array[position].name)
        binding.edtAuthorsMovie.setText(array[position].author)
        binding.edtAboutMovies.setText(array[position].about)
        binding.edtMovieDate.setText(array[position].date)

        binding.btnSave.text = "Edit"

        binding.btnSave.setOnClickListener {
            val name =  binding.edtMoviesName.text.toString().trim()
            val author =  binding.edtAuthorsMovie.text.toString().trim()
            val about =  binding.edtAboutMovies.text.toString().trim()
            val date =  binding.edtMovieDate.text.toString().trim()

            array[position] = Movie(name,author, about, date)
            MySharedPreference.stringOb =array
            Toast.makeText(this, "Edit movies", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}