package com.example.budget.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budget.AddNewCategoryActivity
import com.example.budget.CategoryAdapter
import com.example.budget.Databases.CategoryDatabase
import com.example.budget.R
import com.example.budget.databinding.FragmentBudgetBinding


class BudgetFragment : Fragment() {

    private lateinit var viewBinding:FragmentBudgetBinding
    private lateinit var categoryAdapter:CategoryAdapter
    private lateinit var categoryDb:CategoryDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewBinding = FragmentBudgetBinding.inflate(layoutInflater,container,false)
        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryDb = CategoryDatabase(viewBinding.root.context)
        categoryAdapter = CategoryAdapter(categoryDb.getCategories(),viewBinding.root.context)

        viewBinding.addNewCategoryFloatingActionButton2.setOnClickListener {
            val addIntent = Intent(viewBinding.root.context,AddNewCategoryActivity::class.java)
            startActivity(addIntent)
        }
        viewBinding.categoriesRecyclerView.layoutManager = LinearLayoutManager(viewBinding.root.context)
        viewBinding.categoriesRecyclerView.adapter = categoryAdapter

    }

}