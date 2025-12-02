package com.reringuy.taskflow.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class SingleEffectLauncher<T> : MutableLiveData<T>() {
    private val observers = mutableSetOf<ObserverWrapper<in T>>()

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        val wrapper = ObserverWrapper(observer)
        observers.add(wrapper)
        super.observe(owner, wrapper)
    }

    override fun setValue(value: T) {
        observers.onEach { it.newValue() }
        super.setValue(value)
    }

    private class ObserverWrapper<T>(private val observer: Observer<T>) : Observer<T> {
        private var pending = false

        fun newValue() {
            pending = true
        }

        override fun onChanged(value: T) {
            if (pending) {
                pending = false
                observer.onChanged(value)
            }
        }
    }
}