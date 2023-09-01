package com.example.tacocloud.data

import com.example.tacocloud.Ingredient
import java.util.*

interface IngredientRepository {

    fun findAll(): Iterable<Ingredient>
    fun findById(id: String): Optional<Ingredient>
    fun save(ingredient: Ingredient): Ingredient
}