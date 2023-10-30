package com.driuft.random_pets_starter

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    private lateinit var petList: MutableList<String>
    private lateinit var nameList: MutableList<String>
    private lateinit var heightList: MutableList<String>
    private lateinit var rvPets: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvPets = findViewById(R.id.pet_list)
        petList = mutableListOf()
        nameList = mutableListOf()
        heightList = mutableListOf()
        getDogImageURL()
    }

    private fun getDogImageURL() {
        val client = AsyncHttpClient()

        for(id in 0 until 20){
        client["https://pokeapi.co/api/v2/pokemon/${id}", object : JsonHttpResponseHandler() {
            @SuppressLint("SuspiciousIndentation")
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Dog Success", "$json")
//                pokeImageURL = json.jsonObject.getJSONObject("sprites").getString("front_default")
//                pokeName = json.jsonObject.getString("name")
//                pokeHeight = json.jsonObject.getString("height")
                val petImageArray = json.jsonObject.getJSONObject("sprites").getString("front_default")
                val pokeName = json.jsonObject.getString("name")
                val pokeHeight = json.jsonObject.getString("height")

                    petList.add(petImageArray)
                    nameList.add(pokeName)
                    heightList.add(pokeHeight)
                val adapter = PokemonAdapter(petList, nameList, heightList)
                rvPets.adapter = adapter
                rvPets.layoutManager = LinearLayoutManager(this@MainActivity)

            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Dog Error", errorResponse)
            }
        }]}

    }
}

