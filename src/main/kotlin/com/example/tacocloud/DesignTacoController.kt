package com.example.tacocloud

import com.example.tacocloud.data.IngredientRepository
import jakarta.validation.Valid
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import java.util.*

@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
class DesignTacoController @Autowired constructor(
    private val ingredientRepository: IngredientRepository,
) {

    private val logger = KotlinLogging.logger { }

    @ModelAttribute
    fun addIngredientsToModel(model: Model) {
        val ingredients = ingredientRepository.findAll()
        Ingredient.Type.values().forEach { type ->
            model.addAttribute(
                type.toString().lowercase(Locale.US),
                ingredients.filter { it.type == type }
            )
        }
    }

    @ModelAttribute(name = "tacoOrder")
    fun order() = TacoOrder()

    @ModelAttribute(name = "taco")
    fun taco() = Taco()

    @GetMapping
    fun showDesignForm(): String {
        return "design"
    }

    @PostMapping
    fun processTaco(
        @Valid taco: Taco,
        errors: Errors,
        @ModelAttribute tacoOrder: TacoOrder
    ): String {
        logger.info { "Processing taco $taco\nerrors: $errors" }

        if (errors.hasErrors()) {
            return "design"
        }

        tacoOrder.addTaco(taco)

        return "redirect:/orders/current"
    }
}
