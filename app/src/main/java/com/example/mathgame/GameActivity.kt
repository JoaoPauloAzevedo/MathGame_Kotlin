package com.example.mathgame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.jetbrains.annotations.Nullable
import java.util.*
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    lateinit var txtScore : TextView
    lateinit var txtLife : TextView
    lateinit var txtTime : TextView

    lateinit var txtQuestion : TextView
    lateinit var edtAnswer : EditText

    lateinit var btnOk : Button
    lateinit var btnNext : Button

    var correctAnswer = 0
    var userScore = 0
    var userLife = 3

    lateinit var timer : CountDownTimer

    private val starTimerInMillis : Long = 60000
    var timeLeft : Long = starTimerInMillis

    var modoGame = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        supportActionBar!!.title = "Adição"

        txtScore = findViewById(R.id.txtScoreNmr)
        txtLife = findViewById(R.id.txtLife)
        txtTime = findViewById(R.id.txtTime)

        txtQuestion = findViewById(R.id.txtQuestion)
        edtAnswer = findViewById(R.id.edtAnswer)

        btnNext = findViewById(R.id.btn_Next)
        btnOk = findViewById(R.id.btn_ok)

        val modo = intent.getIntExtra("modo", 0)
        modoGame = modo.toString()
        gameContinue()

        btnOk.setOnClickListener {

            val input = edtAnswer.text.toString()

            if (input == "")
            {
                Toast.makeText(applicationContext, "Por Favor introduza um respota ou então clique no botão 'Proximo'." , Toast.LENGTH_LONG ).show()
            }
            else
            {
                pauseTimer()

                var userAnswer = input.toInt()

                if (userAnswer == correctAnswer)
                {
                    userScore += 10
                    txtQuestion.text = "Parabens a sua resposta está correta!"
                    txtScore.text = userScore.toString()
                }
                else
                {
                    userLife--
                    txtQuestion.text = "A sua resposta está errada!"
                    txtLife.text = userLife.toString()

                   if (userLife == 0)
                   {
                       btnNext.setText("Score")
                   }
                }
            }
        }

        btnNext.setOnClickListener {

            pauseTimer()
            resetTimer()

            edtAnswer.setText("")

            if (userLife == 0)
            {
                Toast.makeText(applicationContext, "Jogo Terminado", Toast.LENGTH_LONG).show()
                val intent = Intent(this@GameActivity, ResultActivity::class.java)
                intent.putExtra("score", userScore)
                startActivity(intent)
            }
            else
            {
                gameContinue()
            }

        }

    }
    fun gameContinue()
    {

        val nmr1 =  Random.nextInt(0 , 100)
        val nmr2 =  Random.nextInt(0 , 100)


        if (modoGame == "0")
        {
            txtQuestion.text = "$nmr1 + $nmr2"
            correctAnswer = nmr1 + nmr2
        }
        if (modoGame == "1")
        {
            txtQuestion.text = "$nmr1 - $nmr2"
            correctAnswer = nmr1 - nmr2
        }
        if (modoGame == "2")
        {
            txtQuestion.text = "$nmr1 x $nmr2"
            correctAnswer = nmr1 * nmr2
        }


        startTimer()

    }

    fun startTimer()
    {

        timer = object : CountDownTimer(timeLeft, 1000)
        {
            override fun onTick(millisUntilFinished: Long) {

                timeLeft = millisUntilFinished
                updateText()


            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateText()

                userLife--
                txtQuestion.text = "O tempo acabou"
                txtLife.text = userLife.toString()
            }

        }.start()

    }

    fun updateText()
    {
        val remainingTime : Int = (timeLeft / 1000).toInt()
        txtTime.text = String.format(Locale.getDefault(), "%02d", remainingTime)
    }

    fun pauseTimer()
    {
        timer.cancel()
    }

    fun resetTimer()
    {
        timeLeft = starTimerInMillis
        updateText()
    }

}