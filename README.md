# Reactive E-commerce Product Management Service

## Architecture Overview
![Architecture](/docs/diagramaproyecto.png)


This project is a microservices-based e-commerce application built using reactive programming principles, Spring WebFlux, Docker, and Clean Architecture. The system consists of the following components:

- Product Service: Manages products and categories
- Customer Service: Handles customer information
- Order Service: Processes and manages orders
- Notification Service: Sends email notifications to customers
- API Gateway: Routes requests to appropriate microservices
- Config Service: Centralizes configuration management
- Discovery Service: Handles service registration and discovery

## Technical Stack

### Reactive Programming & Spring WebFlux
The application leverages reactive programming using Spring WebFlux to build non-blocking, event-driven microservices. This provides several benefits:
- Improved scalability for handling concurrent requests
- Better resource utilization with fewer threads
- Increased responsiveness under high load
- Built-in support for streaming and real-time data

### Clean Architecture
The project follows Clean Architecture principles to ensure:
- Separation of concerns between layers
- Independence from frameworks and external details
- Highly testable business logic
- Flexibility for evolving requirements

The architecture layers include:
- Domain layer (entities, use cases)
- Application layer 
- Infrastructure layer 

### Microservices
The system is built using a microservices architecture, allowing:
- Independent deployment and scaling of services
- Language and technology flexibility
- Resilience and fault isolation
- Team autonomy and focused development

### Docker
Docker is used for containerization, ensuring:
- Consistent environments across development, testing, and production
- Simplified dependency management
- Easy scaling and deployment
- Isolation between services

## API Endpoints

### Category Management
- `POST /api/v1/categoria`: Create a new category
  ```json
  {
    "nombre": "Jeans"
  }
  ```

### Product Management
- `POST /api/v1/producto`: Create a new product
  ```json
  {
    "nombre": "Jean Skinny",
    "sku": "87321312313",
    "descripcion": "Jean al cuerpo",
    "color": "blue",
    "categoriaId": 1,
    "precio": 200000.0,
    "size": "32",
    "stock": 5,
    "imagenes": ["image link"],
    "isActive": true
  }
  ```

- `GET /api/v1/producto/{id}`: Get a specific product by ID
    - No body required

- `GET /api/v1/producto`: List all products
    - No body required

- `PUT /api/v1/producto/{id}`: Update a product
  ```json
  {
    "nombre": "Jean",
    "descripcion": "Jean trapered",
    "color": "blue",
    "precio": 80000,
    "active": true
  }
  ```

- `DELETE /api/v1/producto/{id}`: Delete a product
    - No body required

- `POST /api/v1/producto/purchase`: Process products for purchase
  ```json
  [
    {
      "productId": 5,
      "cantidad": 80
    },
    {
      "productId": 7,
      "cantidad": 3
    }
  ]
  ```

### Product Variants
- `POST /api/v1/product-variant/`: Create a new product variant
  ```json
  {
    "id_product": 2,
    "sku": "Prod 123",
    "size": "M",
    "quantity": 20,
    "price_offset": 20000.0
  }
  ```

### Customer Management
- `POST /api/v1/customer`: Create a new customer
  ```json
  {
    "name": "Jane",
    "lastName": "Doe",
    "email": "janedoe@gmail.com",
    "address": {
      "street": "Calle 456",
      "city": "New York",
      "state": "California",
      "zipCode": "11111"
    }
  }
  ```

- `GET /api/v1/customer`: List all customers
    - No body required

- `GET /api/v1/customer/{id}`: Get a specific customer by ID
    - No body required

- `PUT /api/v1/customer/{id}`: Update a customer
  ```json
  {
    "name": "Jane",
    "lastName": "Doe",
    "email": "janedoe@gmail.com",
    "address": {
      "street": "Calle 456",
      "city": "New York",
      "state": "California",
      "zipCode": "11111"
    }
  }
  ```

- `DELETE /api/v1/customer/{id}`: Delete a customer
    - No body required

### Order Management
- `POST /api/v1/order`: Create a new order
  ```json
  {
    "date": "2023",
    "customerId": "67be4a522a1b36745e2ae939",
    "products": [
      {
        "productId": 5,
        "cantidad": 2
      },
      {
        "productId": 7,
        "cantidad": 2
      }
    ]
  }
  ```

## Order Service

The Order service is responsible for managing customer orders. When a new order is created, it:

1. Validates the customer exists
2. Verifies product availability
3. Creates the order record
4. Sends an order confirmation event to Kafka

### Order API Endpoints

- `POST /api/v1/order`: Create a new order
    - Requires customer ID and list of products
    - Returns order confirmation with details
    - Triggers async notification flow

## Notification Service

The Notification service handles all customer communications. It:

1. Listens for events on Kafka topics
2. Processes order confirmations
3. Sends email notifications to customers

### Email Notifications

The service sends the following types of emails:
- Order confirmation with order details


Emails are sent using configured SMTP settings and HTML templates.

## Service Integration

The Order and Notification services integrate asynchronously through Kafka:

1. Order Creation Flow:
   ```
   Order Service -> Kafka (order-topic) -> Notification Service -> Email to Customer
   ```

2. The order confirmation event includes:
    - Order reference number
    - Customer details
    - Product list
    - Total amount
    - Payment method

3. The Notification service:
    - Consumes events from Kafka topics
    - Processes the order confirmation
    - Generates personalized email content
    - Sends email to customer

## Gateway Service

The API Gateway runs on port 8111 and serves as the single entry point for all client requests. It provides:
- Request routing to appropriate microservices
- Load balancing
- Request/response transformation

## Service Port Configuration

- Product service: 8080
- Customer service: 8088
- Order service: 8889
- Gateway service: 8111
- Config service: 8888
- Discovery service: 8761

## Infrastructure Requirements

- Kafka broker running on port 9092
- Zookeeper for Kafka cluster management
- MailDev for local email testing (ports 1025, 1080)

For local development, these services are configured in the docker-compose file and can be started with:

```bash
docker-compose up -d
```

## Startup Sequence

To properly start the entire system, follow this sequence:

# Requirements
- Java 21
- docker
- gradle 8.2 or higher
1. Ensure Docker containers are running:
   ```bash
   docker-compose up -d
   ```

2. Verify Kafka service is running properly

3. Start the microservices in this order:
    - Config Service
    - Discovery Service
    - Product Service
    - Customer Service
    - Order Service
    - Notification Service
    - Gateway Service

This startup sequence ensures that dependent services are available when needed. The Config Service must start first as it provides configuration to all other services, followed by the Discovery Service for service registration.
