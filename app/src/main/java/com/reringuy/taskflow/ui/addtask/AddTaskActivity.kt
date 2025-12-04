package com.reringuy.taskflow.ui.addtask

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.reringuy.taskflow.R
import com.reringuy.taskflow.data.entities.Task
import org.koin.android.ext.android.inject

class AddTaskActivity : AppCompatActivity() {

    private val viewModel: AddTaskViewModel by inject<AddTaskViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.add_activity_main)

        val cancelButton = findViewById<Button>(R.id.add_task_button_cancel)
        val confirmButton = findViewById<Button>(R.id.add_task_button_confirm)
        val titleTextField = findViewById<TextInputEditText>(R.id.textInputEditText)
        val descriptionTextField = findViewById<TextInputEditText>(R.id.descriptionEditText)

        viewModel.onDoneEffect.observe(this) {
            Toast.makeText(this, "Task adicionada", Toast.LENGTH_SHORT).show()
            finish()
        }

        titleTextField.addTextChangedListener { text ->
            confirmButton.isEnabled = !text.isNullOrBlank()
        }

        confirmButton.setOnClickListener {
            val auxText = Task(
                title = titleTextField.text.toString(),
                description = if (descriptionTextField.text.isNullOrBlank()) null else descriptionTextField.text.toString()
            )
            viewModel.saveTask(auxText)
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }
}