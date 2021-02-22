package com.example.rickandmortyandroid.models.data

import java.io.Serializable

data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
) : Serializable
