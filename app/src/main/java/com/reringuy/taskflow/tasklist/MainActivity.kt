package com.reringuy.taskflow.tasklist

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.reringuy.taskflow.AddTaskActivity
import com.reringuy.taskflow.R
import com.reringuy.taskflow.ui.components.TaskCardAdapter
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var taskCardAdapter: TaskCardAdapter

    private val viewModel: TaskListViewModel by inject<TaskListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val addFloatingButton = findViewById<FloatingActionButton>(R.id.activitiy_main_floating_action_button)
        val textView = findViewById<TextView>(R.id.activity_main_text_view)

        taskCardAdapter = TaskCardAdapter()

        viewModel.taskList.observe(this) { tasks ->
            textView.visibility = if (tasks.isEmpty()) TextView.VISIBLE else TextView.GONE
            taskCardAdapter.submitList(tasks)
        }


        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = taskCardAdapter


        addFloatingButton.setOnClickListener {
            startActivity(Intent(this, AddTaskActivity::class.java))
        }
    }
}