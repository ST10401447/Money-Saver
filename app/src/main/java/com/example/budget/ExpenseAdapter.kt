package com.example.budget

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.budget.DataClasses.Expense
import kotlin.math.exp

class ExpenseAdapter(private var expenseList: List<Expense>,context: Context):
RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>(){

    class ExpenseViewHolder(expenseView:View):RecyclerView.ViewHolder(expenseView) {
        val categoryTitle:TextView = expenseView.findViewById(R.id.categoryTextView6)
        val amount:TextView = expenseView.findViewById(R.id.AmountTextView)
        val date:TextView = expenseView.findViewById(R.id.dateTextView)
        val picture:ImageView = expenseView.findViewById(R.id.PictureReceipt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val expenseView = LayoutInflater.from(parent.context).inflate(R.layout.expense_view,parent,false)
        return ExpenseViewHolder(expenseView)
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = expenseList[position]
        holder.categoryTitle.text = expense.category
        holder.amount.text = "R ${expense.amount}"
        holder.date.text = expense.expenseDate
        holder.picture.setImageBitmap(byteArrayToBitmap(expense.picture))
    }
    fun byteArrayToBitmap(byteArray: ByteArray): Bitmap? {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
}