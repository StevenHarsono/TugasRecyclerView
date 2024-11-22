package com.example.tugasrecyclerview

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var _rvTask: RecyclerView
    private var _nama: MutableList<String> = mutableListOf()
    private var _deskripsi: MutableList<String> = mutableListOf()

    private var arTask :MutableList<Task> = dataTask
    private var arTampilan: MutableList<Task> = mutableListOf()

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

        val adapterRecView = adapterRecView(arTampilan)
        _rvTask.adapter = adapterRecView

        adapterRecView.setOnItemClickCallback(object: adapterRecView.OnItemClickCallback {
            override fun delData(pos: Int) {
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("HAPUS DATA")
                    .setMessage("Apakah Benar Data '"+arTask.get(pos).nama+"' akan dihapus ?")
                    .setPositiveButton(
                        "HAPUS",
                        DialogInterface.OnClickListener { dialog, which ->
                            arTask.removeAt(0)
                            TambahData()
                            TampilkanData()
                        }
                    )
                    .setNegativeButton(
                        "BATAL",
                        DialogInterface.OnClickListener { dialog, which ->
                            Toast.makeText(
                                this@MainActivity,
                                "Data Batal Dihapus",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    )
                    .show()
            }
        })

    }

    private fun TambahData() {
        arTampilan.clear()
        for (data in arTask) {
            arTampilan.add(data)
        }
    }

    companion object {
        var dataTask = mutableListOf<Task>()
    }
}