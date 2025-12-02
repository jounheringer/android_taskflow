package com.reringuy.taskflow.tasklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reringuy.taskflow.data.entities.Task
import com.reringuy.taskflow.data.repositories.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskListViewModel(
    private val taskRepository: TaskRepository,
) : ViewModel() {
    private val _taskList: MutableLiveData<List<Task>> = MutableLiveData(emptyList())

    val taskList: LiveData<List<Task>> = _taskList


    init {
        loadTaskList()
    }

    private fun loadTaskList() {
        viewModelScope.launch(Dispatchers.IO) {
            _taskList.postValue(taskRepository.getAll())
        }
    }
}