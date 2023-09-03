package com.example.tacocloud

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Ingredient(
    @field:Id val id: String = "",
    val name: String = "",
    val type: Type = Type.Wrap,
) {
    enum class Type {
        Wrap,
        Protein,
        Veggies,
        Cheese,
        Sauce,
    }
}
