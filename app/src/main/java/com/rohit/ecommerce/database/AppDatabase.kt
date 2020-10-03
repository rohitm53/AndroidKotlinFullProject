package com.rohit.ecommerce.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProductFromDatabase::class,CartModel::class],version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao():ProductsDao
    abstract fun cartDao():CartDao

}