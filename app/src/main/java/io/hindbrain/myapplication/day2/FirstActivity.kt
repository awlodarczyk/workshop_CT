package io.hindbrain.myapplication.day2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.hindbrain.myapplication.R

class FirstActivity:AppCompatActivity() {
    companion object{
        val FIRST_REQUEST_CODE = 123
    }
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        textView = findViewById(R.id.text)
        findViewById<Button>(R.id.button).setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivityForResult(intent,FIRST_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == FIRST_REQUEST_CODE){
            data?.extras?.get("key")?.let {
                textView.text = it as String
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}