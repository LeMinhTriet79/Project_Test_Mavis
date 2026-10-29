package vn.fpt.mavis.fruit.pricing.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/pricing")
public class FruitPricingController {

    @GetMapping("/quote/{fruitCode}")
    public Map<String, Object> getFruitQuote(@PathVariable("fruitCode") String fruitCode) {
        long unitPrice = switch (fruitCode.toUpperCase()) {
            case "DURIAN-RI6" -> 125000L;
            case "MANGO-CAT-HOA-LOC" -> 85000L;
            case "POMELO-DA-XANH" -> 65000L;
            default -> 55000L;
        };
        return Map.of(
                "fruitCode", fruitCode,
                "unitPricePerKgVnd", unitPrice,
                "currency", "VND"
        );
    }

    @PostMapping("/discount/calculate")
    public Map<String, Object> calculateBulkDiscount(@RequestBody Map<String, Object> discountRequest) {
        String fruitCode = String.valueOf(discountRequest.getOrDefault("fruitCode", "DURIAN-RI6"));
        int quantityKg = discountRequest.get("quantityKg") != null
                ? Integer.parseInt(String.valueOf(discountRequest.get("quantityKg")))
                : 1;
        int discountPercent = quantityKg >= 10 ? 15 : (quantityKg >= 5 ? 8 : 0);
        return Map.of(
                "fruitCode", fruitCode,
                "quantityKg", quantityKg,
                "discountPercent", discountPercent
        );
    }

    @GetMapping("/seasonal-rate/{season}")
    public Map<String, Object> getSeasonalMultiplier(@PathVariable("season") String season) {
        return Map.of(
                "season", season,
                "multiplier", 0.95,
                "promotionNote", "Mua thu hoach trai cay Mien Tay giam 5%"
        );
    }
}
