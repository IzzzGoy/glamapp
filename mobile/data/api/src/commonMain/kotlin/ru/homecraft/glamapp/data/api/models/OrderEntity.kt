package ru.homecraft.glamapp.data.api.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OrderEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val status: Long,
    val createdAt: Long,
    val description: String?,
)
