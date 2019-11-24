package com.my.color.database

import com.my.color.model.Color

object ColorRepository {
    fun findAll(): List<Color> {
        return db.withHandle<List<Color>, Exception> { handle ->
            handle
                    .createQuery("SELECT * FROM Color")
                    .map { rs, _ ->
                        Color(
                                id = rs.getInt("id"),
                                name = rs.getString("name"),
                                hex = rs.getString("hex")
                        )
                    }.toList()
        }
    }
}