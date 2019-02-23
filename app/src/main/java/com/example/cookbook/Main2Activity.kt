package com.example.cookbook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val dishName = intent.getStringExtra("dish_name")
        val dishDescription = intent.getStringExtra("dish_description")
        val textDishName: TextView= findViewById(R.id.dishName)
        val textDishDescription: TextView= findViewById(R.id.dishDescription)
        textDishName.text=dishName
        textDishDescription.text=dishDescription
    }
}
