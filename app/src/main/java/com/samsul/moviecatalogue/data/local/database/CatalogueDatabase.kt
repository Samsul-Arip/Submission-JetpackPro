package com.samsul.moviecatalogue.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.samsul.moviecatalogue.data.local.entity.DataLocalMovie
import com.samsul.moviecatalogue.data.local.entity.DataLocalTvShow

@Database(
    entities = [DataLocalMovie::class, DataLocalTvShow::class],
    version = 1,
    exportSchema = false
)
abstract class CatalogueDatabase: RoomDatabase() {

    abstract fun catalogueDao(): CatalogueDao

    companion object {

        @Volatile
        private var INSTANCE: CatalogueDatabase? = null

        fun getInstance(context: Context): CatalogueDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    CatalogueDatabase::class.java,
                    "Catalogue.db"
                ).build().apply { INSTANCE = this }
            }

    }
}