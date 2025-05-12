package com.example.budget.Databases

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.budget.DataClasses.Category
import com.example.budget.DataClasses.Expense
import kotlin.math.exp

class CategoryDatabase(context: Context):SQLiteOpenHelper(context, Database,null, Version) {
    companion object{
        private const val Database = "Categories"
        private const val Version = 1
        private const val CategoriesTable = "CategoriesTable"
        private const val CategoryId = "CategoryId"
        private const val CategoryTitle = "Title"

        //expense table
        private const val ExpenseTable = "ExpenseTable"
        private const val ExpenseId = "ExpensesDb"
        private const val ExpenseDescription = "Description"
        private const val Category = "Category"
        private const val ExpenseDate = "Date"
        private const val Amount = "Amount"
        private const val Picture = "Picture"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE $CategoriesTable (" +
                "$CategoryId INTEGER PRIMARY KEY," +
                "$CategoryTitle TEXT)"

        db?.execSQL(query)

        //create expense table


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $CategoriesTable"
        db?.execSQL(dropTableQuery)
        onCreate(db)

        //drop expense table


    }
    fun createCategory(category: Category){
        val db = writableDatabase
        val values = ContentValues().apply {

            put(CategoryTitle,category.categoryTitle)
        }
        db.insert(CategoriesTable,null,values)
    }




    fun getCategories():List<Category>{
        val db = readableDatabase
        val categoriesList = mutableListOf<Category>()
        val query = "SELECT * FROM $CategoriesTable"
        val cursor = db.rawQuery(query,null)

        while (cursor.moveToNext()){
            val categoryId = cursor.getInt(cursor.getColumnIndexOrThrow(CategoryId))
            val categoryTitle = cursor.getString(cursor.getColumnIndexOrThrow(CategoryTitle))

            val category = Category(categoryId,categoryTitle)
            categoriesList.add(category)
        }
        cursor.close()
        db.close()
        return categoriesList

    }




}