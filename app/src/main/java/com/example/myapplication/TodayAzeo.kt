package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_today_azeo.*
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class TodayAzeo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_today_azeo)

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
                    TAque.text=naver.que
                    TAanswer.text=naver.answer+" 엌ㅋㅋㅋㅋ"
                }
            }

        })
    }

    fun setText(data : MainActivity.azeo){
        que.text=data.que
    }

    data class azeo(val que: String, val answer:String)
}