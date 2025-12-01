package com.reringuy.taskflow.ui.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reringuy.taskflow.R
import com.reringuy.taskflow.data.entities.Task

class TaskCardAdapter(private val tasks: List<Task>) :
    RecyclerView.Adapter<TaskCardAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.task_card_text_view)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_card, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = tasks[position].title
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}