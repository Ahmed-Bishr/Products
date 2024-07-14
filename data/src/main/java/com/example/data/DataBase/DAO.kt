package com.example.data.Repository.DataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.ProductsItem

/**
 *
 * Rules for the DataBase
 * */
@Dao
interface DAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveData(products: List<ProductsItem>)

    @Query("SELECT * FROM products")
    suspend fun getData(): List<ProductsItem>

    @Query("DELETE FROM products")
    suspend fun deleteAllData()


}