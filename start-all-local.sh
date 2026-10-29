#!/usr/bin/env bash
set -e
echo "Building all 6 Fruit Store Microservices..."
mvn clean package -DskipTests

java -jar fruit-pricing-service/target/fruit-pricing-service-1.0.0.jar &
PID_PRICING=$!
java -jar fruit-catalog-service/target/fruit-catalog-service-1.0.0.jar &
PID_CATALOG=$!
java -jar fruit-payment-service/target/fruit-payment-service-1.0.0.jar &
PID_PAYMENT=$!
java -jar fruit-delivery-service/target/fruit-delivery-service-1.0.0.jar &
PID_DELIVERY=$!
sleep 3
java -jar fruit-order-service/target/fruit-order-service-1.0.0.jar &
PID_ORDER=$!
java -jar fruit-api-gateway/target/fruit-api-gateway-1.0.0.jar &
PID_GATEWAY=$!

echo "All 6 services started! Press Ctrl+C to stop."
trap "kill $PID_PRICING $PID_CATALOG $PID_PAYMENT $PID_DELIVERY $PID_ORDER $PID_GATEWAY 2>/dev/null" EXIT
wait
