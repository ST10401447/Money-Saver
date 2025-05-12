package com.example.budget.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budget.Databases.CategoryDatabase
import com.example.budget.Databases.ExpensesDatabaseHelper
import com.example.budget.ExpenseAdapter
import com.example.budget.R
import com.example.budget.databinding.ExpenseViewBinding
import com.example.budget.databinding.FragmentChartBinding


class ChartFragment : Fragment() {

    private lateinit var viewBinding: FragmentChartBinding
    private lateinit var expenseDb:ExpensesDatabaseHelper
    private lateinit var expenseAdapter: ExpenseAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentChartBinding.inflate(layoutInflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        expenseDb = ExpensesDatabaseHelper(viewBinding.root.context)
        expenseAdapter = ExpenseAdapter(expenseDb.getAllExpenses(),viewBinding.root.context)

        viewBinding.expensesRecyclerView.layoutManager = LinearLayoutManager(viewBinding.root.context)
        viewBinding.expensesRecyclerView.adapter = expenseAdapter
    }


}