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
import com.example.budget.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var viewBinding:ActivitySignUpBinding
    private lateinit var userDb:UserDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewBinding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        userDb = UserDatabaseHelper(this)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewBinding.signupButton.setOnClickListener {
            val email = viewBinding.emailRegisterText.text.toString()
            val password = viewBinding.passwordRegisterText.text.toString()
            val confirmPassword = viewBinding.confirmPasswordTxt.text.toString()

            if(password == confirmPassword){
                val user = User(0,email,password)
                userDb.createUser(user)
                Toast.makeText(this,"Sign Up Successful",Toast.LENGTH_SHORT).show()
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this,"Passwords don't match",Toast.LENGTH_SHORT).show()
            }
        }
    }
}