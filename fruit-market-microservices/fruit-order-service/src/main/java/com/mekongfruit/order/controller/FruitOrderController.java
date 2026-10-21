package com.mekongfruit.order.controller;

import com.mekongfruit.order.entity.FruitOrder;
import com.mekongfruit.order.service.FruitOrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class FruitOrderController {

    private final FruitOrderService fruitOrderService;

    public FruitOrderController(FruitOrderService fruitOrderService) {
        this.fruitOrderService = fruitOrderService;
    }

    @PostMapping
    public FruitOrder createFruitOrder(@RequestBody FruitOrder order, @RequestParam(defaultValue = "1") Long fruitId) {
        return fruitOrderService.placeFruitOrder(order, fruitId);
    }

    @GetMapping("/{orderId}")
    public FruitOrder getOrderDetails(@PathVariable Long orderId) {
        return fruitOrderService.getOrderById(orderId);
    }
}
