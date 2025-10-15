package ru.homecraft.glamapp.data.impl

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import ru.homecraft.glamapp.data.api.AppDatabase
import ru.homecraft.glamapp.data.api.getDatabaseBuilder
import ru.homecraft.glamapp.data.api.getRoomDatabase
import ru.homecraft.glamapp.data.api.local.OrderDao

@Configuration
@ComponentScan("ru.homecraft.glamapp.data.impl")
@Module
class DI {

    @Single(createdAtStart = true)
    fun provideDatabase(): AppDatabase {
        return getRoomDatabase(getDatabaseBuilder())
    }

    @Single(createdAtStart = true)
    fun provideOrdersDao(appDatabase: AppDatabase): OrderDao {
        return appDatabase.getDao()
    }
}