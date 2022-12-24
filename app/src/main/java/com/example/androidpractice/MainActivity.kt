package com.example.androidpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.lang.Math.abs
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.tv_random)
        val tv_t: TextView = findViewById(R.id.tv_timer)
        val tv_p: TextView = findViewById(R.id.tv_point)
        val btn: Button = findViewById(R.id.btn_main)

        var sec: Int = 0
        var timerTask: Timer? = null
        var isRunning: Boolean = false


        val random_box = Random()
        val num = random_box.nextInt(1001)
        tv.text = (num.toFloat() / 100).toString()


        btn.setOnClickListener {
            isRunning = !isRunning

            if (isRunning) {
                timerTask = timer(period = 10) {
                    sec++
                    runOnUiThread {
                        tv_t.text = (sec.toFloat()/100).toString()
                    }
                }
            } else {
                timerTask?.cancel()
                val point = abs(sec - num).toFloat() / 100
                tv_p.text = point.toString()
           }
        }
    }
}