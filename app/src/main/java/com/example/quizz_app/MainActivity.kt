package com.example.quizz_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    lateinit var buttonLeft: Button
    lateinit var buttonRight: Button
    lateinit var quiz: Quiz


    companion object {
        val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wireWidgets()

        loadQuestions()


    }

    private fun wireWidgets() {
        buttonLeft = findViewById(R.id.button_main_false)
        buttonRight = findViewById(R.id.button_main_true)
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

        quiz = Quiz(questions)

        //Log.d(TAG, "loadQuestions: $questions")


        //next steps:
        // Make your question object
        // Use this tutorial (on classroom)
        // scroll down to "parsing between a Collection, List, or Array"
        //convert your jsonString to a List<Question>
        // Log that list of questions to see if it worked


        //Create a Quiz object and pass in that list of questions as a param

        //do the initial question and answer choices setup

        //Set listeners to react to user input
        //passing info to and from the Quiz object
    }

}