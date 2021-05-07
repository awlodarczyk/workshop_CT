package io.hindbrain.myapplication.day4.model

import java.io.Serializable

enum class Priority{
    LOW,MEDIUM,HIGH
}
data class ShoppingItem(
    var name:String,
    var qty: Int,
    var priority:Priority,
    var comment:String
):Serializable