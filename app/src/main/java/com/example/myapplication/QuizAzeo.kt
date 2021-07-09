package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_quiz_azeo.*
import kotlinx.android.synthetic.main.activity_today_azeo.*
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class QuizAzeo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_azeo)

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
                println("로딩실패")
            }

            override fun onResponse(call: okhttp3.Call, response: Response) {
                val body = response?.body()?.string()
                println(body)
                val gson = GsonBuilder().create()
                val naver = gson.fromJson(body, azeo::class.java)


                runOnUiThread {
                    QAque.text=naver.que
                    answerBtn.setOnClickListener {
                        Log.d("asdff", "onResponse: ${naver.que} ${naver.answer}")
                        if(naver.answer==QAanswer.text.toString()) sucuss.text="정답입니다!"
                        else if(QAanswer.text.toString()==null) Toast.makeText(
                            this@QuizAzeo,
                            "정답을 적어주세요.",
                            Toast.LENGTH_SHORT
                        ).show()
                        else sucuss.text="정답이 아닌데~ ㅋㅋ 정답은 "+naver.answer+" ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ"
                        Log.d("ㅁㄴㅇㄹ", "onResponse: 클릭됨")
                    }
                }
            }
        })
    }


    data class azeo(val que: String, val answer:String)
}