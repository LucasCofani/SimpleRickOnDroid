package br.lucascofani.simplerickondroid.models.character

import br.lucascofani.simplerickondroid.models.util.LocationResume

data class CharacterDto(
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: LocationResume,
    val location: LocationResume,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String,
    val id: Long
)