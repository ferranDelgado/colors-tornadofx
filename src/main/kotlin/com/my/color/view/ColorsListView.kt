package com.my.color.view

import javafx.scene.layout.Priority
import com.my.color.controller.ColorController
import com.my.color.model.Color
import tornadofx.*


class ColorsListView : View() {

    private val colorController: ColorController by inject()

    init {
       colorController.load()
    }

    override val root = tableview (colorController.colors) {
        vgrow = Priority.ALWAYS
        column("ID", Color::id)
        column("Nom", Color::name)
        column("Hexadecimal", Color::hex)
    }
}