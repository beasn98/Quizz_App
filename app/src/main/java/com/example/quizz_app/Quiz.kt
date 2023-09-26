package com.example.quizz_app

import android.util.Log

class Quiz(val questions: List<Question>) {
    private var score = 0
    private var qNum = 0;
    //var to track: score, question number

    fun isEnd(): Boolean {
        return qNum <= questions.size
    }

    fun getChoice(num: Int): String {
        return questions[qNum].choices[num]
    }

    fun isRight(choice: String): Boolean {
        if (questions[qNum].answer == choice) {
            score++
            return true
        }
        return false
    }

    fun getQuestion(): String {
        return questions[qNum].question
    }

    fun updateNum() {
        qNum++
    }

    fun getScore(): Int {
        return score
    }


    //functions
    //are there more questions?
    //Checking answer
    //Getting the current question

    //Optional
    //reset the quiz
    //shuffle questions
}