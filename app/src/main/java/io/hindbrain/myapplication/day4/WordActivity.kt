package io.hindbrain.myapplication.day4

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.hindbrain.myapplication.MyApplication
import io.hindbrain.myapplication.R
import io.hindbrain.myapplication.day4.adapter.WordListAdapter
import io.hindbrain.myapplication.day4.model.Word
import io.hindbrain.myapplication.day4.viewmodel.WordViewModel
import io.hindbrain.myapplication.day4.viewmodel.WordViewModelFactory

class WordActivity:AppCompatActivity() {

    private val newWordActivityRequestCode = 1

    private val viewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as MyApplication).wordRepository)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.word_activity)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            val intent = Intent(this, NewWordActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


        viewModel.allWords.observe(this){ words->
            words.let {
                adapter.submitList(it)
            }
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra(NewWordActivity.EXTRA_REPLY)?.let { reply ->
                val word = Word(null,reply)
                viewModel.insert(word)
            }
        } else {
            Toast.makeText(
                applicationContext,
                "ups",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}