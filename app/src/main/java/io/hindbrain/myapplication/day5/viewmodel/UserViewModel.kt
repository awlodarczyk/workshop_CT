package io.hindbrain.myapplication.day5.viewmodel

import androidx.lifecycle.*
import io.hindbrain.myapplication.day4.db.repository.WordRepository
import io.hindbrain.myapplication.day4.model.Word
import io.hindbrain.myapplication.day5.model.User
import io.hindbrain.myapplication.day5.repository.UserRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class UserViewModel(private val repository: UserRepository):ViewModel() {

    val allItems: LiveData<List<User>> = repository.getUsers()

}

class UserViewModelFactory(private val repository: UserRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UserViewModel::class.java)){
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("View Model not match")
    }

}