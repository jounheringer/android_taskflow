package com.reringuy.taskflow.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.reringuy.taskflow.R
import com.reringuy.taskflow.data.entities.Task

class TaskCardAdapter(
    private val onTaskCheckedChange: (Task) -> Unit
) :
    ListAdapter<Task, TaskCardAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.task_card_text_view)
        val checkBox: CheckBox = view.findViewById(R.id.task_checkbox)
    }

    class DiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = getItem(position)
        holder.textView.text = task.title
        holder.checkBox.isChecked = task.done

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            onTaskCheckedChange(task.copy(done = isChecked))
        }
    }
}