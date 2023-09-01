package com.example.tacocloud

data class Ingredient(
    val id: String,
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
