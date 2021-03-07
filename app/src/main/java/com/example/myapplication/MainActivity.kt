package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.widget.Toast
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        getApi()
    }

    fun getApi(){
        println("getApi")

        var serviceKey = "KSudKwnrnmAe3IzRikeHzW2vfVQ0zAsieDXdBzd1IZZgWMlJPjJJQfp8LDu8jNmlSU0kibIK%2F0fm2x%2BhachMHw%3D%3D"
        val url = "https://aje.teamwv.ml/api/json"

        val client = OkHttpClient.Builder().build()

        val request  = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                println("로딩실패 개새끼")
            }

            override fun onResponse(call: okhttp3.Call, response: Response) {
                val body = response?.body()?.string()
                println(body)
                val gson = GsonBuilder().create()
                val naver = gson.fromJson(body, azeo::class.java)


                runOnUiThread {
                    que.text="님들 그거 앎?"+naver.que
                    answer.text=naver.answer+"임 엌ㅋㅋ"
                    btn.setOnClickListener {

                        if(answer.text.toString()==naver.answer) Toast.makeText(applicationContext, "정답입니다.", Toast.LENGTH_SHORT).show()
                        else Toast.makeText(applicationContext, "정답이 아닙니다. 정답은"+naver.answer+"입니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        })
    }

    fun setText(data : MainActivity.azeo){
        que.text=data.que
    }

    data class azeo(val que: String, val answer:String)
}
