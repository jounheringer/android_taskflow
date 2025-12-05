package com.reringuy.taskflow.ui.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reringuy.taskflow.data.entities.Task
import com.reringuy.taskflow.data.repositories.TaskRepository
import com.reringuy.taskflow.utils.OperationHandler
import com.reringuy.taskflow.utils.SingleEffectLauncher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditTaskViewModel(
    private val taskRepository: TaskRepository,
) : ViewModel() {
    private val _task: MutableLiveData<OperationHandler<Task>> =
        MutableLiveData(OperationHandler.Waiting)

    val task: LiveData<OperationHandler<Task>> = _task

    val onDoneEffect = SingleEffectLauncher<Unit>()

    fun getTask(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _task.postValue(OperationHandler.Loading)
            val response = taskRepository.getTaskById(id)
            if (response != null)
                _task.postValue(OperationHandler.Success(response))
            else
                _task.postValue(OperationHandler.Failure("Task não encontrada."))
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.updateTask(task)
            onDoneEffect.postValue(Unit)
        }
    }
}