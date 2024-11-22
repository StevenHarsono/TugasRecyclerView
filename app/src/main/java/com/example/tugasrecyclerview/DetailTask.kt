package com.example.tugasrecyclerview

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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
        val button = findViewById<Button>(R.id.button4)

        title.setText(task.nama)
        desc.setText(task.deskripsi)

        button.setOnClickListener {
            title = findViewById(R.id.editTitle)
            desc = findViewById(R.id.editDesc)


            MainActivity.dataTask.set(position.toInt(),Task(title.text.toString(), desc.text.toString()))
            val intent = Intent(this@DetailTask, MainActivity::class.java)
            startActivity(intent)
        }

    }

    companion object {
        var position = "-1"
    }
}