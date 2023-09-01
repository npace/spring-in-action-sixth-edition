package com.example.tacocloud.data

import com.example.tacocloud.Ingredient
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.util.*

@Repository
class JdbcIngredientRepository(
    private val jdbcTemplate: JdbcTemplate,
) : IngredientRepository {

    override fun findAll(): Iterable<Ingredient> {
        return jdbcTemplate.query(
            "select id, name, type from Ingredient",
            ::mapRowToIngredient,
        )
    }

    override fun findById(id: String): Optional<Ingredient> {
        val results = jdbcTemplate.query(
            "select id, name, type from Ingredient where id=?",
            ::mapRowToIngredient,
            id,
        )
        return if (results.isEmpty()) {
            Optional.empty()
        } else {
            Optional.of(results.first())
        }
    }

    override fun save(ingredient: Ingredient): Ingredient {
        jdbcTemplate.update(
            "insert into Ingredient (id, name, type) values (?, ?, ?)",
            ingredient.id,
            ingredient.name,
            ingredient.type.toString(),
        )
        return ingredient
    }

    private fun mapRowToIngredient(row: ResultSet, rowNum: Int): Ingredient {
        return Ingredient(
            row.getString("id"),
            row.getString("name"),
            Ingredient.Type.valueOf(row.getString("type")),
        )
    }
}