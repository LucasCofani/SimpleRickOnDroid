package com.example.rickandmortyandroid.models.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity
data class Location(
        val name: String,
        val type: String,
        val dimension: String,
        val residents: List<String>,
        val url: String,
        val created: String,
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0
): Serializable