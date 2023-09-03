package com.example.tacocloud

import org.springframework.data.annotation.Id

data class Ingredient(
    @field:Id val id: String,
    val name: String,
    val type: Type,
) {
    enum class Type {
        Wrap,
        Protein,
        Veggies,
        Cheese,
        Sauce,
    }
}
