package com.example.foodyapp.ui.fragment.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.foodyapp.R
import com.example.foodyapp.databinding.FragmentRecipesBinding

class RecipesFragment : Fragment() {

    //https://api.spoonacular.com/recipes/complexSearch?number=1&apiKey=1d18da579a424add80c151e1ac28dfd2&type=finger%20food&diet=vegen&addRecipeInformation=true&fillIngredients=true
    private var mBinding : FragmentRecipesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentRecipesBinding.inflate(inflater,container,false)

        mBinding!!.recyclerView.showShimmer()
        return mBinding!!.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }


}