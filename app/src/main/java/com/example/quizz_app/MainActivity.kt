package com.example.quizz_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button
    lateinit var button4: Button
    lateinit var inquiry: TextView //question at the top
    lateinit var quiz: Quiz
    lateinit var buttons: Group //main Screen elements

    lateinit var gameOver: TextView
    lateinit var score: TextView
    lateinit var end: Group // score and game over txt



    companion object {
        val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wireWidgets()

        end.visibility = View.GONE

        loadQuestions()

        updateQuestion()

        updateAllButtons()




        button1.setOnClickListener {
            if (quiz.notEnd()) {
                quiz.isRight(button1.text.toString())
                quiz.updateNum()
                updateAllButtons()
                updateQuestion()
            } else {
                quiz.isRight(button1.text.toString())
                //end of quiz
                gameOver()
            }
        }

        button2.setOnClickListener {
            if (quiz.notEnd()) {
                quiz.isRight(button2.text.toString())
                quiz.updateNum()
                updateAllButtons()
                updateQuestion()
            } else {
                quiz.isRight(button2.text.toString())
                gameOver()
            }
        }

        button3.setOnClickListener {
            if (quiz.notEnd()) {
                quiz.isRight(button3.text.toString())
                quiz.updateNum()
                updateAllButtons()
                updateQuestion()
            } else {
                quiz.isRight(button3.text.toString())
                gameOver()
            }
        }

        button4.setOnClickListener {
            if (quiz.notEnd()) {
                quiz.isRight(button4.text.toString())
                quiz.updateNum()
                updateAllButtons()
                updateQuestion()
            } else {
                quiz.isRight(button4.text.toString())
                gameOver()
            }
        }

    }

    private fun updateQuestion() {
        inquiry.text = quiz.getQuestion()
    }

    private fun updateAllButtons() {
        for (i in 0..3) {
            updateButton(i)
        }
    }

    private fun updateButton(a: Int) {
        when (a) {
            0 -> button1.text = quiz.getChoice(a)
            1 -> button2.text = quiz.getChoice(a)
            2 -> button3.text = quiz.getChoice(a)
            3 -> button4.text = quiz.getChoice(a)
        }
    }

    private fun gameOver() {
        buttons.visibility = View.GONE
        end.visibility = View.VISIBLE
        gameOver.text = resources.getString(R.string.gameover)
        score.text = "${resources.getString(R.string.score)}: " + quiz.getScore().toString()
    }

    private fun wireWidgets() {
        button1 = findViewById(R.id.button_main_one)
        button2 = findViewById(R.id.button_main_two)
        button3 = findViewById(R.id.button_main_three)
        button4 = findViewById(R.id.button_main_four)
        inquiry = findViewById(R.id.textView_main_question)
        buttons = findViewById(R.id.group_main_quizUI)
        end = findViewById(R.id.group_main_endText)
        gameOver = findViewById(R.id.textView_main_gameOver)
        score = findViewById(R.id.textView_main_score)
    }

    private fun loadQuestions() {
        //load questions from JSON
        val inputStream = resources.openRawResource(R.raw.questions)
        val jsonString = inputStream.bufferedReader().use {
            it.readText()
        }
        Log.d(TAG, "onCreate: jsonString $jsonString")
        val gson = Gson()
        val qType =
            object : TypeToken<List<Question>>() {}.type // data type of the list, questions.
        val questions = gson.fromJson<List<Question>>(jsonString, qType)

        Log.d(TAG, "loadQuestions: $questions")
        Log.d(TAG, "loadQuestions: ${questions.size}")

        quiz = Quiz(questions)
    }

}