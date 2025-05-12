package com.example.budget.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budget.Databases.ExpensesDatabaseHelper
import com.example.budget.ExpenseAdapter
import com.example.budget.R
import com.example.budget.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

private lateinit var viewBinding:FragmentHomeBinding
private lateinit var expenseDb:ExpensesDatabaseHelper
private lateinit var expenseAdapter: ExpenseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        expenseDb = ExpensesDatabaseHelper(viewBinding.root.context)
        expenseAdapter = ExpenseAdapter(expenseDb.getAllExpenses(),viewBinding.root.context)

        viewBinding.transactionsRecyclerView.layoutManager = LinearLayoutManager(viewBinding.root.context)
        viewBinding.transactionsRecyclerView.adapter = expenseAdapter

        viewBinding.sumTotalTextView.text = "R ${expenseDb.getExpenseAmountTotal()}"
    }


}