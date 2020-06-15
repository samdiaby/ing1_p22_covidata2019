package com.example.covidata2019

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonData : Button = findViewById(R.id.data)
        buttonData.setOnClickListener{
            val intent = Intent(this,Data::class.java)
            startActivity(intent);
        }
    }
}
