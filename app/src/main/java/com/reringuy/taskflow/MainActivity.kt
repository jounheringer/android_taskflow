package com.reringuy.taskflow

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.reringuy.taskflow.data.entities.Task
import com.reringuy.taskflow.ui.components.TaskCardAdapter

class MainActivity : AppCompatActivity() {

    val auxTasks = listOf(
        Task(
            id = 1L,
            title = "Task 1",
            description = "Description 1"
        ),
        Task(
            id = 2L,
            title = "Task 2",
            description = "Description 2"
        ),
        Task(
            id = 3L,
            title = "Task 3",
            description = "Description 3"
        ),
        Task(
            id = 4L,
            title = "Task 4",
            description = "Description 4"
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val taskCardAdapter = TaskCardAdapter(auxTasks)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = taskCardAdapter

        val addFloatingButton = findViewById<FloatingActionButton>(R.id.activitiy_main_floating_action_button)

        addFloatingButton.setOnClickListener {
            startActivity(Intent(this, AddTaskActivity::class.java))
        }
    }
}