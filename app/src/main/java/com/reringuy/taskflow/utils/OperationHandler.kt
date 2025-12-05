package com.reringuy.taskflow.utils


sealed class OperationHandler<out T> {

    object Waiting:OperationHandler<Nothing>()

    object Loading:OperationHandler<Nothing>()

    data class Success<out T>(val data:T):OperationHandler<T>()

    data class Failure(val message:String):OperationHandler<Nothing>()
}
