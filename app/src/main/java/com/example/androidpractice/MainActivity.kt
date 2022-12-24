package com.example.androidpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.lang.Math.abs
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    var p_num = 3
    var k = 1
    var point_list = mutableListOf<Float>()

    fun start() {
        setContentView(R.layout.activity_start)
        val tv_pnum: TextView = findViewById(R.id.tv_pnum2)
        val btn_minus: TextView = findViewById(R.id.btn_minus)
        val btn_plus: TextView = findViewById(R.id.btn_plus)
        val btn_start: TextView = findViewById(R.id.btn_start)

        tv_pnum.text = p_num.toString()

        btn_minus.setOnClickListener {
            p_num--
            if (p_num == 0) {
                p_num = 1
            }
            tv_pnum.text = p_num.toString()
        }
        btn_plus.setOnClickListener {
            p_num++
            tv_pnum.text = p_num.toString()
        }
        btn_start.setOnClickListener {
            main()
        }
    }

    fun main() {
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.tv_pnum)
        val tv_t: TextView = findViewById(R.id.tv_timer)
        val tv_p: TextView = findViewById(R.id.tv_point)
        val tv_people: TextView = findViewById(R.id.tv_people)
        val btn: Button = findViewById(R.id.btn_start)

        val random_box = Random()
        val num = random_box.nextInt(1001)

        var sec: Int = 0
        var timerTask: Timer? = null
        var stage: Int = 1

        tv.text = (num.toFloat() / 100).toString()
        btn.text = "시작"
        tv_people.text = "참가자 $k"

        btn.setOnClickListener {
            stage++

            if (stage == 2) {
                timerTask = timer(period = 10) {
                    sec++
                    runOnUiThread {
                        tv_t.text = (sec.toFloat()/100).toString()
                    }
                }
                btn.text = "정지"
            } else if (stage == 3) {
                timerTask?.cancel()
                val point = abs(sec - num).toFloat() / 100
                point_list.add(point)

                tv_p.text = point.toString()
                btn.text = "다음"
                stage = 0
            } else if (stage == 1) {
                if (k < p_num) {
                    k++
                    main()
                } else {
                    end()
                }
            }
        }
    }

    fun end() {
        setContentView(R.layout.activity_end)
        val tv_last: TextView = findViewById(R.id.tv_last)
        val tv_lpoint: TextView = findViewById(R.id.tv_lpoint)
        val btn_init: Button = findViewById(R.id.btn_init)

        tv_lpoint.text = point_list.maxOrNull().toString()
        var index_last = point_list.indexOf(point_list.maxOrNull())
        tv_last.text = "참가자 " + (index_last + 1).toString()

        btn_init.setOnClickListener {
            point_list.clear()
            k = 1
            start()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        start()
    }
}