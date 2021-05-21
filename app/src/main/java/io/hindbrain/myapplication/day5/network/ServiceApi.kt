package io.hindbrain.myapplication.day5.network

import android.content.Context
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import io.hindbrain.myapplication.MyApplication
import io.hindbrain.myapplication.day5.model.User
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface ServiceApi {

    @GET("api/v1/users")
    fun getAllUsers(): Call<List<User>>


    companion object{
        var INSTANCE:ServiceApi? = null
        fun invoke(context: Context):ServiceApi{
            if(INSTANCE==null) {
                val BASE_URL = "https://60a7505b3b1e130017175b80.mockapi.io/"
                val httpClient = OkHttpClient.Builder()
                    .addInterceptor(ChuckInterceptor(context))
                    .connectTimeout(30,TimeUnit.SECONDS)
                    .readTimeout(20,TimeUnit.SECONDS)
                    .writeTimeout(20,TimeUnit.SECONDS)
                    .build()

                val converter = GsonBuilder()
                        .create()

                INSTANCE = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create(converter))
                    .build().create(ServiceApi::class.java)
            }
            return INSTANCE!!
        }
    }
}