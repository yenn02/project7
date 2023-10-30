package com.driuft.random_pets_starter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PokemonAdapter(private val petList: List<String>, private val nameList:List<String>, private val heightList:List<String>): RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val pokemonImage: ImageView
        val pokemonName: TextView
        val pokemonHeight: TextView


        init {
            // Find our RecyclerView item's ImageView for future use
            pokemonImage = view.findViewById(R.id.pet_image)
            pokemonName = view.findViewById(R.id.poke_name)
            pokemonHeight = view.findViewById(R.id.poke_height)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() =  petList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(petList[position])
            .centerCrop()
            .into(holder.pokemonImage)

        holder.pokemonName.setText("Name:" + nameList[position])
        holder.pokemonHeight.setText("Height:"+heightList[position])
    }

}