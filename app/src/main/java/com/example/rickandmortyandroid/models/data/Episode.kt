package com.example.rickandmortyandroid.models.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Episode(
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
): Serializable