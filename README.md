# Reactive E-commerce Product Management Service

This Spring Boot-based microservice manages products, categories, and product variants for an e-commerce platform using reactive programming principles and a PostgreSQL database.

The Product Management Service is a crucial component of an e-commerce microservices architecture, designed to handle the creation and management of products, categories, and product variants. It leverages Spring WebFlux for reactive programming, R2dbc for database interactions, and follows clean architecture principles to ensure maintainability and scalability.

## Repository Structure

The repository structure follows a clean architecture approach:

- `applications`: Contains the main application configuration and entry point.
- `domain`: Holds the core business logic, including models and use cases.
- `infrastructure`: Implements the technical details, such as database adapters and API endpoints.
- `deployment`: Contains deployment-related files, such as Dockerfiles.

## Project Architecture

This project follows Clean Architecture principles, which promotes separation of concerns and independence of frameworks. The architecture is divided into several layers:

1. **Domain Layer** (innermost layer):
   - Located in the `domain` directory
   - Contains the core business logic and entities
   - Includes `model` (business entities) and `usecase` (business rules)
   - Independent of any external frameworks or technologies

2. **Application Layer**:
   - Located in the `applications/app-service` directory
   - Orchestrates the flow of data between the outer layers and the domain layer
   - Contains configuration classes and the main application entry point

3. **Infrastructure Layer** (outermost layer):
   - Located in the `infrastructure` directory
   - Implements technical details and adapters for external services
   - Divided into:
     - `driven-adapters`: Implements outgoing adapters (e.g., database repositories)
     - `entry-points`: Implements incoming adapters (e.g., REST API endpoints)

Key components and their responsibilities:

- **Use Cases**: Implement specific business rules and orchestrate the flow of data (e.g., `GuardarProductoUseCase`)
- **Repositories**: Define interfaces for data access in the domain layer (e.g., `ProductoRepository`)
- **Entities**: Represent the core business objects (e.g., `Producto`, `Categoria`)
- **DTOs**: Used for data transfer between layers (e.g., `ProductRequest`, `CategoriaDTO`)
- **Mappers**: Convert between DTOs and domain entities (e.g., `ProductoMapper`)
- **Handlers**: Handle incoming HTTP requests (e.g., `Handler`)
- **Routers**: Define API routes (e.g., `RouterRest`)

The project uses Spring WebFlux for reactive programming, allowing for non-blocking, asynchronous request handling. This is particularly evident in the use of `Mono` types in repository methods and handlers.

Dependency flow:

- The domain layer has no dependencies on outer layers
- The application layer depends on the domain layer
- The infrastructure layer depends on both the domain and application layers

This architecture ensures that the core business logic remains isolated from external concerns, making the system more maintainable, testable, and adaptable to changes in technology or requirements.

## Usage Instructions

### Prerequisites

- Java 17
- Docker and Docker Compose
- Gradle 7.x or later

### Installation

1. Clone the repository:
   ```
   git clone <repository-url>
   cd services/producto
   ```

2. Build the project:
   ```
   ./gradlew build
   ```

3. Start the PostgreSQL database using Docker Compose:
   ```
   docker-compose up -d
   ```

4. Run the application:
   ```
   java -jar applications/app-service/build/libs/productos.jar
   ```

### API Endpoints

- `POST /api/v1/categoria`: Create a new category
- `GET /api/v1/categoria`: Get all categories
- `POST /api/v1/producto`: Create a new product
- `POST /api/vi/product-variant`: Create a new product variant

### Configuration

The main configuration file is located at `applications/app-service/src/main/resources/application.yaml`. Key configurations include:

- Server port: 8080
- Database connection details
- CORS allowed origins

### Testing

Run the tests using:

```
./gradlew test
```

### Troubleshooting

- If you encounter database connection issues, ensure the PostgreSQL container is running and the connection details in `application.yaml` are correct.
- For CORS-related problems, check the `cors.allowed-origins` configuration in `application.yaml`.

## Data Flow

1. The client sends a request to one of the API endpoints.
2. The `RouterRest` class routes the request to the appropriate handler method in the `Handler` class.
3. The handler method processes the request, using the relevant use case (e.g., `GuardarProductoUseCase`).
4. The use case interacts with the domain model and repository interfaces.
5. The JPA repository adapter implements the repository interface and interacts with the PostgreSQL database.
6. The response flows back through the layers and is returned to the client.

```
Client -> RouterRest -> Handler -> UseCase -> Repository Interface -> JPA Adapter -> Database
```

## Deployment

To deploy the application:

1. Build the Docker image:
   ```
   docker build -t producto-service -f deployment/Dockerfile .
   ```

2. Run the container:
   ```
   docker run -p 8080:8080 producto-service
   ```

## Infrastructure

The application uses the following key infrastructure components:

- PostgreSQL Database:
  - Container name: `ecommerce_pg_sql`
  - Exposed port: 5432
  - Credentials: root/root

- Spring Boot Application:
  - Base image: eclipse-temurin:17-jdk-alpine
  - JVM options: -Xshareclasses:name=cacheapp,cacheDir=/cache,nonfatal -XX:+UseContainerSupport -XX:MaxRAMPercentage=70