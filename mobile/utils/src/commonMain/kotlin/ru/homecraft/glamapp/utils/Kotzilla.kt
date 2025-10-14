package ru.homecraft.glamapp.utils

interface KotzillaLogger {
    fun log(message: String)
    fun<T: Any> trace(tag: String, stackTrace: Boolean = false, block: () -> T): T
}

expect object Logger: KotzillaLogger
