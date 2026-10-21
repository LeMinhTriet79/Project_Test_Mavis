package com.mekongfruit.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;

@FeignClient(name = "fruit-payment-service", url = "http://fruit-payment-service:8084")
public interface PaymentServiceClient {

    @PostMapping("/api/v1/payments/charge")
    Map<String, Object> chargeOrderPayment(@RequestBody Map<String, Object> paymentRequest);
}
