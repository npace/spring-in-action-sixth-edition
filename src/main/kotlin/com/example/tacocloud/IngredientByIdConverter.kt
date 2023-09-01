package com.example.tacocloud

import com.example.tacocloud.data.IngredientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class IngredientByIdConverter @Autowired constructor(
    private val ingredientRepository: IngredientRepository,
) : Converter<String, Ingredient> {

    override fun convert(source: String): Ingredient? {
        return ingredientRepository.findById(source).orElse(null)
    }
}