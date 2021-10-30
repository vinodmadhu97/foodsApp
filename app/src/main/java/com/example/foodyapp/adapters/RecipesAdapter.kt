package com.example.foodyapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodyapp.databinding.RecipesRowLayoutBinding
import com.example.foodyapp.models.FoodRecipe
import com.example.foodyapp.models.Result
import com.example.foodyapp.utils.RecipeDiffUtil

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.MyViewHolder>() {

    private var recipes  = emptyList<Result>()

    class MyViewHolder(private val binding : RecipesRowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Result){
            binding.result = result
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup):MyViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecipesRowLayoutBinding.inflate(layoutInflater,parent,false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentRecipe = recipes[position]
        holder.bind(currentRecipe)

    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    fun setData(newRecipe:FoodRecipe){
        val diffUtil = RecipeDiffUtil(recipes,newRecipe.results)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        recipes =newRecipe.results
        diffUtilResult.dispatchUpdatesTo(this)
    }
}