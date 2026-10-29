package vn.fpt.mavis.fruit.order.controller;

import org.springframework.web.bind.annotation.*;
import vn.fpt.mavis.fruit.order.entity.FruitOrderEntity;
import vn.fpt.mavis.fruit.order.service.FruitOrderService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class FruitOrderController {

    private final FruitOrderService fruitOrderService;

    public FruitOrderController(FruitOrderService fruitOrderService) {
        this.fruitOrderService = fruitOrderService;
    }

    @GetMapping
    public List<FruitOrderEntity> listAllOrders() {
        return fruitOrderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    public FruitOrderEntity getOrderDetails(@PathVariable("orderId") Long orderId) {
        return fruitOrderService.getOrderById(orderId);
    }

    @PostMapping("/checkout")
    public FruitOrderEntity submitCheckout(@RequestBody Map<String, Object> payload) {
        Long fruitId = payload.get("fruitId") != null ? Long.valueOf(String.valueOf(payload.get("fruitId"))) : 1L;
        String fruitCode = String.valueOf(payload.getOrDefault("fruitCode", "DURIAN-RI6"));
        String customerName = String.valueOf(payload.getOrDefault("customerName", "Le Minh Triet"));
        Integer quantityKg = payload.get("quantityKg") != null ? Integer.valueOf(String.valueOf(payload.get("quantityKg"))) : 5;
        return fruitOrderService.checkoutFruitOrder(fruitId, fruitCode, customerName, quantityKg);
    }

    @PutMapping("/{orderId}/delivery-status")
    public FruitOrderEntity receiveDeliveryCallback(@PathVariable("orderId") Long orderId,
                                                    @RequestBody Map<String, Object> callbackBody) {
        String status = String.valueOf(callbackBody.getOrDefault("deliveryStatus", "DELIVERED"));
        return fruitOrderService.updateDeliveryStatus(orderId, status);
    }
}
