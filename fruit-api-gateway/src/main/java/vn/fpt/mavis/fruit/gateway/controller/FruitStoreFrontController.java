package vn.fpt.mavis.fruit.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.fpt.mavis.fruit.gateway.client.GatewayCatalogFeignClient;
import vn.fpt.mavis.fruit.gateway.client.GatewayOrderFeignClient;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/storefront")
public class FruitStoreFrontController {

    private final GatewayCatalogFeignClient catalogFeignClient;
    private final GatewayOrderFeignClient orderFeignClient;

    public FruitStoreFrontController(GatewayCatalogFeignClient catalogFeignClient,
                                     GatewayOrderFeignClient orderFeignClient) {
        this.catalogFeignClient = catalogFeignClient;
        this.orderFeignClient = orderFeignClient;
    }

    @GetMapping("/catalog")
    public List<Map<String, Object>> browseFruits() {
        return catalogFeignClient.listCatalogFruits();
    }

    @GetMapping("/fruits/{id}")
    public Map<String, Object> inspectFruit(@PathVariable("id") Long id) {
        return catalogFeignClient.getFruitById(id);
    }

    @PostMapping("/orders/checkout")
    public Map<String, Object> placeFruitOrder(@RequestBody Map<String, Object> request) {
        return orderFeignClient.submitCheckout(request);
    }

    @GetMapping("/orders/{orderId}")
    public Map<String, Object> getOrderSummary(@PathVariable("orderId") Long orderId) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("gateway", "fruit-api-gateway");
        result.put("order", orderFeignClient.getOrderDetails(orderId));
        return result;
    }
}
