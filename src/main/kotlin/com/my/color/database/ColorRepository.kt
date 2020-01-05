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

    fun insert(colorName: String, hex: String): Color {
        val insertQuery = """
                           INSERT INTO Color (name, hex) 
                           VALUES(:name, :hex) 
                        """
        return db.withHandle<Color, Exception> { handle ->
            val id = handle
                    .createUpdate(insertQuery)
                    .bind("name", colorName)
                    .bind("hex", hex)
                    .executeAndReturnGeneratedKeys("id")
                    .mapTo(Int::class.java)
                    .one()

            Color(
                    id = id,
                    name = colorName,
                    hex = hex
            )
        }
    }

    fun update(color: Color) : Color {
        val updateQuery = """
                            UPDATE Color
                            SET 
                                name =:name,
                                hex =:hex
                            WHERE id = :id
                        """
        db.useTransaction<Exception> { handle ->
            val modifiedRows = handle
                    .createUpdate(updateQuery)
                    .bind("name", color.name)
                    .bind("hex", color.hex)
                    .bind("id", color.id)
                    .execute()
            require(modifiedRows > 0) { "Impossible to update Color: ${color.id}" }
        }

        return color
    }
}