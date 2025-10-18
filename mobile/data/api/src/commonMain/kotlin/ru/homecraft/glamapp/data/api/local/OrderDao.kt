package ru.homecraft.glamapp.data.api.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.homecraft.glamapp.data.api.models.OrderEntity

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: OrderEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBatch(entities: List<OrderEntity>)

    @get:Query("SELECT * FROM OrderEntity")
    val getAllAsFlow: Flow<List<OrderEntity>>
}