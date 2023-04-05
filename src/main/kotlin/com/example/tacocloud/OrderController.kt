package com.example.tacocloud

import mu.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.bind.support.SessionStatus

@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
class OrderController {
    private val logger = KotlinLogging.logger {  }

    @GetMapping("/current")
    fun orderForm(): String {
        return "orderForm"
    }

    @PostMapping
    fun processOrder(order: TacoOrder, sessionStatus: SessionStatus): String {
        logger.info { "Order submitted: $order" }
        sessionStatus.setComplete()

        return "redirect:/"
    }
}