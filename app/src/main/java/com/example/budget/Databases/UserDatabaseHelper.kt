package com.example.budget.Databases

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.budget.DataClasses.User

class UserDatabaseHelper(context: Context):
SQLiteOpenHelper(context, Database,null, Version){
    companion object{
        private const val Database = "Users"
        private const val Version = 1
        private const val Table = "UsersTable"
        private const val UserId = "UserId"
        private const val EmailAddress = "Email"
        private const val Password = "Password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableUsers = "CREATE TABLE $Table (" +
                "$UserId INTEGER PRIMARY KEY," +
                "$EmailAddress Text," +
                "$Password Text)"
        db?.execSQL(createTableUsers)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTale = "DROP TABLE IF EXISTS $Table"
        db?.execSQL(dropTale)
        onCreate(db)
    }

    fun createUser(user: User){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(EmailAddress,user.userEmail)
            put(Password,user.password)
        }
        db.insert(Table,null,values)
    }

    fun getUser(email:String,password:String):Boolean{
        val db = readableDatabase
        val selection = "$EmailAddress = ? AND $Password = ?"
        val selectionArgs = arrayOf(email,password)
        val cursor = db.query(Table,null,selection,selectionArgs,null,null,null)

        val userExists = cursor.count > 0
        cursor.close()
        return userExists
    }
}