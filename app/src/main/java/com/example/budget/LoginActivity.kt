package com.example.budget

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.budget.DataClasses.User
import com.example.budget.Databases.UserDatabaseHelper
import com.example.budget.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var viewBinding:ActivityLoginBinding
    private lateinit var userDb:UserDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        userDb = UserDatabaseHelper(this)

        viewBinding.loginButton.setOnClickListener {
            val email = viewBinding.emailLogInText.text.toString()
            val password = viewBinding.passwordLoginText.text.toString()


            val userExists = userDb.getUser(email,password)
            if(userExists){
                Toast.makeText(this,"Log In Successful",Toast.LENGTH_SHORT).show()
                val HomeIntent = Intent(this,MainActivity::class.java)
                startActivity(HomeIntent)
                finish()
            }
            else{
                Toast.makeText(this,"Login Failed",Toast.LENGTH_SHORT).show()
            }
        }
        viewBinding.signUpRedirectButton.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}