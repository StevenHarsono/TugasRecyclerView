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

class DetailTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_task)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val task = MainActivity.dataTask.get(position.toInt())

        var title = findViewById<EditText>(R.id.editTitle)
        var desc = findViewById<EditText>(R.id.editDesc)
        var date = findViewById<DatePicker>(R.id.datePicker)
        val button = findViewById<Button>(R.id.button4)

        title.setText(task.nama)
        desc.setText(task.deskripsi)

        val tanggalTask = task.tanggal?.split("-")
        if (tanggalTask != null) {
            date.updateDate(
                tanggalTask[2].toInt(),
                tanggalTask[1].toInt() - 1,
                tanggalTask[0].toInt()
            )
        }

        button.setOnClickListener {
            title = findViewById(R.id.editTitle)
            desc = findViewById(R.id.editDesc)

            val day = date.dayOfMonth
            val month = date.month + 1
            val year = date.year

            val date = "$day-$month-$year"

            MainActivity.dataTask.set(position.toInt(),Task(title.text.toString(), desc.text.toString(), date))
            val intent = Intent(this@DetailTask, MainActivity::class.java)
            startActivity(intent)
        }

    }

    companion object {
        var position = "-1"
    }
}