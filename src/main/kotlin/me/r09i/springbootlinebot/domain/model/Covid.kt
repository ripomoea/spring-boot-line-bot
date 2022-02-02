package me.r09i.springbootlinebot.domain.model

data class Covid(
    val country: String,
    val code: String,
    val confirmed: Int,
    val recovered: Int,
    val critical: Int,
    val deaths: Int,
    val latitude: Float,
    val longitude: Float,
    val lastChange: String,
    val lastUpdate: String,
)