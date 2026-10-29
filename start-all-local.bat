@echo off
echo ============================================================================
echo   KHOI DONG HE THONG MICROSERVICES CUA HANG TRAI CAY (CHAY LOCAL VOI H2 DB)
echo ============================================================================
echo Dang build toan bo 6 microservices...
call mvn clean package -DskipTests
if %ERRORLEVEL% NEQ 0 (
    echo [LOI] Build Maven that bai. Hay kiem tra JDK 17+ va Maven!
    pause
    exit /b 1
)

start "1. fruit-pricing-service (8084)" cmd /k "java -jar fruit-pricing-service/target/fruit-pricing-service-1.0.0.jar"
timeout /t 2 >nul
start "2. fruit-catalog-service (8082)" cmd /k "java -jar fruit-catalog-service/target/fruit-catalog-service-1.0.0.jar"
timeout /t 2 >nul
start "3. fruit-payment-service (8083)" cmd /k "java -jar fruit-payment-service/target/fruit-payment-service-1.0.0.jar"
timeout /t 2 >nul
start "4. fruit-delivery-service (8085)" cmd /k "java -jar fruit-delivery-service/target/fruit-delivery-service-1.0.0.jar"
timeout /t 2 >nul
start "5. fruit-order-service (8081)" cmd /k "java -jar fruit-order-service/target/fruit-order-service-1.0.0.jar"
timeout /t 2 >nul
start "6. fruit-api-gateway (8080)" cmd /k "java -jar fruit-api-gateway/target/fruit-api-gateway-1.0.0.jar"

echo Da bat 6 cua so Microservices!
echo - Gateway:  http://localhost:8080/health
echo - Order:    http://localhost:8081/api/orders
echo - Catalog:  http://localhost:8082/api/fruits
echo - Payment:  http://localhost:8083/api/payments/health
echo - Pricing:  http://localhost:8084/api/pricing/quote/DURIAN-RI6
echo - Delivery: http://localhost:8085/api/deliveries/health
pause
