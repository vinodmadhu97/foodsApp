package com.example.foodyapp.ui.fragment.foodJoke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.foodyapp.R
import com.example.foodyapp.databinding.FragmentFoodJokesBinding

class FoodJokesFragment : Fragment() {

    private var mBinding: FragmentFoodJokesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentFoodJokesBinding.inflate(inflater,container,false)
        return mBinding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }


}