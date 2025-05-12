package com.example.budget.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budget.CategoryAdapter
import com.example.budget.Databases.CategoryDatabase
import com.example.budget.R
import com.example.budget.databinding.FragmentCategoriesBinding


class CategoriesFragment : Fragment() {
    private lateinit var viewBinding:FragmentCategoriesBinding
    private lateinit var categoriesDb:CategoryDatabase
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentCategoriesBinding.inflate(layoutInflater,container,false)
        return viewBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        categoriesDb = CategoryDatabase(viewBinding.root.context)
        categoryAdapter = CategoryAdapter(categoriesDb.getCategories(),viewBinding.root.context)

        viewBinding.categoriesRecyclerView.layoutManager = LinearLayoutManager(viewBinding.root.context)
        viewBinding.categoriesRecyclerView.adapter = categoryAdapter

        viewBinding.newCategoryButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_categoriesFragment_to_createCategoryFragment)
        }
    }

}