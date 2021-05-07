package io.hindbrain.myapplication.day4

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import io.hindbrain.myapplication.R
import io.hindbrain.myapplication.day4.adapter.ShoppingAdapter
import io.hindbrain.myapplication.day4.model.Priority
import io.hindbrain.myapplication.day4.model.ShoppingItem

class SharedPrefsActivity:AppCompatActivity() {


    private lateinit var textView: TextView
    private lateinit var editText: EditText
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shared_activity)

        textView = findViewById<TextView>(R.id.textView)
        editText = findViewById<EditText>(R.id.editText)
        refreshTextView()
        findViewById<Button>(R.id.button).setOnClickListener {
//            saveAndRefreshText()
            saveAndRefreshAdapter()
        }

        recyclerView = findViewById<RecyclerView>(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ShoppingAdapter(getItems())


    }

    private fun getItems():List<ShoppingItem>{
        val sharedPref = this.getSharedPreferences("SHARED_PREFS", MODE_PRIVATE)
        val jsonList = sharedPref.getString("SHOPPING_ITEMS","")
        if(jsonList!!.isNotEmpty()){
            val gson = Gson()
            val typeToken = object : TypeToken<ArrayList<ShoppingItem>>() {}.type
            try {
                val items = gson.fromJson<ArrayList<ShoppingItem>>(jsonList, typeToken)
                return items
            }catch (ex: JsonSyntaxException){
                Log.i("EX","blad parsowania danych")
            }
        }
        return emptyList()
    }
    private fun saveAndRefreshAdapter(){
        val sharedPref = this.getSharedPreferences("SHARED_PREFS", MODE_PRIVATE)
        val value = editText.text.toString()
        val item = ShoppingItem(value,1,Priority.MEDIUM,"")
        val list = getItems().toMutableList()
        list.add(item)

        //powinien nastapic zaspis do sharedPrefs
        val gson = Gson()
        val jsonList = gson.toJson(list)
        sharedPref.edit().putString("SHOPPING_ITEMS",jsonList).apply()
        refreshAdapter()
    }
    private fun refreshAdapter(){
        (recyclerView.adapter as ShoppingAdapter).updateAdapter(getItems())
    }
    private fun saveAndRefreshText(){
        val sharedPref = this.getSharedPreferences("SHARED_PREFS", MODE_PRIVATE)
        val value = editText.text.toString()
        sharedPref?.edit()?.putString("TEXT_KEY",value)?.apply()

        refreshTextView()
    }
    private fun refreshTextView(){
        val sharedPref = this.getSharedPreferences("SHARED_PREFS", MODE_PRIVATE)
        val txt = sharedPref.getString("TEXT_KEY","Brak danych w pamieci")
        textView.text = txt
    }
}