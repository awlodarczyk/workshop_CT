package io.hindbrain.myapplication.day2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.hindbrain.myapplication.R

class CustomFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom)
    }
}