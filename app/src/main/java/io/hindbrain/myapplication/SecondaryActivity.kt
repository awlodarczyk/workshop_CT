package io.hindbrain.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondaryActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)
        intent.extras?.get("HELLO_VARIABLE")?.let {
            findViewById<TextView>(R.id.text_2).text = it as String
        }
    }
}