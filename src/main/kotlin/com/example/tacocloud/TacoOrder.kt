package com.example.tacocloud

import jakarta.validation.constraints.Digits
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import org.hibernate.validator.constraints.CreditCardNumber
import java.util.*

data class TacoOrder(
    var id: Long = 0,
    var placedAt: Date = Date(),

    @field:NotBlank(message = "Delivery name is required")
    var deliveryName: String? = null,

    @field:NotBlank(message = "Street is required")
    var deliveryStreet: String? = null,

    @field:NotBlank(message = "City is required")
    var deliveryCity: String? = null,

    @field:NotBlank(message = "State is required")
    var deliveryState: String? = null,

    @field:NotBlank(message = "Zip code is required")
    var deliveryZip: String? = null,

    @field:CreditCardNumber(message = "Not a valid credit card number")
    var ccNumber: String? = null,

    @field:Pattern(
        regexp = "^(0[1-9]|1[0-2])(/)([2-9][0-9])$",
        message = "Must be formatted MM/YY"
    )
    var ccExpiration: String? = null,

    @field:Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    var ccCVV: String? = null,

    val tacos: MutableList<Taco> = mutableListOf(),
) {

    fun addTaco(taco: Taco) {
        tacos.add(taco)
    }
}
