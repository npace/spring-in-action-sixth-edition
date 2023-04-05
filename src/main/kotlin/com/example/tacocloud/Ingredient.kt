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

    companion object {
        val allIngredients = listOf(
            Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.Wrap),
            Ingredient("COTO", "Corn Tortilla", Ingredient.Type.Wrap),
            Ingredient("GRBF", "Ground Beef", Ingredient.Type.Protein),
            Ingredient("CARN", "Carnitas", Ingredient.Type.Protein),
            Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.Veggies),
            Ingredient("LETC", "Lettuce", Ingredient.Type.Veggies),
            Ingredient("CHED", "Cheddar", Ingredient.Type.Cheese),
            Ingredient("JACK", "Monterrey Jack", Ingredient.Type.Cheese),
            Ingredient("SLSA", "Salsa", Ingredient.Type.Sauce),
            Ingredient("SRCR", "Sour Cream", Ingredient.Type.Sauce),
        )
    }
}
