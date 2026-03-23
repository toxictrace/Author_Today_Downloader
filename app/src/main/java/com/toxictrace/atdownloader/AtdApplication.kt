package com.toxictrace.atdownloader

import android.app.Application
import androidx.room.Room
import com.toxictrace.atdownloader.database.AppDatabase

class AtdApplication : Application() {
    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate();
        
        // Initialize database
        database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "atd_database"
        ).build()
    }
}