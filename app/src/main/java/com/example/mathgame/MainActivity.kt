package com.example.mathgame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var  adicao : Button
    lateinit var subtracao : Button
    lateinit var multi : Button

    var modo = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adicao = findViewById(R.id.btn_Add)
        subtracao = findViewById(R.id.btn_Subtracao)
        multi = findViewById(R.id.btn_Multi)

        adicao.setOnClickListener {

            modo = 0
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("modo", modo)
            startActivity(intent)
        }
        subtracao.setOnClickListener {

            modo = 1
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("modo", modo)
            startActivity(intent)
        }

        multi.setOnClickListener {

            modo = 2
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("modo", modo)
            startActivity(intent)
        }


    }
}