package com.example.movieapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.Adapters.MyAdapter
import com.example.movieapp.Cache.MySharedPreference
import com.example.movieapp.Models.Movie
import com.example.movieapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var myList: ArrayList<Movie> = ArrayList()
    lateinit var myAdapter: MyAdapter

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addData()
        binding.myRecycle.layoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.myRecycle.addItemDecoration(dividerItemDecoration)
    }

    private fun addData() {
        MySharedPreference.init(this)
        myList = MySharedPreference.stringOb
        myAdapter = MyAdapter(this, myList, object : MyAdapter.OnMyItemClickListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onMyItemClickDelete(movie: Movie, position: Int) {
                myList = MySharedPreference.stringOb
                myList.removeAt(position)
                MySharedPreference.stringOb = myList
                addData()
                myAdapter.notifyDataSetChanged()
                Toast.makeText(this@MainActivity, "Deleted", Toast.LENGTH_SHORT).show()
            }

            override fun onMyItemClickDEdit(movie: Movie, position: Int) {
                val intent = Intent(this@MainActivity, EditActivity::class.java)
                intent.putExtra("position", position)
                startActivity(intent)
            }

            override fun onMyLinClick(movie: Movie, position: Int) {
                val intent = Intent(this@MainActivity, Avengers::class.java)
                intent.putExtra("position", position)
                startActivity(intent)
            }
        })
        binding.myRecycle.adapter = myAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onStart() {
        super.onStart()
        addData()
        myAdapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.new_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.plus -> {
                val intent = Intent(this, AddMovie::class.java)
                startActivity(intent)
                Toast.makeText(this, "Menu added", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}