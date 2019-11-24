package com.my.color.model

import javafx.beans.property.*
import tornadofx.*
import java.time.LocalDate

class Color(
        id: Int,
        name: String,
        hex: String
) {

    val idProperty = SimpleIntegerProperty(this, "id", id)
    var id by idProperty

    val nameProperty = SimpleStringProperty(this, "name", name)
    var name by nameProperty

    val hexProperty = SimpleStringProperty(this, "hex", hex)
    var hex by hexProperty
}