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



            binding.rvListPokemon.adapter = PokemonAdapter(response.getJSONArray("results"))


        }, Response.ErrorListener { error ->
            //error.messge
            Log.w("JSONRESPONSE",error.message as String)
        })

        queue.add(jsonRequest)

        /*val fakeVideoData: Array<JSONObject> =  arrayOf(
            JSONObject("{\"title\": \"Audifono inalambricos sony\", \"price\": \"$ 1,008\", \"meses\": \"en 12x $ 84MSl\", \"manana\": \"llega gratis mañana\", \"vendedor\": \"vendido por MAFE Distribuciones\"}"),
            JSONObject("{\"title\": \"Control inalambricos ps4\", \"price\": \"$ 1,400\", \"meses\": \"en 12x $ 84MSl\", \"manana\": \"llega gratis mañana\", \"vendedor\": \"vendido por MAFE Distribuciones\"}"),
            JSONObject("{\"title\": \"Tv samsung\", \"price\": \"$ 5,999\", \"meses\": \"en 12x $ 84MSl\", \"manana\": \"llega gratis mañana\", \"vendedor\": \"vendido por MAFE Distribuciones\"}"),
            JSONObject("{\"title\": \"Playera nike\", \"price\": \"$ 1,967\", \"meses\": \"en 12x $ 84MSl\", \"manana\": \"llega gratis mañana\", \"vendedor\": \"vendido por MAFE Distribuciones\"}"),
            JSONObject("{\"title\": \"Telefono xiaomi\", \"price\": \"$ 4,300\", \"meses\": \"en 12x $ 84MSl\", \"manana\": \"llega gratis mañana\", \"vendedor\": \"vendido por MAFE Distribuciones\"}")

        )


        binding.rvListPokemon.adapter = PokemonAdapter(fakeVideoData)*/


    }

    override fun onStop() {
        super.onStop()
        queue.cancelAll("Stopped")
    }
}