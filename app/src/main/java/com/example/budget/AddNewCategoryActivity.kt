package com.example.budget

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.budget.DataClasses.Category
import com.example.budget.Databases.CategoryDatabase
import com.example.budget.Fragments.BudgetFragment
import com.example.budget.databinding.ActivityAddNewCategoryBinding

class AddNewCategoryActivity : AppCompatActivity() {
    private lateinit var viewBinding:ActivityAddNewCategoryBinding
    private lateinit var categoryDb:CategoryDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewBinding = ActivityAddNewCategoryBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        categoryDb = CategoryDatabase(this)
       viewBinding.createButton.setOnClickListener{
           val categoryTitle =viewBinding.categoryEditText.text.toString()

           if(categoryTitle != ""){
               val category = Category(0,categoryTitle)
               categoryDb.createCategory(category)
               Toast.makeText(this,"Category Saved",Toast.LENGTH_SHORT).show()
           }
           else{
               Toast.makeText(this,"Category title is missing",Toast.LENGTH_SHORT).show()
           }
       }
    }
}