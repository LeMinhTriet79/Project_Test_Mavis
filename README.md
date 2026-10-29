# 🍎 MAVIS Fruit Store Microservices Demo (Java Spring Boot)

Dự án mẫu gồm **6 Microservices Java Spring Boot 3.2** + **PostgreSQL** + **RabbitMQ** chủ đề **Cửa hàng Trái cây Tươi Đặc sản Miền Tây**, được thiết kế chuyên biệt để kiểm thử 100% tính năng của hệ thống **MAVIS**:
1. **Kiểm thử Cây Dẫn chứng Kết nối 2 đầu (`A ↔ B`, `A ↔ C`, `A ↔ D`, `A ↔ E`)**:
   - Đầy đủ tất cả các kiểu kết nối: **Spring Cloud OpenFeign (`@FeignClient`)**, **Spring `RestTemplate`**, **Spring `WebClient`**, **Spring Cloud Gateway Routes**, **RabbitMQ (`RabbitTemplate` / `@RabbitListener`)**, **Spring Data JPA (`JpaRepository` -> PostgreSQL)**, và **Docker Compose (`depends_on` / `environment`)**.
   - Giữa **`fruit-catalog-service` (B)** và **`fruit-payment-service` (C)** hoàn toàn **KHÔNG có kết nối nào**, còn **`fruit-order-service` (A)** là trung tâm kết nối với B, C, D, E.
2. **Kiểm thử Phân tích Tác động khi Sập Service (`/impact` — Blast Radius 1-Hop, 2-Hop, 3-Hop)**.
3. **Kiểm thử Luồng Chạy Thực nghiệm Runtime (`/runtime-config`, `/scenarios`, `/sessions`)**.
