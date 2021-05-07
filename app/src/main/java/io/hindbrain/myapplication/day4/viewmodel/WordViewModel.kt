package io.hindbrain.myapplication.day4.viewmodel

import androidx.lifecycle.*
import io.hindbrain.myapplication.day4.db.repository.WordRepository
import io.hindbrain.myapplication.day4.model.Word
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class WordViewModel(private val repository: WordRepository):ViewModel() {

    val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()
    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }
}

class WordViewModelFactory(private val repository: WordRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WordViewModel::class.java)){
            return WordViewModel(repository) as T
        }
        throw IllegalArgumentException("View Model not match")
    }

}