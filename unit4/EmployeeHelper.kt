package com.example.unit4

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class EmployeeHelper(context: Context, factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION)
{
    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE " + EmployeeHelper.TABLE_NAME + " ("
                + EmployeeHelper.ID_COL + " INTEGER PRIMARY KEY, " +
                EmployeeHelper.NAME_COL + " TEXT, " +
                EmployeeHelper.AGE_COL + " TEXT, " +
                EmployeeHelper.SALARY_COL + "TEXT" +
                ")")
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${DBHelper.TABLE_NAME}")
        onCreate(db)
    }
    companion object{
        private val DATABASE_NAME = "CSE226"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "my_table1"
        val ID_COL = "id"
        val NAME_COL = "name"
        val AGE_COL = "age"
        val SALARY_COL = "salary"
    }
}