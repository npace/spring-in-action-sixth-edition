package com.example.tacocloud

import com.example.tacocloud.data.OrderRepository
import jakarta.validation.Valid
import mu.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.bind.support.SessionStatus

@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
class OrderController(
    private val orderRepository: OrderRepository,
) {
    private val logger = KotlinLogging.logger { }

    @GetMapping("/current")
    fun orderForm(): String {
        return "orderForm"
    }

    @PostMapping
    fun processOrder(
        @Valid order: TacoOrder,
        errors: Errors,
        sessionStatus: SessionStatus
    ): String {
        logger.info { "Order submitted: $order\nerrors: $errors" }

        if (errors.hasErrors()) {
            return "orderForm"
        }
        orderRepository.save(order)
        sessionStatus.setComplete()

        return "redirect:/"
    }
}