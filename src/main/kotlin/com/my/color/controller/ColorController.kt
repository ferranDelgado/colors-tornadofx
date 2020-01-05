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

    fun insert(colorName: String, hex: String): Color {
        val color = ColorRepository.insert(colorName, hex)
        colors.add(color)
        return color
    }

    fun update(color: Color): Color {
        return ColorRepository.update(color)
    }

}