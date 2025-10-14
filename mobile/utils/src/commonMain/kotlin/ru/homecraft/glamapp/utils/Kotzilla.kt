package ru.homecraft.glamapp.utils

interface KotzillaLogger {
    fun log(message: String)
    fun trace(tag: String, stackTrace: Boolean = false, block: () -> Unit)
}

expect object Logger: KotzillaLogger
