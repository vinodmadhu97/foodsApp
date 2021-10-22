package com.example.foodyapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.foodyapp.R
import com.example.foodyapp.databinding.FragmentRecipesBinding

class RecipesFragment : Fragment() {


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