package com.example.rentastic.ui.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.rentastic.databinding.ActivityLoginBinding
import com.example.rentastic.ui.profile.ProfileFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.login.setOnClickListener {
            if (binding.username.text.isNotEmpty() && binding.password.text.isNotEmpty()){
                binding.progressBar.visibility = View.VISIBLE
                mAuth.signInWithEmailAndPassword(binding.username.text.toString(), binding.password.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful){
                            binding.progressBar.visibility = View.GONE
                            val currentUser = mAuth.currentUser
                            updateUI(currentUser)
                        } else {
                            Toast.makeText(this,it.exception?.localizedMessage,Toast.LENGTH_LONG).show()
                            binding.progressBar.visibility = View.GONE
                        }
                    }
            } else {
                Toast.makeText(this, "Polyalardı toldırıń", Toast.LENGTH_LONG).show()
            }
        }
        binding.register.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(user: FirebaseUser?){
        if (user != null){
            val intent = Intent(this,ProfileFragment::class.java)
            startActivity(intent)
            finish()
        }
    }
}