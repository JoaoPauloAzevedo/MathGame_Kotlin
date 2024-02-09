package com.example.mathgame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    lateinit var txtFinalScore : TextView
    lateinit var  btnTryAgain : Button
    lateinit var  btnExit : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        txtFinalScore = findViewById(R.id.txtFinalScore)
        btnTryAgain = findViewById(R.id.btnTryAgain)
        btnExit = findViewById(R.id.btnExit)

        val score = intent.getIntExtra("score", 0)
        txtFinalScore.text = "O teu score é: " + score

        btnTryAgain.setOnClickListener {

            val intent = Intent(this@ResultActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnExit.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

    }
}