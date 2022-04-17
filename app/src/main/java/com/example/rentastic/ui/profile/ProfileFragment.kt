package com.example.rentastic.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.rentastic.R
import com.example.rentastic.databinding.FragmentProfileBinding
import com.example.rentastic.ui.sign.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    lateinit var binding: FragmentProfileBinding
    private val mAuth = FirebaseAuth.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)
        checkUser()
        binding.signBtn.setOnClickListener {
            val intent = Intent(requireContext(),LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkUser(){
        //get current user
        val firebaseUser = mAuth.currentUser
        if (firebaseUser != null){
            binding.tv1.text = firebaseUser?.email
            //menimshe o'shiriw kerek shiǵar
            binding.signBtn.visibility = View.GONE
        }
    }

}