package io.hindbrain.myapplication.day5.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import io.hindbrain.myapplication.R
import io.hindbrain.myapplication.day5.adapter.UserListAdapter
import io.hindbrain.myapplication.day5.model.User
import io.hindbrain.myapplication.day5.network.ServiceApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddUserActivity : AppCompatActivity() {

    lateinit var name:EditText
    lateinit var address:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        name = findViewById<EditText>(R.id.name)
        address = findViewById<EditText>(R.id.address)
        findViewById<Button>(R.id.save).setOnClickListener {
            if(name.text.isNotBlank() && address.text.isNotBlank()){
                val user = User(name = name.text.toString(),address = address.text.toString())
                ServiceApi.invoke(this).addUser(user).enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if (response.isSuccessful) {
                            //mozemy odczytac uzytkownikow
                            val user = response.body()
                            Log.i("user", user.toString())
                            finish()
                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {

                        Toast
                            .makeText(
                                this@AddUserActivity,
                                "Błąd połączenia: " + t.message,
                                Toast.LENGTH_LONG
                            )
                            .show()
                    }

                })
            }
        }




    }
}