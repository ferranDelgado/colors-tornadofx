package com.my.color.view

import javafx.scene.layout.Priority
import com.my.color.controller.ColorController
import com.my.color.model.Color
import tornadofx.*


class ColorsListView : View() {

    override fun onSave() {
        println("save")
    }

    override fun onCreate() {
        println("New color")
        model.rebind {
            item = null
        }
    }

    override fun onRefresh() {
        colorController.load()
    }

    private val colorController: ColorController by inject()
    val model by inject<ColorModel>()

    init {
        colorController.load()
    }

    override val root = hbox {
        add(tableView())
        add(colorForm())
    }

    class ColorModel : ItemViewModel<Color>() {
        val id = bind(Color::idProperty)
        val name = bind(Color::nameProperty)
        val hex = bind(Color::hexProperty)
    }

    private fun colorForm() = form {
        fieldset("Color Information") {
            field("Id") {
                isDisable = true
                textfield().bind(model.id)
            }
            field("Name") {
                textfield().bind(model.name)
            }

            field("Hexadecimal") {
                textfield().bind(model.hex)
            }
        }
        hbox {
            button("Save") {
                val btnText = model.itemProperty.isNull.stringBinding {
                    if (it == true) {
                        "Create"
                    } else {
                        "Save"
                    }
                }
                textProperty().bind(btnText)
                hboxConstraints { hGrow = Priority.ALWAYS }
                useMaxWidth = true
                enableWhen(model.dirty)
                action {
                    println("Save ${model.id} - ${model.name}")
                    model.commit()
                    runAsync {
                        if (model.isEmpty) {
                            colorController.insert(model.name.value, model.hex.value)
                        } else {
                            colorController.update(model.item!!)
                        }
                    } ui { saved ->
                        model.rebind {
                            item = saved
                        }
                    }
                }
            }
        }
    }

    private fun tableView() = tableview(colorController.colors) {
        vgrow = Priority.ALWAYS
        column("ID", Color::id)
        column("Nom", Color::nameProperty)
        column("Hexadecimal", Color::hexProperty)
        bindSelected(model)
    }
}