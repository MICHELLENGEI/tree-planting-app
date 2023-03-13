package com.example.treeplantingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.treeplantingapp.databinding.ActivityLoginBinding
import com.example.treeplantingapp.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class Login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        binding.buttonLogin.setOnClickListener {

            val email = binding.eTLoginEmailAddress.text.toString()
            val password = binding.eTLoginNumberPassword.text.toString()
            if (checkAllField()){
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intentHome = Intent(this, HomeActivity::class.java)
                        startActivity(intentHome)
                        finish()
                    } else {
                        Log.e("error: ", it.exception.toString())
                    }
                }
            }
        }
        binding.textViewSignUp.setOnClickListener {
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun checkAllField(): Boolean {
        val emailS = binding.eTLoginEmailAddress.text.toString()
        if (binding.eTLoginEmailAddress.text.toString() == "") {
            Toast.makeText(this, "Email is a required field", Toast.LENGTH_LONG).show()
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailS).matches()) {
            Toast.makeText(this, "Check the Email Format", Toast.LENGTH_LONG).show()
            return false
        }
        if (binding.eTLoginNumberPassword.text.toString() == "") {
            Toast.makeText(this, "Password is a required field", Toast.LENGTH_LONG).show()
            return false
        }
        if (binding.eTLoginNumberPassword.length() <= 6) {
            Toast.makeText(this, "Password should be at least 8 characters", Toast.LENGTH_LONG)
                .show()
            return false
        }
        return true
    }
}


