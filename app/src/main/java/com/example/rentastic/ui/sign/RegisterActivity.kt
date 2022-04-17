package com.example.rentastic.ui.sign

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rentastic.R
import com.example.rentastic.databinding.ActivityRegisterBinding
import com.example.rentastic.ui.profile.ProfileFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class RegisterActivity : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()
    lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var binding: ActivityRegisterBinding

    private companion object{
        private const val RC_SIGN_IN = 100
        private const val TAG = "GOOGLE_SING_IN_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //register button onClicked
        binding.register.setOnClickListener {
            if (binding.name.text.isNotEmpty() && binding.username.text.isNotEmpty()
                && binding.password.text.isNotEmpty()){
                binding.loading.visibility = View.VISIBLE
                mAuth.createUserWithEmailAndPassword(binding.username.text.toString(),binding.password.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful){
                           val user = mAuth.currentUser
                           binding.loading.visibility = View.VISIBLE
                           updateUI(user)
                        } else {
                            Toast.makeText(this,it.exception?.localizedMessage, Toast.LENGTH_LONG).show()
                            binding.loading.visibility = View.GONE
                        }
                    }
            }else {
                Toast.makeText(this, "Polyalardı toldırıń", Toast.LENGTH_LONG).show()
            }
        }
        //google sign in
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.your_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions)
        val user = mAuth.currentUser
        updateUI(user)
        binding.googleRegister.setOnClickListener {
            Log.d(TAG, "onCreate: begin google signIn")
            val intent = googleSignInClient.signInIntent
            startActivityForResult(intent, RC_SIGN_IN)
        }

    }

    private fun updateUI(user:FirebaseUser?){
        if (user != null) {
            val intent = Intent(this, ProfileFragment::class.java)
            startActivity(intent)
        }
    }

//    private fun checkUser(){
//        val firebaseUser = mAuth.currentUser
//        if (firebaseUser != null){
//            val intent = Intent(this, ProfileFragment::class.java)
//            startActivity(intent)
//            finish()
//        }
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN){
            Log.d(TAG, "onActivityResult: Google signIn intent result")
            val accountTask = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                //Google signIn success, now auth with firebase
                val account = accountTask.getResult(ApiException::class.java)
                firebaseAuthWithAccount(account)
            }
            catch (e: Exception){
                //failed Google singIn
                Log.d(TAG, "onActivityResult: ${e.message}")
            }
        }
    }

    private fun firebaseAuthWithAccount(account: GoogleSignInAccount?) {
        Log.d(TAG, "firebaseAuthWithAccount: begin firebase auth with account")
        val credentail = GoogleAuthProvider.getCredential(account!!.idToken,null)
            mAuth.signInWithCredential(credentail)
                .addOnSuccessListener {
                    //login success
                    Log.d(TAG, "firebaseAuthWithAccount: loggedIn")

                    //get LoggedIn user
                    val firebaseUser = mAuth.currentUser
                    //get user info
                    val uid = firebaseUser!!.uid
                    val email = firebaseUser.email
                    Log.d(TAG, "firebaseAuthWithAccount: Uid: $uid")
                    Log.d(TAG, "firebaseAuthWithAccount: Email: $email")

                    //check if user is new or existing
                    if(it.additionalUserInfo!!.isNewUser){
                        //user is new - Account created
                        Log.d(TAG, "firebaseAuthWithAccount: Account created... \n$email")
                        Toast.makeText(this, "Account created... \n$email", Toast.LENGTH_LONG).show()
                    } else {
                        //existing user - LoggedIn
                        Log.d(TAG, "firebaseAuthWithAccount: Existing user... \n$email")
                        Toast.makeText(this, "LoggedIn... \n$email", Toast.LENGTH_LONG).show()
                    }
                    //start profile activity

                }
                .addOnFailureListener {
                    //login failed
                    Log.d(TAG, "firebaseAuthWithAccount: Login Failed due to $it")
                    Toast.makeText(this, "Login Failed due to $it", Toast.LENGTH_LONG).show()
                }
    }

}