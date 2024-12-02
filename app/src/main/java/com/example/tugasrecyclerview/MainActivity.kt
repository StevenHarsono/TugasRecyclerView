package com.example.tugasrecyclerview

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    lateinit var sp: SharedPreferences
    private lateinit var _rvTask: RecyclerView
    private var arTask :MutableList<Task> = dataTask
    private var arTampilan: MutableList<Task> = mutableListOf()
    private var savedTask: MutableList<Task> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        sp = getSharedPreferences("dataSP", MODE_PRIVATE)

        _rvTask = findViewById<RecyclerView>(R.id.rvTask)
        val gson = Gson()
        val isiSP = sp.getString("task", null)
        val type = object : TypeToken<MutableList<Task>> () {}.type
        if (isiSP != null) {
            arTask.addAll(gson.fromJson(isiSP, type))
            savedTask = gson.fromJson(isiSP, type)
        }

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
                    .setMessage("Apakah Benar Data '"+ arTask[pos].nama+"' akan dihapus ?")
                    .setPositiveButton(
                        "HAPUS",
                        DialogInterface.OnClickListener { dialog, which ->
                            for (i in 0 until savedTask.size) {
                                if (savedTask[i] == arTask[pos]) {
                                    savedTask.removeAt(i)
                                }
                            }
                            val gson = Gson()
                            val editor = sp.edit()
                            val json = gson.toJson(savedTask)
                            editor.putString("task", json)
                            editor.apply()

                            arTask.removeAt(pos)
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

            override fun editData(pos: Int) {
                DetailTask.position = pos.toString()
                val intent = Intent(this@MainActivity, DetailTask::class.java)
                startActivity(intent)
            }

            override fun saveData(pos: Int) {


                savedTask.add(arTask.get(pos))
                val gson = Gson()
                val editor = sp.edit()
                val json = gson.toJson(savedTask)
                editor.putString("task", json)
                editor.apply()
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