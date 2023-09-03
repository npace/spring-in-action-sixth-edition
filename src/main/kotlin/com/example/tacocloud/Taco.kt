package com.example.tacocloud

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.util.*

@Entity
data class Taco(
    @field:Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,

    var createdAt: Date = Date(),

    @NotNull
    @field:Size(min = 5, message = "Name must be at least 5 characters long")
    var name: String = "",

    @ManyToMany
    @field:Size(min = 1, message = "You must choose at least 1 ingredient")
    var ingredients: List<Ingredient> = emptyList()
)
