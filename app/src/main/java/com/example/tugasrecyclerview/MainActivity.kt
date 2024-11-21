package com.example.tugasrecyclerview

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.indices
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var _rvTask: RecyclerView
    private lateinit var _nama: Array<String>
    private lateinit var _deskripsi: Array<String>

    private var arTask = arrayListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        _rvTask = findViewById<RecyclerView>(R.id.rvTask)
        SiapkanData()
        TambahData()
        TampilkanData()

        var btnAdd = findViewById<Button>(R.id.buttonAdd)
        btnAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, AddTask::class.java)
            startActivity(intent)
        }
    }

    private fun TampilkanData() {
        _rvTask.layoutManager = LinearLayoutManager(this)
        _rvTask.adapter = adapterRecView(arTask)
    }

    private fun TambahData() {
        for (position in _nama.indices) {
            val data = Task(
                _nama[position],
                _deskripsi[position]
            )
            arTask.add(data)
        }
    }

    private fun SiapkanData() {
        _nama = resources.getStringArray(R.array.namaTask)
        _deskripsi = resources.getStringArray(R.array.deskripsiTask)
    }
}