package com.my.color.view

import com.my.color.database.initDb
import tornadofx.*

fun main() {
    launch<ColorsApp>()
}

class ColorsApp : App(ColorsWorkspace::class) {
    init {
        initDb("colors.db")
    }

    override fun onBeforeShow(view: UIComponent) {
        workspace.dock<ColorsListView>()
    }
}

class ColorsWorkspace : Workspace()

