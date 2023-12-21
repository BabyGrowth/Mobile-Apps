package com.cp.babygrowth.data

sealed class Hasil<out R> private constructor(){
    data class Success<out T>(val data: T) : Hasil<T>()
    data class Error(val error: String) : Hasil<Nothing>()
    object Loading : Hasil<Nothing>()
}
