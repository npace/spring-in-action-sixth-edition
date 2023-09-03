package com.example.tacocloud

import jakarta.validation.constraints.Size
import java.util.*

data class Taco(
    var id: Long = 0,
    var createdAd: Date = Date(),

    var createdAt: Date = Date(),

    @field:Size(min = 5, message = "Name must be at least 5 characters long")
    var name: String = "",

    @field:Size(min = 1, message = "You must choose at least 1 ingredient")
    var ingredients: List<Ingredient> = emptyList()
)
