package io.hindbrain.myapplication.day5.model

import com.google.gson.annotations.SerializedName
import java.util.*

//{
//"id": "1",
//"createdAt": "2021-05-20T23:43:44.953Z",
//"name": "Becky Kuhic",
//"avatar": "https://cdn.fakercloud.com/avatars/alexivanichkin_128.jpg",
//"address": "88055 Hyman Forges"
//},
data class User(
    val id:Int,
    @SerializedName("createdAt")
    val created:Date,
    val name:String,
    val avatar:String,
    val address:String
)
