package br.lucascofani.simplerickondroid.models.episode

data class EpisodeDto(
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String,
    val id: Long
)