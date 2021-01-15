package com.example.rickandmortyandroid.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Character(
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    @Embedded(prefix = "origin_")
    val origin: LocationResume,
    @Embedded(prefix = "location_")
    val location: LocationResume,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
) : Serializable