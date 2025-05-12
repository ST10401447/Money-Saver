package com.example.budget.Databases

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.budget.DataClasses.Expense
import com.example.budget.DataClasses.Totals


class ExpensesDatabaseHelper(context:Context):SQLiteOpenHelper(context, Database,null, Version) {
    companion object{
        private const val Database = "ExpensesDb"
        private const val Version = 1
        private const val ExpenseTable = "ExpenseTable"
        private const val ExpenseId = "ExpensesDb"
        private const val ExpenseDescription = "Description"
        private const val Category = "Category"
        private const val ExpenseDate = "Date"
        private const val Amount = "Amount"
        private const val Picture = "Picture"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createExpenseTable = "CREATE TABLE $ExpenseTable (" +
                "$ExpenseId INTEGER PRIMARY KEY," +
                "$ExpenseDescription TEXT," +
                "$Category TEXT," +
                "$ExpenseDate TEXT," +
                "$Amount DOUBLE," +
                "$Picture BLOB)"
        db?.execSQL(createExpenseTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropExpenseTable = "DROP TABLE IF EXISTS $ExpenseTable"
        db?.execSQL(dropExpenseTable)
        onCreate(db)
    }
    fun createExpense(expense: Expense){
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put(ExpenseDescription,expense.expenseDescription)
            put(Category,expense.category)
            put(ExpenseDate,expense.expenseDate)
            put(Amount,expense.amount)
            put(Picture,expense.picture)
        }
        db.insert(ExpenseTable,null,contentValues)
    }
    fun getAllExpenses():List<Expense>{
        val db = readableDatabase
        val expensesList = mutableListOf<Expense>()
        val query= "SELECT * FROM $ExpenseTable"
        val expCursor = db.rawQuery(query,null)

        while(expCursor.moveToNext()){
            val expenseId = expCursor.getInt(expCursor.getColumnIndexOrThrow(ExpenseId))
            val expenseDescription = expCursor.getString(expCursor.getColumnIndexOrThrow(
               ExpenseDescription
            ))
            val category = expCursor.getString(expCursor.getColumnIndexOrThrow(Category))
            val expenseDate = expCursor.getString(expCursor.getColumnIndexOrThrow(ExpenseDate))
            val expenseAmount = expCursor.getDouble(expCursor.getColumnIndexOrThrow(Amount))
            val picture = expCursor.getBlob(expCursor.getColumnIndexOrThrow(Picture))

            val expense = Expense(expenseId,expenseDescription,category,expenseDate,expenseAmount,picture)
            expensesList.add(expense)
        }
        expCursor.close()
        db.close()
        return expensesList
    }

    fun getCategoryTotals():List<Totals>{
        val db = readableDatabase
        val totals = mutableListOf<Totals>()
        val query = "SELECT $Category, SUM($Amount) AS $Amount FROM $ExpenseTable GROUP BY $Category"
        val cursor = db.rawQuery(query,null)

        var m = 0
        while(cursor.moveToNext()){
            val category = cursor.getString(cursor.getColumnIndexOrThrow(Category))
            val totalAmount = cursor.getDouble(cursor.getColumnIndexOrThrow(Amount))
            val total = Totals(m,category,totalAmount)
            totals.add(total)
            m++

        }
        return totals
    }
    fun getExpenseAmountTotal():Double{
        val db = readableDatabase
        val amountTotal:Double
        val query = "SELECT $Amount FROM $ExpenseTable"
        val cursor = db.rawQuery(query,null)

        var sumTotal:Double = 0.0
        while (cursor.moveToNext()){
            val amounnt = cursor.getDouble(cursor.getColumnIndexOrThrow(Amount))
            sumTotal=sumTotal+amounnt

        }
        return sumTotal
    }

}