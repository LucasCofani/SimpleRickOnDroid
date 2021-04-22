package br.lucascofani.simplerickondroid.ui.chars_list

sealed class CharListEvent {
    object NewSearchEvent : CharListEvent()
}