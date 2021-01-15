package com.example.rickandmortyandroid.database

import androidx.room.TypeConverter


class Converters {
    @TypeConverter
    fun toString(value: List<String?>?): String {
        var res = ""
        value?.forEach {
            res+= ";$it"
        }
        return res
    }
    @TypeConverter
    fun toListString(value: String) : List<String>{
        return value.split(";")
    }
}