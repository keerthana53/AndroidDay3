package com.example.task36

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {


    var num = (0..1000).random()
    var stringNum = num.toString()
    var attemptCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numberInputView = findViewById<TextInputLayout>(R.id.inputNumber)
        val checkButtonView = findViewById<TextView>(R.id.checkButton)
        val resultView = findViewById<TextView>(R.id.result)

        checkButtonView.setOnClickListener{
            val number = numberInputView.editText?.text?.toString()
            val guessedNumber =
                if (number.isNullOrEmpty()) "0"
                else number
            if (guessedNumber != "0") {
                if (guessedNumber.toInt() == num) {
                    resultView.text = "You are right!"
                    val newScreenIntent = Intent(this,AmIRight::class.java)
                    newScreenIntent.putExtra("number",stringNum)
                    newScreenIntent.putExtra("msg","You won! The number is")
                    newScreenIntent.putExtra("color","#00FF00")
                    startActivity(newScreenIntent)
                }
                else if(guessedNumber.toInt() > num)
                    resultView.text = "No:) My number is smaller"
                else
                    resultView.text = "No:) My number is bigger"
            }
            else
                resultView.text = "please enter the number"
            attemptCount++
            if(attemptCount > 12) {
                val newScreenIntent = Intent(this,AmIRight::class.java)
                newScreenIntent.putExtra("number",stringNum)
                newScreenIntent.putExtra("msg","You lost after 12 attempts. The number is")
                newScreenIntent.putExtra("color","#FF0000")
                startActivity(newScreenIntent)
            }
        }


    }
}