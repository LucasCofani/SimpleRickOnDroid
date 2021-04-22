package br.lucascofani.simplerickondroid.models.location

import java.util.*

data class Location(
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: Date,
    val id: Long
)