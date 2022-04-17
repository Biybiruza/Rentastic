package com.example.rentastic.ui.poster

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rentastic.R
import com.example.rentastic.databinding.FragmentPosterBinding
import com.example.rentastic.ui.MarginItemDecoration

class PosterFragment : Fragment(R.layout.fragment_poster) {

    lateinit var binding: FragmentPosterBinding
    private val adapter = PosterListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPosterBinding.bind(view)
        binding.recyclerView.addItemDecoration(MarginItemDecoration(6,16))


    }

}