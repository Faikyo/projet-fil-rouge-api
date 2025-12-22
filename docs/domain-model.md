# Domain Model (V1)

## Entités
- User { id, email, role, active }
- ServiceCatalogItem { id, name, price, active }
- Order { id, customerId, status, items }
- OrderItem { id, serviceId, unitPrice, quantity }
- Invoice { id, orderId, issuedAt, amount }

## Enums
- Role = CLIENT | ADMIN
- OrderStatus = CREATED | VALIDATED | PAID | CANCELLED
