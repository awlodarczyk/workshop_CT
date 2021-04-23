package io.hindbrain.myapplication.day2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import io.hindbrain.myapplication.R

class SecondActivity:AppCompatActivity() {
    private lateinit var editText:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        editText = findViewById<EditText>(R.id.text)

        findViewById<Button>(R.id.button).setOnClickListener {

            if (editText.text.isNotBlank()){
                val data = Intent()
                data.putExtra("key",editText.text.toString())
                setResult(FirstActivity.FIRST_REQUEST_CODE,data)
                finish()
            }
        }
    }
}