package com.epita.android.covidata2019

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonData : Button = findViewById(R.id.data)
        val buttonGraph : Button = findViewById(R.id.Graph)
        val buttonMystery : Button = findViewById(R.id.Mystery)

        // open the data layout
        buttonData.setOnClickListener{
            val intent = Intent(this, Data::class.java)
            startActivity(intent);
        }

        // open the graph layout
        buttonGraph.setOnClickListener{
            val intent = Intent(this, com.epita.android.covidata2019.Graph::class.java)
            startActivity(intent);
        }

        // open the graph layout
        buttonMystery.setOnClickListener{
            val intent = Intent(this, com.epita.android.covidata2019.MysteryActivity::class.java)
            startActivity(intent);
        }
    }
}