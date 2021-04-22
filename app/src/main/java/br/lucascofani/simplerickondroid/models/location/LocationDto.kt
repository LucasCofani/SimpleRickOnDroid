package br.lucascofani.simplerickondroid.models.location

data class LocationDto(
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String,
    val id: Long
)