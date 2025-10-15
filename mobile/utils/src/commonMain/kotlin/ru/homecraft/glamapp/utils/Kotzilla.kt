package ru.homecraft.glamapp.utils

interface KotzillaLogger {
    fun log(message: String)
    fun<T: Any> trace(tag: String, stackTrace: Boolean = false, block: () -> T): T
    suspend fun<T: Any> suspendTrace(tag: String, stackTrace: Boolean = false, block: suspend () -> T): T
}

expect object Logger: KotzillaLogger
