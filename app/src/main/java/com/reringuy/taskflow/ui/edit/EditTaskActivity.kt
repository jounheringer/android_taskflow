package com.reringuy.taskflow.ui.edit

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.reringuy.taskflow.R
import com.reringuy.taskflow.utils.OperationHandler
import org.koin.android.ext.android.inject

class EditTaskActivity: AppCompatActivity() {

    private val viewModel: EditTaskViewModel by inject<EditTaskViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.edit_task)

        val id = intent.getLongExtra(EXTRA_TASK_ID, -1)
        val loadingComponent = findViewById<FrameLayout>(R.id.loading_view)
        val cancelButton = findViewById<Button>(R.id.edit_task_button_cancel)
        val confirmButton = findViewById<Button>(R.id.edit_task_button_confirm)
        val titleTextInput = findViewById<TextInputEditText>(R.id.edit_title)
        val descriptionTextInput = findViewById<TextInputEditText>(R.id.edit_description)

        viewModel.getTask(id)


        viewModel.onDoneEffect.observe(this) {
            Toast.makeText(this, "Task atualizada", Toast.LENGTH_SHORT).show()
            finish()
        }

        viewModel.task.observe(this) { taskResult ->
            when (taskResult) {
                is OperationHandler.Failure -> {
                    loadingComponent.visibility = View.GONE
                    Toast.makeText(this, taskResult.message, Toast.LENGTH_SHORT).show()
                    finish()
                }
                is OperationHandler.Success -> {
                    loadingComponent.visibility = View.GONE

                    var task = taskResult.data
                    titleTextInput.setText(task.title)
                    descriptionTextInput.setText(task.description ?: "")
                    confirmButton.isEnabled = titleTextInput.text.toString().isNotEmpty()

                    titleTextInput.addTextChangedListener {
                        task = task.copy(title = it.toString())
                    }

                    descriptionTextInput.addTextChangedListener {
                        task = task.copy(description = it.toString())
                    }

                    confirmButton.setOnClickListener {
                        viewModel.updateTask(task)
                    }

                    cancelButton.setOnClickListener {
                        finish()
                    }
                }
                else -> {
                    loadingComponent.visibility = View.VISIBLE
                }
            }
        }
    }

    companion object {
        const val EXTRA_TASK_ID = "extra_task_id"
    }
}