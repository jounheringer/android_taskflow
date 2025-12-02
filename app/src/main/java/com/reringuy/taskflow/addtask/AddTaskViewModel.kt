package com.reringuy.taskflow.addtask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reringuy.taskflow.data.entities.Task
import com.reringuy.taskflow.data.repositories.TaskRepository
import com.reringuy.taskflow.utils.SingleEffectLauncher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddTaskViewModel(private val taskRepository: TaskRepository) : ViewModel() {

    val onDoneEffect = SingleEffectLauncher<Unit>()

    fun saveTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.addTask(task)

            onDoneEffect.postValue(Unit)
        }
    }
}