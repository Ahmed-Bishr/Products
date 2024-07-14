package com.example.data.Repository.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.converters.Converters
import com.example.data.converters.ReviewsItemTypeConverter
import com.example.data.converters.RoomConverters
import com.example.data.model.ProductsItem

@Database(entities = [ProductsItem::class], version = 1, exportSchema = false)
@TypeConverters(
    Converters::class,                // For List<String>
    ReviewsItemTypeConverter::class,  // For List<ReviewsItem>
    RoomConverters::class             // for RoomConverters
)
abstract class ProductDataBase : RoomDatabase() {

    abstract fun getProductDao(): DAO

    companion object {
        @Volatile
        private var INSTANCE: ProductDataBase? = null
        private const val DATABASENAME = "ProductsDatabase"

        fun init(context: Context) {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ProductDataBase::class.java,
                        DATABASENAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
        }

        fun getInstance(): ProductDataBase {
            return INSTANCE ?: throw IllegalStateException("Database not initialized")
        }
    }
}
