package com.jorge.paulo.mvi.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jorge.paulo.mvi.data.local.entity.AnimalEntity

@Database(entities = [AnimalEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dbDao(): AnimalDao
}