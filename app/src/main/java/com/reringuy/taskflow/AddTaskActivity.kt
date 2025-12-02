package com.reringuy.taskflow

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText

class AddTaskActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.add_activity_main)

        val cancelButton = findViewById<Button>(R.id.add_task_button_cancel)
        val confirmButton = findViewById<Button>(R.id.add_task_button_confirm)
        val titleTextField = findViewById<TextInputEditText>(R.id.textInputEditText)


        titleTextField.addTextChangedListener { text ->
            confirmButton.isEnabled = !text.isNullOrBlank()
        }

        confirmButton.setOnClickListener {
            finish()
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }
}