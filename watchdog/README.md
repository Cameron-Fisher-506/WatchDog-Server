# Watchdog - Security Incident Management System

A Spring Boot-based security incident management system built with **Clean Architecture** principles. The system manages security incidents, patrol vehicle dispatch, and client communication for a security company.

---

## 📋 Table of Contents

- [Tech Stack](#tech-stack)
- [Architecture Overview](#architecture-overview)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Features](#features)
- [API Endpoints](#api-endpoints)
- [Database](#database)
- [Authentication](#authentication)
- [Development](#development)
- [Clean Architecture Guidelines](#clean-architecture-guidelines)

---

## 🔧 Tech Stack

### Backend Framework
- **Spring Boot 4.0.4** - Application framework
- **Java 21** - Programming language
- **Kotlin 2.3.10** - Support for Kotlin code

### Data & Database
- **Spring Data JPA** - ORM framework
- **Hibernate** - JPA implementation
- **PostgreSQL** - Relational database
- **HikariCP** - Database connection pooling

### Security
- **Spring Security** - Authentication & authorization
- **JWT (JJWT 0.12.3)** - JSON Web Tokens for stateless authentication

### Messaging
- **Spring AMQP** - Message broker integration
- **RabbitMQ** - Asynchronous messaging

### Utilities
- **Lombok** - Reduce boilerplate code
- **Jackson** - JSON processing
- **Maven** - Build automation

### Testing
- **Spring Boot Test** - Unit & integration testing
- **Spring Boot Data JPA Test** - JPA testing utilities
- **Spring Boot AMQP Test** - Message queue testing

---

## 🏗️ Architecture Overview

The application follows **Clean Architecture** principles with clear separation of concerns:

```
┌─────────────────────────────────────────┐
│     Presentation Layer (Controllers)    │ HTTP Requests/Responses
├─────────────────────────────────────────┤
│    Domain Layer (Use Cases & Models)    │ Business Logic
├─────────────────────────────────────────┤
│    Data Layer (Repositories & DAOs)     │ Data Access
├─────────────────────────────────────────┤
│    Database Layer (Entities & DAOs)     │ Persistence
└─────────────────────────────────────────┘
```

### Layer Responsibilities

1. **Presentation Layer** (`presentation/`)
   - REST Controllers (@RestController, @RequestMapping)
   - Request/Response DTOs (Data Transfer Objects)
   - Presentation mappers (DTO ↔ Domain Model conversion)
   - API contract definitions

2. **Domain Layer** (`domain/`)
   - Business logic (Use Cases)
   - Domain models (core business entities)
   - Repository interfaces (contracts, not implementations)
   - Business rules & custom exceptions
   - Managers (SecurityManager, etc.)

3. **Data Layer** (`data/`)
   - Repository implementations (Repository Pattern)
   - Data mappers (Domain Model ↔ Entity conversion)
   - Local data sources (database access)
   - Remote data sources (external APIs)

4. **Database Layer** (`common/data/local/database/`)
   - JPA Entity models (persistence layer)
   - DAOs (Data Access Objects - Spring Data repositories)
   - Database initialization scripts

### Dependency Flow

```
Presentation (DTOs) → Domain (Use Cases) → Data (Repository) → Database (Entities)
```

**Key Rule**: Dependencies point inward. Outer layers depend on inner layers, never the reverse.

---

## 📁 Project Structure

```
watchdog/
├── src/
│   ├── main/
│   │   ├── java/za/co/watchdog/
│   │   │   ├── WatchdogApplication.java          # Entry point
│   │   │   │
│   │   │   ├── common/                            # Shared across features
│   │   │   │   ├── data/
│   │   │   │   │   ├── local/
│   │   │   │   │   │   ├── database/              # JPA entities & DAOs
│   │   │   │   │   │   │   ├── model/             # IncidentEntity, UserEntity, etc.
│   │   │   │   │   │   │   └── dao/               # Spring Data Repositories
│   │   │   │   │   │   ├── mapper/                # Local mappers
│   │   │   │   │   │   └── dataSource/
│   │   │   │   │   ├── remote/
│   │   │   │   │   └── repository/
│   │   │   │   ├── domain/
│   │   │   │   │   ├── exception/                 # Business exceptions
│   │   │   │   │   ├── manager/                   # SecurityManager, etc.
│   │   │   │   │   ├── model/                     # Shared domain models
│   │   │   │   │   ├── repository/                # Repository interfaces
│   │   │   │   │   └── usecase/                   # Common use cases
│   │   │   │   └── presentation/
│   │   │   │       ├── common/
│   │   │   │       ├── converters/
│   │   │   │       ├── manager/
│   │   │   │       └── model/
│   │   │   │
│   │   │   └── features/                          # Feature modules
│   │   │       ├── authManagement/
│   │   │       │   ├── data/                      # Layer separation
│   │   │       │   │   ├── repository/
│   │   │       │   │   └── local/
│   │   │       │   ├── domain/
│   │   │       │   │   ├── model/
│   │   │       │   │   ├── repository/
│   │   │       │   │   └── usecase/
│   │   │       │   └── presentation/
│   │   │       │
│   │   │       ├── clientManagement/
│   │   │       │   ├── data/
│   │   │       │   ├── domain/
│   │   │       │   └── presentation/
│   │   │       │
│   │   │       └── incidentManagement/            # Feature: Incident Management
│   │   │           ├── config/                    # ← NEW: Spring bean configuration
│   │   │           │   └── IncidentManagementUseCaseConfig.java
│   │   │           ├── data/
│   │   │           │   ├── repository/            # IncidentManagementRepositoryImpl
│   │   │           │   │   └── data source calls
│   │   │           │   ├── local/                 # Data source implementations
│   │   │           │   │   ├── dataSource/
│   │   │           │   │   └── mapper/            # Entity ↔ Domain mappers
│   │   │           │   └── remote/
│   │   │           ├── domain/
│   │   │           │   ├── model/                 # Incident, Client, PatrolVehicle, etc.
│   │   │           │   ├── repository/            # Repository interfaces
│   │   │           │   │   └── IncidentManagementRepository
│   │   │           │   └── usecase/               # Business logic (NO Spring annotations)
│   │   │           │       ├── CreateIncidentUseCase
│   │   │           │       ├── FetchClientByUserIdUseCase
│   │   │           │       ├── mapper/            # Use case output mappers
│   │   │           │       └── model/             # UseCase input/output models
│   │   │           │           ├── CreateIncidentInput
│   │   │           │           └── CreateIncidentOutput
│   │   │           └── presentation/
│   │   │               ├── controller/            # REST endpoints
│   │   │               │   └── IncidentManagementController
│   │   │               ├── mapper/                # DTO ↔ Domain mappers
│   │   │               │   └── IncidentPresentationMapper
│   │   │               └── model/                 # Request/Response DTOs
│   │   │                   ├── IncidentRequestDto
│   │   │                   ├── IncidentResponseDto
│   │   │                   └── dto/                # Sub-DTOs
│   │   │
│   │   └── resources/
│   │       ├── application.yaml                  # Configuration
│   │       ├── static/
│   │       └── templates/
│   │
│   └── test/                                     # Test suites
│       └── java/za/co/watchdog/
│
├── pom.xml                                       # Maven configuration
├── watchdog.iml                                  # IDE configuration
├── README.md                                     # This file
└── scripts/
    ├── generate_secret_key.sh                   # JWT secret generation
    └── jwt_secret_key.txt
```

---

## 🚀 Getting Started

### Prerequisites

- **Java 21+**
- **Maven 3.6+**
- **PostgreSQL 12+**
- **RabbitMQ** (optional, for messaging features)

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd watchdog
   ```

2. **Configure PostgreSQL**
   ```bash
   # Create database
   createdb watchdog
   ```

3. **Update `application.yaml`**
   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/watchdog
       username: your_username
       password: your_password
   ```

4. **Generate JWT Secret Key** (Recommended for production)
   ```bash
   bash scripts/generate_secret_key.sh
   ```
   Update the secret in `application.yaml`:
   ```yaml
   security:
     jwt:
       secret-key: <generated-key>
   ```

5. **Build the project**
   ```bash
   mvn clean install
   ```

6. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`

---

## ✨ Features

### Current Features

- **Authentication Management**
  - User registration & login
  - JWT token-based authentication
  - Security context management
  - Token refresh capability

- **Client Management**
  - Client profile management
  - Client-user associations
  - Security company management
  - Client zone assignment

- **Incident Management**
  - Create & track security incidents
  - Trigger source identification (Smartphone GPS / Call / Direct)
  - Intelligent patrol vehicle dispatch
  - Location-based patrol assignment
  - Closest patrol vehicle calculation (GPS-based)
  - Incident status tracking (TRIGGERED, ASSIGNED, IN_PROGRESS, RESOLVED)
  - Real-time location updates
  - Incident tracking and history

---

## 🔌 API Endpoints

### Authentication
```
POST /api/v1/auth/register        - Register new user
POST /api/v1/auth/login           - Authenticate user
POST /api/v1/auth/refresh         - Refresh JWT token
```

### Client Management
```
GET  /api/v1/client-management    - List all clients
POST /api/v1/client-management    - Create new client
GET  /api/v1/client-management/{id} - Get client details
PUT  /api/v1/client-management/{id} - Update client
```

### Incident Management
```
POST /api/v1/incident-management/incident           - Create incident
POST /api/v1/incident-management/update-location   - Update incident location
GET  /api/v1/incident-management/{id}              - Get incident details
GET  /api/v1/incident-management/status/{status}   - Get incidents by status
```

### Request/Response Examples

**Create Incident**
```
POST /api/v1/incident-management/incident

Request Body:
{
  "triggerSource": "SMARTPHONE_GPS",
  "addressId": 1
}

Response (200 OK):
{
  "success": true,
  "data": {
    "incidentId": 123,
    "incidentStatus": "TRIGGERED",
    "patrolDto": {
      "patrolId": 1,
      "name": "John",
      "surname": "Doe",
      "officerCode": "JD001",
      "contactNumber": "+27123456789"
    },
    "vehicleDto": {
      "vehicleId": 1,
      "plateNumber": "GP123XY",
      "model": "Toyota Hilux"
    },
    "createdAt": "2024-05-24T10:30:00Z"
  }
}
```

---

## 🗄️ Database

### Connection Configuration

```yaml
spring:
  datasource:
    url: jdbc:postgresql://127.0.0.1:5432/watchdog
    username: postgres
    password: your_password
    driver-class-name: org.postgresql.Driver
    
    hikari:
      maximum-pool-size: 10      # Max connections
      minimum-idle: 5             # Minimum idle connections
      idle-timeout: 300000        # 5 minutes
      connection-timeout: 20000   # 20 seconds
      max-lifetime: 1200000       # 20 minutes
```

### Database Schema Initialization

- **Mode**: `always` - Initializes on every startup
- **DDL Auto**: `update` - Automatically updates schema based on entities
- **Dialect**: PostgreSQL 

Hibernate automatically manages table creation and schema updates based on entity definitions.

### Key Entities

- **User** - System users with authentication credentials
- **Client** - Security clients/customers
- **Incident** - Security incidents with tracking
- **Patrol** - Security patrol officers
- **Vehicle** - Patrol vehicles with GPS tracking
- **Address** - Location/zone data
- **SecurityCompany** - Security company information
- **IncidentTracking** - Incident history and updates

---

## 🔐 Authentication

### JWT Token-Based Authentication

The application uses JWT (JSON Web Tokens) for stateless authentication.

**Token Configuration** (`application.yaml`)
```yaml
security:
  jwt:
    secret-key: RiR7Es0oRSt/wO47nfSyQPqdTfcIhq+Sg5Ad1RmrHmk=
    expiration-time: 3600000  # 1 hour in milliseconds
```

**Token Flow**
1. User logs in with credentials
2. Server generates JWT token
3. Client includes token in Authorization header: `Bearer <token>`
4. Server validates token on each request
5. Token refreshes before expiration

**Using JWT in Requests**
```
GET /api/v1/incident-management/123
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

---

## 🛠️ Development

### Clean Architecture Best Practices

#### 1. Layer Separation
Each layer has a specific responsibility. NEVER violate layer boundaries:

```java
// ✅ CORRECT: Controller uses Use Case
@PostMapping("/incident")
public ResponseEntity<IncidentResponseDto> createIncident(
    @RequestBody IncidentRequestDto dto) {
    CreateIncidentInput input = mapToInput(dto);
    CreateIncidentOutput output = createIncidentUseCase.execute(input);
    return ResponseEntity.ok(mapToResponse(output));
}

// ❌ WRONG: Controller queries database directly
@PostMapping("/incident")
public ResponseEntity<IncidentResponseDto> createIncident(
    @RequestBody IncidentRequestDto dto) {
    IncidentEntity entity = incidentDao.save(new IncidentEntity()); // BAD!
    return ResponseEntity.ok(mapToResponse(entity));
}
```

#### 2. Data Transfer Objects (DTOs)
Always use DTOs at layer boundaries. Never expose domain models directly:

```java
// Presentation Layer
@Data
@Builder
public class IncidentRequestDto {
    private TriggerSource triggerSource;
    private Long addressId;
}

// Domain Layer
@Data
@Builder
public class CreateIncidentInput {
    private TriggerSource triggerSource;
    private Long addressId;
}

// Domain Entity
@Data
@Builder
public class Incident {
    private Long incidentId;
    private Long clientId;
    private Long securityCompanyId;
    private Long patrolId;
    private Long addressId;
    private IncidentStatus incidentStatus;
    private Instant createdAt;
}

// Database Layer
@Entity
@Table(name = "incident")
public class IncidentEntity {
    // ... JPA annotations
}
```

#### 3. Use Case Pattern & Framework Isolation
Business logic lives in Use Cases, NOT in Controllers. **Critically**, Use Cases should be **framework-agnostic** (no Spring annotations) to maintain Clean Architecture principles:

```java
// ✅ CORRECT: Use Case has NO Spring annotations (Pure Domain Logic)
public class CreateIncidentUseCase implements UseCase<CreateIncidentInput, CreateIncidentOutput> {
    private final IncidentManagementRepository incidentManagementRepository;
    private final UserManagementRepository userManagementRepository;
    private final SecurityManager securityManager;
    private final CreateIncidentMapper createIncidentMapper;

    // Constructor injection - no Spring needed
    public CreateIncidentUseCase(
            IncidentManagementRepository incidentManagementRepository,
            UserManagementRepository userManagementRepository,
            CreateIncidentMapper createIncidentMapper,
            SecurityManager securityManager
    ) {
        this.incidentManagementRepository = incidentManagementRepository;
        this.userManagementRepository = userManagementRepository;
        this.createIncidentMapper = createIncidentMapper;
        this.securityManager = securityManager;
    }

    @Override
    public CreateIncidentOutput execute(CreateIncidentInput input) {
        // All business logic here
        User currentUser = getCurrentUser();
        Client client = fetchClient(currentUser.getId());
        
        if (input.getTriggerSource() == TriggerSource.SMARTPHONE_GPS) {
            // GPS dispatch logic
        } else {
            // Zone-based dispatch logic
        }
        
        Incident incident = buildIncident(client, input);
        Incident saved = repository.saveIncident(incident).orElseThrow(...);
        return mapper.map(saved.getId(), patrolVehicle);
    }
}

// ❌ WRONG: Use Case with @Service annotation (Framework Coupling)
@Service  // ← Couples domain logic to Spring framework - AVOID THIS
public class CreateIncidentUseCase implements UseCase<CreateIncidentInput, CreateIncidentOutput> {
    // ...
}

// ❌ WRONG: Business logic in Controller
@PostMapping("/incident")
public ResponseEntity<IncidentResponseDto> createIncident(
    @RequestBody IncidentRequestDto dto) {
    if (dto.getTriggerSource() == TriggerSource.SMARTPHONE_GPS) {
        // Business logic here - BAD!
    }
    return ResponseEntity.ok(...);
}
```

**Why NO @Service on UseCase?**
- ✅ Domain layer remains framework-agnostic
- ✅ Use Cases are testable without Spring context
- ✅ Easy to migrate if switching frameworks
- ✅ Respects Clean Architecture dependency rules

#### 3b. Configuration Layer (Framework Wiring)
The **Config Layer** bridges the Spring Framework with domain logic. It lives at the feature level and handles Spring bean wiring:

**Location:** `features/incidentManagement/config/`

```java
package za.co.watchdog.features.incidentManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import za.co.watchdog.common.domain.manager.SecurityManager;
import za.co.watchdog.common.domain.repository.UserManagementRepository;
import za.co.watchdog.features.incidentManagement.domain.repository.IncidentManagementRepository;
import za.co.watchdog.features.incidentManagement.domain.usecase.createIncident.CreateIncidentUseCase;
import za.co.watchdog.features.incidentManagement.domain.usecase.createIncident.mapper.CreateIncidentMapper;

/**
 * Configuration class for Incident Management Use Cases.
 * 
 * Responsibility: Spring bean configuration and dependency injection
 * Bridges Spring framework with domain layer (keeps domain framework-agnostic)
 * 
 * Layer: config/ (feature-level infrastructure)
 * Pattern: Spring configuration only - no business logic here
 */
@Configuration
public class IncidentManagementUseCaseConfig {
    
    /**
     * Bean for CreateIncidentUseCase
     * Handles incident creation with intelligent patrol vehicle dispatch
     */
    @Bean
    public CreateIncidentUseCase createIncidentUseCase(
            IncidentManagementRepository incidentManagementRepository,
            UserManagementRepository userManagementRepository,
            CreateIncidentMapper createIncidentMapper,
            SecurityManager securityManager
    ) {
        return new CreateIncidentUseCase(
            incidentManagementRepository,
            userManagementRepository,
            createIncidentMapper,
            securityManager
        );
    }
    
    // Wire additional use cases here as needed
}
```

**Layer Architecture with Config:**

```
Presentation (Controllers) → Config (@Configuration) → Domain (UseCases - no Spring)
                             Spring beans only           Pure business logic
```

**Benefits of Config Layer Pattern:**
| Aspect | @Service on UseCase | Config Layer |
|--------|-------------------|--------------|
| Framework Coupling | ❌ Domain depends on Spring | ✅ Only config imports Spring |
| Testability | ❌ Needs Spring context | ✅ Plain POJO instantiation |
| Clean Architecture | ❌ Violates dependency rule | ✅ Respects dependency rule |
| Layer Isolation | ❌ Spring code in domain | ✅ Framework in separate layer |
| Framework Migration | ❌ Would break domain code | ✅ Only config needs changes |
| Reusability | ❌ Tightly coupled to Spring | ✅ Use cases reusable anywhere |

**Example Unit Test (Without Spring Context):**
```java
@Test
public void testCreateIncidentUseCase() {
    // ✅ Can instantiate without Spring - Pure domain testing
    CreateIncidentUseCase useCase = new CreateIncidentUseCase(
        mockRepository,
        mockUserRepository,
        mockMapper,
        mockSecurityManager
    );
    
    CreateIncidentOutput output = useCase.execute(mockInput);
    
    assertNotNull(output);
    assertEquals(expectedIncidentId, output.getIncidentId());
}
```

---

## Testing

### Unit Testing

Run tests with Maven:
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=CreateIncidentUseCaseTest

# Run with coverage
mvn test jacoco:report
```

### Test Structure

```
src/test/java/za/co/watchdog/
├── features/
│   └── incidentManagement/
│       ├── domain/
│       │   └── usecase/
│       │       └── CreateIncidentUseCaseTest.java
│       ├── data/
│       │   └── repository/
│       │       └── IncidentManagementRepositoryImplTest.java
│       └── presentation/
│           └── controller/
│               └── IncidentManagementControllerTest.java
└── common/
    └── domain/
        └── usecase/
            └── FetchCurrentUserByUsernameUseCaseTest.java
```

---

## 📝 Configuration

### Application Profiles

- **dev** - Development environment (default)
  - SQL debugging enabled
  - Hibernate SQL logging: `show_sql: true`
  - Auto schema update: `ddl-auto: update`
  - Live reload with DevTools

### Environment Variables (Production)

Set these in your environment or `.env` file:

```bash
# Database Configuration
DATABASE_URL=jdbc:postgresql://host:5432/watchdog
DATABASE_USERNAME=postgres
DATABASE_PASSWORD=secure_password
DATABASE_MAX_POOL_SIZE=10

# Security
JWT_SECRET_KEY=<generate-strong-key-with-script>
JWT_EXPIRATION_TIME=3600000

# Spring
SPRING_PROFILES_ACTIVE=prod

# RabbitMQ (if using messaging)
RABBITMQ_HOST=localhost
RABBITMQ_PORT=5672
RABBITMQ_USERNAME=guest
RABBITMQ_PASSWORD=guest
```

---

## 🐛 Troubleshooting

### Common Issues

1. **Database Connection Failed**
   ```
   Error: jdbc.SQLRecoverableException: IO Error: ...
   ```
   - Verify PostgreSQL is running: `sudo service postgresql status`
   - Check credentials in `application.yaml`
   - Ensure database exists: `createdb watchdog`
   - Verify PostgreSQL is listening on the right port

2. **JWT Token Invalid or Expired**
   ```
   Error: JWT token is expired or invalid
   ```
   - Verify secret key is consistent between generation and configuration
   - Check token expiration time setting
   - Ensure Authorization header format: `Bearer <token>` (note the space)
   - Regenerate token if secret was changed

3. **Port Already in Use**
   ```
   Error: Address already in use: bind
   ```
   - Run on different port: 
   ```bash
   mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=9090"
   ```

4. **Hibernate DDL-Auto Issues**
   ```
   Error: Could not execute statement
   ```
   - Set `ddl-auto: validate` to check without modifying
   - Use `ddl-auto: update` for development only
   - Use `ddl-auto: none` in production with manual migrations

5. **Build Failures with Lombok**
   ```
   Error: cannot find symbol: symbol: method builder()
   ```
   - Ensure IDE has Lombok plugin installed
   - In IntelliJ: Enable annotation processing in Settings → Build → Compiler → Annotation Processors

---

## 📚 Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA Guide](https://spring.io/guides/gs/accessing-data-jpa/)
- [Clean Architecture by Robert C. Martin](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [JWT Best Practices](https://tools.ietf.org/html/rfc8725)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
- [Maven Documentation](https://maven.apache.org/guides/index.html)
- [Spring Security Documentation](https://spring.io/projects/spring-security)

---

## 📄 License

This project is proprietary software for Watchdog Security.

---

## 👥 Contributors

- Cameron Fisher - Lead Developer

---

## 📞 Support

For issues, questions, or feature requests, please contact the development team or create an issue in the repository.

---

**Last Updated**: May 24, 2026  
**Version**: 0.0.1-SNAPSHOT

