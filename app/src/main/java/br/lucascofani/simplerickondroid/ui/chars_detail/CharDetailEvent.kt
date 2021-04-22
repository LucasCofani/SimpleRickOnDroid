package br.lucascofani.simplerickondroid.ui.chars_detail

sealed class CharDetailEvent {
    data class getCharEvent(val id : Int) : CharDetailEvent()
}