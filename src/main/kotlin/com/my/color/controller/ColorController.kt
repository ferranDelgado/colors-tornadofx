package com.my.color.controller

import com.my.color.model.Color
import tornadofx.*

class ColorController : Controller() {
    val colors = SortedFilteredList<Color>()
}