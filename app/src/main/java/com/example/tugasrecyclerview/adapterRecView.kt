package com.example.tugasrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.*
import java.util.Date

class adapterRecView(private val listTask: MutableList<Task>):
    Adapter<adapterRecView.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun delData(pos: Int)
        fun editData(pos: Int)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(itemView: View): ViewHolder(itemView) {
        var _namaTask = itemView.findViewById<TextView>(R.id.task)
        var _deskripsiTask = itemView.findViewById<TextView>(R.id.description)
        var _tanggalTask = itemView.findViewById<TextView>(R.id.tanggal)
        var _delButton = itemView.findViewById<Button>(R.id.button)
        var _editButton = itemView.findViewById<Button>(R.id.button2)
        var _selesai = itemView.findViewById<CheckBox>(R.id.checkBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listTask.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var task = listTask[position]

        holder._namaTask.setText(task.nama)
        holder._deskripsiTask.setText(task.deskripsi)
        holder._tanggalTask.setText(task.tanggal)

        holder._delButton.setOnClickListener {
            onItemClickCallback.delData(position)
        }

        holder._editButton.setOnClickListener {
            onItemClickCallback.editData(position)
        }

        holder._selesai.setOnClickListener {
            holder._selesai.isEnabled = false
            holder._editButton.isEnabled = false
            task.status = true
        }

        if (task.status) {
            holder._selesai.isEnabled = false
            holder._selesai.isChecked = true
            holder._editButton.isEnabled = false
        }
    }
}