package br.lucascofani.simplerickondroid.models.util

data class ResultWrapper<T>(
    val info : Info?,
    val results : List<T>
)