package io.hindbrain.myapplication.day1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import io.hindbrain.myapplication.MyApplication.Companion.GLOBAL_COUNTER
import io.hindbrain.myapplication.R
import io.hindbrain.myapplication.day2.FirstActivity
import io.hindbrain.myapplication.day4.SharedPrefsActivity
import io.hindbrain.myapplication.day4.WordActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var button: Button? = null
    private var text: TextView? = null
    private var localCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("APP", "onCreate")
        button = findViewById<Button>(R.id.button)
        text = findViewById<TextView>(R.id.text)

        this.button?.text = "XYZ"
        this.button?.setOnClickListener(this)

        findViewById<Button>(R.id.button2).setOnClickListener(this)
        findViewById<Button>(R.id.button3).setOnClickListener(this)
        findViewById<Button>(R.id.button_day2).setOnClickListener{
            val intent = Intent(this, FirstActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.button_day4).setOnClickListener{
//            val intent = Intent(this, SharedPrefsActivity::class.java)
//            startActivity(intent)

            val intent = Intent(this, WordActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        localCounter = 0
    }

    fun createAlert() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Pytanie")
        dialogBuilder.setMessage("Czy dalej chcesz liczyc klikniecia?")
        dialogBuilder.setPositiveButton("Tak", { dialog, which ->
            Log.i("APP", "Click positive")
        })
        dialogBuilder.setNegativeButton("Nie", { dialog, which ->
            Log.i("APP", "Click negative")
            localCounter = 0

        })
        dialogBuilder.show()
    }

    override fun onClick(button: View?) {
        if (button?.id == R.id.button3) {
            text?.text = "Global Counter is ${GLOBAL_COUNTER++}\n local counter is ${localCounter++}"
            if(localCounter>=10) {
                createAlert()
            }
            Toast.makeText(this, "Button 3 Clicked", android.widget.Toast.LENGTH_LONG).show()
        }else if (button?.id == R.id.button2) {
            Toast.makeText(this, "Button 2 Clicked", android.widget.Toast.LENGTH_LONG).show()
        } else {

            val intent = Intent(this, SecondaryActivity::class.java)
            intent.putExtra("HELLO_VARIABLE", "Hello from MainActivity")
            startActivity(intent)

            Toast.makeText(this, "Other button click", Toast.LENGTH_SHORT).show()
        }
        when (button?.id) {
            R.id.button -> Log.i("Btn", "Button")
            R.id.button2 -> Log.i("Btn", "Button1")
            else -> Log.e("ERR", "ups")
        }
        Log.i("APP", "Clicked via listener")
    }
}