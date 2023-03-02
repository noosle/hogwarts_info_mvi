package ru.noosle.harry_potter_mvi.ui.main.base

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        var retrofitService: RetrofitClient? = null
        fun getInstance() : RetrofitClient {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://hp-api.onrender.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitClient::class.java)
            }
            return retrofitService!!
        }
    }
}