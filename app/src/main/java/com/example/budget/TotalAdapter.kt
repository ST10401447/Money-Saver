package com.example.budget

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.budget.DataClasses.Totals
import org.w3c.dom.Text

class TotalAdapter(private var totals:List<Totals>,context: Context):
RecyclerView.Adapter<TotalAdapter.TotalsViewHolder>(){

    class TotalsViewHolder(totalItemView:View):RecyclerView.ViewHolder(totalItemView) {
        val categoryTitles:TextView = totalItemView.findViewById(R.id.CategoryHolderTextView)
        val totalAmount:TextView = totalItemView.findViewById(R.id.AmountTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TotalsViewHolder {
        val totalView = LayoutInflater.from(parent.context).inflate(R.layout.total_view,parent,false)
        return TotalsViewHolder(totalView)
    }

    override fun getItemCount(): Int {
        return totals.size
    }

    override fun onBindViewHolder(holder: TotalsViewHolder, position: Int) {
        val total = totals[position]
        holder.categoryTitles.text = total.category
        holder.totalAmount.text = "R ${total.total}"
    }
}