package vn.fpt.mavis.fruit.catalog.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "fruit-pricing-service", url = "http://fruit-pricing-service:8084")
public interface SeasonalPricingFeignClient {

    @GetMapping("/api/pricing/seasonal-rate/{season}")
    Map<String, Object> getSeasonalMultiplier(@PathVariable("season") String season);
}
