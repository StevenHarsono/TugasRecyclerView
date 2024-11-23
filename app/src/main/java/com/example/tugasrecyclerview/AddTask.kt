package com.example.tugasrecyclerview

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Date
import kotlin.math.tan

class AddTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_task)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var buttonAdd = findViewById<Button>(R.id.addButton)
        buttonAdd.setOnClickListener {
            val titleText = findViewById<EditText>(R.id.editText).text
            val desc = findViewById<EditText>(R.id.editText2).text
            val tanggal = findViewById<DatePicker>(R.id.datePicker)

            val day = tanggal.dayOfMonth
            val month = tanggal.month + 1
            val year = tanggal.year

            val date = "$day-$month-$year"

            val task = Task(titleText.toString(), desc.toString(), date)

            MainActivity.dataTask.add(task)
            val intent = Intent(this@AddTask, MainActivity::class.java)
            startActivity(intent)
        }

    }
}