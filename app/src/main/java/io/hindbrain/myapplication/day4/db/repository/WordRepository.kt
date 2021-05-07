package io.hindbrain.myapplication.day4.db.repository

import androidx.annotation.WorkerThread
import io.hindbrain.myapplication.day4.db.dao.WordDao
import io.hindbrain.myapplication.day4.model.Word
import kotlinx.coroutines.flow.Flow

class WordRepository(private val dao:WordDao) {

    val allWords: Flow<List<Word>> = dao.getAlphabetizedWords()


    @WorkerThread
    suspend fun insert(word:Word){
        dao.insert(word)
    }
 }