package com.example.tacocloud

import com.example.tacocloud.Ingredient.Type
import com.example.tacocloud.data.IngredientRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@SpringBootApplication
class TacoCloudApplication

fun main(args: Array<String>) {
    runApplication<TacoCloudApplication>(*args)
}

@Configuration
class Config {

    @Bean
    fun dataLoader(repo: IngredientRepository): CommandLineRunner = CommandLineRunner {
        repo.save(Ingredient("FLTO", "Flour Tortilla", Type.Wrap))
        repo.save(Ingredient("COTO", "Corn Tortilla", Type.Wrap))
        repo.save(Ingredient("GRBF", "Ground Beef", Type.Protein))
        repo.save(Ingredient("CARN", "Carnitas", Type.Protein))
        repo.save(Ingredient("TMTO", "Diced Tomatoes", Type.Veggies))
        repo.save(Ingredient("LETC", "Lettuce", Type.Veggies))
        repo.save(Ingredient("CHED", "Cheddar", Type.Cheese))
        repo.save(Ingredient("JACK", "Monterrey Jack", Type.Cheese))
        repo.save(Ingredient("SLSA", "Salsa", Type.Sauce))
        repo.save(Ingredient("SRCR", "Sour Cream", Type.Sauce))
    }
}
