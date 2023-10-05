package com.example.unit4

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class DBDemoActivity : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dbdemo)
        val addName: Button = findViewById(R.id.button)
        val printName: Button = findViewById(R.id.button2)
        val updateName: Button = findViewById(R.id.button3)
        val deleteDetails: Button = findViewById(R.id.button4)
        val enterName : EditText = findViewById(R.id.editTextText)
        val enterAge : EditText = findViewById(R.id.editTextText2)
        val Name : TextView = findViewById(R.id.textView)
        val Age : TextView = findViewById(R.id.textView2)
        addName.setOnClickListener {
            val db = DBHelper(this, null)

            val name = enterName.text.toString()
            val age = enterAge.text.toString()

            db.addName(name, age)
            Toast.makeText(this, "$name added to database", Toast.LENGTH_LONG).show()

            enterName.text.clear()
            enterAge.text.clear()
        }
        updateName.setOnClickListener {
            val dbHandler = DBHelper(this, null)
            dbHandler.updateName(
                enterName.text.toString(),
                enterAge.text.toString()
            )
            Toast.makeText(this, "Name Updated", Toast.LENGTH_LONG).show()
        }
        deleteDetails.setOnClickListener {
            val db = DBHelper(this, null)
            db.DeleteDetails(
                enterAge.text.toString()
            )
            Toast.makeText(this, "Deleted", Toast.LENGTH_LONG).show()
        }


        printName.setOnClickListener{
            val db = DBHelper(this, null)
            val cursor = db.getName()
            Name.text="Name\n\n"
            Age.text="Age\n\n"
            cursor!!.moveToFirst()
            Name.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COL))
                    + "\n")
            Age.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL))
                    + "\n")
            while(cursor.moveToNext()){
                Name.append(cursor.getString(cursor.getColumnIndex
                    (DBHelper.NAME_COL)) + "\n")
                Age.append(cursor.getString
                    (cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")
            }
            cursor.close()
        }
    }
}