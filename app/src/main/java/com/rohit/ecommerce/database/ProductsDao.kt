package com.rohit.ecommerce.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductsDao {

    @Query("SELECT * FROM Productfromdatabase")
    fun getAll(): List<ProductFromDatabase>

    @Insert
    fun insertAll(vararg productFromDatabase: ProductFromDatabase)

    @Query("SELECT * FROM Productfromdatabase where title like :term")
    fun searchFor(term:String) : List<ProductFromDatabase>

}