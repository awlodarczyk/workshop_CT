package io.hindbrain.myapplication.day5.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.hindbrain.myapplication.day5.model.User
import io.hindbrain.myapplication.day5.network.ServiceApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(private val api:ServiceApi) {

    fun getUsers():LiveData<List<User>>{
        val items = MutableLiveData<List<User>>()
        api.getAllUsers().enqueue(object: Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if(response.isSuccessful){
                    //mozemy odczytac uzytkownikow
                    response.body()?.let {
                        items.postValue(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
            }

        })
        return items
    }
}