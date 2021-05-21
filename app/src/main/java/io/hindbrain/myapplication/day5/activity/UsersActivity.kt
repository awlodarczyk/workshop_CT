package io.hindbrain.myapplication.day5.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.hindbrain.myapplication.MyApplication
import io.hindbrain.myapplication.R
import io.hindbrain.myapplication.day4.viewmodel.WordViewModel
import io.hindbrain.myapplication.day4.viewmodel.WordViewModelFactory
import io.hindbrain.myapplication.day5.adapter.UserListAdapter
import io.hindbrain.myapplication.day5.model.User
import io.hindbrain.myapplication.day5.network.ServiceApi
import io.hindbrain.myapplication.day5.viewmodel.UserViewModel
import io.hindbrain.myapplication.day5.viewmodel.UserViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersActivity : AppCompatActivity() {
    private val viewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as MyApplication).usersRepository)
    }
    private var adapter: UserListAdapter = UserListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        findViewById<Button>(R.id.add).setOnClickListener {
            val intent = Intent(this, AddUserActivity::class.java)
            startActivity(intent)
        }
        val recyclerView = findViewById<RecyclerView>(R.id.recycler)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        viewModel.allItems.observe(this, Observer {

                adapter.submitList(it)

        })
    }
}