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
import com.example.budget.TotalAdapter
import com.example.budget.databinding.FragmentOptionsBinding


class Options : Fragment() {

    private lateinit var viewBinding: FragmentOptionsBinding
    private lateinit var expenseDb: ExpensesDatabaseHelper
    private lateinit var totalAdapter: TotalAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentOptionsBinding.inflate(layoutInflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        expenseDb = ExpensesDatabaseHelper(viewBinding.root.context)
        totalAdapter = TotalAdapter(expenseDb.getCategoryTotals(),viewBinding.root.context)

        viewBinding.totalsRecyclerView.layoutManager = LinearLayoutManager(viewBinding.root.context)
        viewBinding.totalsRecyclerView.adapter = totalAdapter
    }
}