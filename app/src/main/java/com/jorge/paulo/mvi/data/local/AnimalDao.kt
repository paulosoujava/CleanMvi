package com.jorge.paulo.mvi.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jorge.paulo.mvi.data.local.entity.AnimalEntity


@Dao
interface AnimalDao {
    @Query("SELECT * FROM animalentity")
    fun getAll(): List<AnimalEntity>

    @Query("SELECT * FROM animalentity WHERE uid IN (:animalId)")
    fun loadAllByIds(animalId: IntArray): List<AnimalEntity>

    @Query("SELECT * FROM animalentity WHERE name LIKE :name  LIMIT 1")
    fun findByName(name: String ): AnimalEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert( animal: AnimalEntity)

    @Delete
    fun delete(user: AnimalEntity)
}