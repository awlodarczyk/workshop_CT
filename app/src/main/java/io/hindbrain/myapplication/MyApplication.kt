package io.hindbrain.myapplication

import android.app.Application
import io.hindbrain.myapplication.day4.db.WordRoomDatabase
import io.hindbrain.myapplication.day4.db.repository.WordRepository

class MyApplication: Application() {

    val database by lazy { WordRoomDatabase.getDatabase(this) }
    val wordRepository by lazy { WordRepository(database.wordDao()) }

    companion object{
        var GLOBAL_COUNTER = 0
    }
    override fun onCreate() {
        super.onCreate()

    }

    override fun onTerminate() {
        super.onTerminate()
    }
}