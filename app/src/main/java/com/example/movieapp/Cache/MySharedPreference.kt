package com.example.movieapp.Cache

import android.content.Context
import android.content.SharedPreferences
import com.example.movieapp.Models.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object MySharedPreference {
    private const val NAME = "MyCache"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var stringOb: ArrayList<Movie>
        get() = gsonStringToArray(preferences.getString("data", "[]")!!)
        set(value) = preferences.edit {
            if (value != null) {
                it.putString("data", arrayToGsonString(value))
            }
        }

    private fun arrayToGsonString(arrayList: ArrayList<Movie>): String {
        return Gson().toJson(arrayList)
    }

    private fun gsonStringToArray(gsonString: String): ArrayList<Movie> {
        val typeToken = object : TypeToken<ArrayList<Movie>>() {}.type
        return Gson().fromJson(gsonString, typeToken)
    }
}