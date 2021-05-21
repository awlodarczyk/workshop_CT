package io.hindbrain.myapplication.day5.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.hindbrain.myapplication.R
import io.hindbrain.myapplication.day5.adapter.UserListAdapter
import io.hindbrain.myapplication.day5.model.User
import io.hindbrain.myapplication.day5.network.ServiceApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersActivity : AppCompatActivity() {

    private var adapter: UserListAdapter = UserListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        ServiceApi.invoke(this).getAllUsers().enqueue(object: Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if(response.isSuccessful){
                    //mozemy odczytac uzytkownikow
                    val users = response.body()
                    Log.i("users",users.toString())
                    adapter.submitList(users)
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {

                Toast
                    .makeText(this@UsersActivity,"Błąd połączenia: "+t.message,Toast.LENGTH_LONG)
                    .show()
            }

        })
    }
}