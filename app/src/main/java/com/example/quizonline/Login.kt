package com.example.quizonline

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizonline.databinding.ActivityLoginBinding
import com.example.quizonline.databinding.ActivitySignupBinding

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        binding.loginButton.setOnClickListener {
            val loginUsername = binding.loginUsername.text.toString()
            val loginPassword = binding.loginPassword.text.toString()
            loginDatabase(loginUsername,loginPassword)
        }
        binding.signupRedirect.setOnClickListener {
            val intent = Intent(this,signup::class.java)
            startActivity(intent)
            finish()
        }
        }

    private fun loginDatabase(username: String,password: String){
        val userExists = databaseHelper.readUser(username,password)
        if (userExists){
           Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show()
           val intent = Intent(this,MainActivity::class.java)
           startActivity(intent)
           finish()
        }else{
            Toast.makeText(this,"Login Failed", Toast.LENGTH_SHORT).show()
        }
    }
}
