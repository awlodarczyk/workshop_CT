package io.hindbrain.myapplication.day4.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.hindbrain.myapplication.day4.db.dao.WordDao
import io.hindbrain.myapplication.day4.model.Word


@Database(entities = [
    Word::class
    ],
    version = 1,
    exportSchema = false
)
abstract class WordRoomDatabase : RoomDatabase(){
    abstract fun wordDao():WordDao

    companion object{

        private var INSTANCE:WordRoomDatabase? = null

        fun getDatabase(context: Context):WordRoomDatabase{


            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    WordRoomDatabase::class.java,
                    "database_word").build()
                INSTANCE = instance
                return@synchronized instance
            }
        }
    }
}