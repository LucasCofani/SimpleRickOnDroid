package br.lucascofani.simplerickondroid.models.episode

import java.util.*

data class Episode(
    val name: String,
    val air_date: Date,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: Date,
    val id: Long
)