package com.example.budget.DataClasses

data class Expense
    (val expenseId:Int,
     val expenseDescription:String,
     val category:String,
     val expenseDate:String,
     val amount:Double,
     val picture:ByteArray)
