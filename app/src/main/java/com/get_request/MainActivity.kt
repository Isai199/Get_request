package com.get_request

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.get_request.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {


    private lateinit var queue: RequestQueue

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        queue = Volley.newRequestQueue(this)
        var nombre = binding.etPokemonAmount.text

        binding.btnUpdatePokemon.setOnClickListener{
            getPokemonList(nombre.toString().toInt())

        }




    }

    fun getPokemonList(ListAmount:  Int){
        val url = "https://pokeapi.co/api/v2/pokemon/?limit=${ListAmount}"

        val jsonRequest = JsonObjectRequest(url , Response.Listener<JSONObject>{ response ->
            //response
            Log.i("JSONRESPONSE", response.getJSONArray("results").toString())
        }, Response.ErrorListener { error ->
            //error.messge
            Log.w("JSONRESPONSE",error.message as String)
        })

        queue.add(jsonRequest)

    }

    override fun onStop() {
        super.onStop()
        queue.cancelAll("Stopped")
    }
}