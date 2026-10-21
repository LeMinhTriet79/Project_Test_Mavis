# Tropical Fruit Market Microservices (Dự Án Mẫu Kiểm Thử Chuẩn Cho Hệ Thống MAVIS V2)

Dự án hệ thống Thương mại Điện tử Trái cây Nhiệt đới (**Mekong Fresh Fruit Market**) được thiết kế chuẩn mực để kiểm thử toàn diện các tính năng của nền tảng **MAVIS V2**:

## 1. Danh sách các Microservices (7 Dịch vụ Ứng dụng + 4 Database + 1 Message Broker)
1. **`api-gateway`** (Port `8080` - Java Spring Boot): Cổng giao tiếp trung tâm điều phối yêu cầu tới Catalog, Order và Delivery.
2. **`fruit-catalog-service`** (Port `8081` - Java Spring Boot + PostgreSQL `catalog-db`): Quản lý danh mục trái cây đặc sản (Sầu riêng Ri6, Xoài cát Hòa Lộc, Bưởi da xanh, Thanh long ruột đỏ), vùng trồng và bảng giá mùa vụ. Gọi sang `fruit-inventory-service`.
3. **`fruit-inventory-service`** (Port `8082` - Java Spring Boot + PostgreSQL `inventory-db`): Quản lý tồn kho lạnh (Cold Storage), lô hàng thu hoạch (Harvest Batch) và giữ chỗ tồn kho khi đặt đơn.
4. **`fruit-order-service`** (Port `8083` - Java Spring Boot + PostgreSQL `order-db`): Xử lý giỏ hàng và đơn đặt mua trái cây. Gọi sang `fruit-catalog-service`, `fruit-inventory-service`, `fruit-payment-service` và `fruit-delivery-service`.
5. **`fruit-payment-service`** (Port `8084` - Java Spring Boot + PostgreSQL `payment-db`): Xử lý thanh toán đơn hàng và hoàn tiền bảo hành trái cây. Gọi sang `fruit-notification-service`.
6. **`fruit-delivery-service`** (Port `8085` - Java Spring Boot): Quản lý vận chuyển xe lạnh (Cold-Chain Logistics) và theo dõi nhiệt độ bảo quản trái cây. Gọi sang `fruit-notification-service`.
7. **`fruit-notification-service`** (Port `8086` - Đa ngôn ngữ Java Spring Boot & C# ASP.NET Core): Gửi email/SMS xác nhận đơn hàng, hóa đơn thanh toán và cảnh báo nhiệt độ giao hàng.

## 2. Chuỗi Phụ Thuộc Đa Tầng (Dùng để Test Thuật toán BFS Change Impact Analysis)
- `api-gateway` -> `fruit-order-service` -> `fruit-payment-service` -> `fruit-notification-service`
- `api-gateway` -> `fruit-order-service` -> `fruit-catalog-service` -> `fruit-inventory-service`
- `api-gateway` -> `fruit-order-service` -> `fruit-delivery-service` -> `fruit-notification-service`

### Kịch bản Test Change Impact Analysis (Gợi ý khi Demo):
- **Khi chọn mục tiêu là `fruit-notification-service`**:
  - **Bị ảnh hưởng trực tiếp (Hop = 1 - DIRECTLY_IMPACTED)**: `fruit-payment-service`, `fruit-delivery-service`
  - **Bị ảnh hưởng gián tiếp (Hop = 2 - INDIRECTLY_IMPACTED)**: `fruit-order-service`
  - **Bị ảnh hưởng gián tiếp (Hop = 3 - INDIRECTLY_IMPACTED)**: `api-gateway`
- **Khi chọn mục tiêu là `fruit-inventory-service`**:
  - **Bị ảnh hưởng trực tiếp (Hop = 1 - DIRECTLY_IMPACTED)**: `fruit-catalog-service`, `fruit-order-service`
  - **Bị ảnh hưởng gián tiếp (Hop = 2 - INDIRECTLY_IMPACTED)**: `api-gateway`
