package ru.homecraft.glamapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform