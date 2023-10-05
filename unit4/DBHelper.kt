package com.example.unit4

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION)
{
    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                NAME_COL + " TEXT, " +
                AGE_COL + " TEXT" + ")")
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addName(name: String, age: String){
        val values = ContentValues()
        values.put(NAME_COL, name)
        values.put(AGE_COL, age)

        val db =  this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }
    fun getName(): Cursor?{
        val db = this.readableDatabase

        return db.rawQuery("SELECT * FROM $TABLE_NAME ", null)
    }

    fun updateName(name: String, age: String?){
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(NAME_COL, name)
        values.put(AGE_COL, age)

        db.update(TABLE_NAME, values, "NAME=?", arrayOf(name))
        db.close()
    }
    fun DeleteDetails(age: String?){
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "AGE=?", arrayOf(age))
        db.close()
    }
/*
    fun deleteName(id: Int) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "$ID_COL = ?", arrayOf(id.toString()))
        db.close()
    }*/

    companion object{
        private val DATABASE_NAME = "CSE226"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "my_table1"
        val ID_COL = "id"
        val NAME_COL = "name"
        val AGE_COL = "age"
    }

}

