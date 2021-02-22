package com.example.rickandmortyandroid.models.data

data class ResultWrapper<T>(
        val info : Info?,
        val results : List<T>
)