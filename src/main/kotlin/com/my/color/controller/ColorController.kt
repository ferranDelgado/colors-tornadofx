package com.my.color.controller

import com.my.color.database.ColorRepository
import com.my.color.model.Color
import tornadofx.*

class ColorController : Controller() {
    val colors = SortedFilteredList<Color>()

    fun load() {
        colors.clear()
        colors.addAll(ColorRepository.findAll())
    }
}