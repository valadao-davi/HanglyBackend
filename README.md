
# Hangly API

The Hangly API is a secure RESTful service built with Java Spring Boot, PostgreSQL, and Spring Security, offering comprehensive event management and user authentication. It includes user registration/login via JWT tokens, ensuring secure access. Each user has a personalized profile with exclusive access to their created events, featuring CRUD operations for both users and events with integrated address management.

## Features

- **User Authentication**: Complete registration and login system with JWT token-based security
- **Event Management**: Create, read, update, and delete events with detailed information including addresses
- **User Profiles**: Comprehensive user management with name, email, password, biography, birth date, rating system, and profile image URL
- **Address Integration**: Full address management system integrated with events for location tracking (street, number, district, city, state, country, CEP)
- **Event Categories**: Events can be organized by categories for better filtering and organization
- **User Rating System**: Users can rate each other (excluding self-rating)
- **PostgreSQL Database**: Scalable database architecture for storing user profiles, events, and address data
- **H2 Database Support**: In-memory database for testing and development
- **Docker Compatibility**: Containerized deployment for seamless integration and easy setup
- **Future Scalability**: Architecture ready for frontend integration and feature expansion
- **Efficient API Design**: Built with Java Spring Boot for high performance and reliable integration
- **MVC Architecture**: Implemented with Controller-Service-Repository pattern for maintainability and scalability
- **Spring Security**: Advanced security with password hashing and JWT token authentication

## Setup

### 1. Clone Repository

```bash
git clone https://github.com/valadao-davi/HanglyBackend
```

### 2. Navigate to the Project Directory

```bash
cd HanglyBackend
```

### 3. Database Setup

#### Option A: PostgreSQL (Production/Development)
Make sure you have PostgreSQL installed and running on port 5433 with:
- Database: `postgres`
- Username: `postgres`
- Password: `1234567`

#### Option B: H2 Database (Testing)
The application will automatically use H2 in-memory database for testing profile.

### 4. Build the Project
Make sure you have Maven and Java 17 installed.

```bash
./mvnw clean install
```

### 5. Run the Application

#### Development Profile (PostgreSQL):
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

#### Test Profile (H2):
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=test
```

### 6. Docker Setup (Optional)
If you'd like to run the project inside a Docker container, follow these steps:

#### 6.1 Build the Docker Image
```bash
docker build -t hangly-api:1.0 .
```

#### 6.2 Run the Docker Container
```bash
docker run -p 8080:8080 hangly-api
```

### 7. Verify the Application is Running

Open your browser and navigate to:
- **Development**: http://localhost:8080/profile
- **Test**: http://localhost:8080/h2-console (for H2 database console)

## API Documentation

### User Requests
Requests to the API should follow these standards:
| Method | Description |
|---|---|
| `GET` | Retrieve the logged-in user's profile information |
| `PUT` | Update the logged-in user's profile details |
| `PATCH` | Rate another user |
| `DELETE` | Delete the logged-in user's account |

### Authentication Requests
| Method | Description |
|---|---|
| `POST` | Register new users and authenticate existing users |

### Address Requests
| Method | Description |
|---|---|
| `POST` | Create a new address (standalone) |
| `PUT` | Update an existing address |

**Note**: When creating events, addresses are automatically created and associated with the event. There's no need to create an address separately before creating an event, as the system includes auto-association functionality.

## Retrieve User Profile [GET /profile]

### Retrieve user safe logged-in information:
- **Authentication:** Required
  - **Type:** Bearer Token
  - **Header:** `Authorization: Bearer <token>`

```http
GET http://localhost:8081/profile
```

+ Example response:
```json    
{
    "userId": 1,
    "name": "João Silva",
    "email": "joao@example.com",
    "biography": "Event organizer and community builder",
    "birthDate": "1990-05-15",
    "rate": 4.5,
    "imgUrl": "https://example.com/profile.jpg"
}
```

## Register User [POST /auth/register]

### Register a new user in the system:

```http
POST http://localhost:8081/auth/register
```

+ Body:
```json    
{
    "name": "João Silva",
    "email": "joao@example.com",
    "password": "securePassword123",
    "imgUrl": "https://example.com/profile.jpg"
}
```

+ Example response:
```json
{
    "message": "User registered"
}
```

## Login User [POST /auth/login]

### Authenticate user and retrieve access token:

```http
POST http://localhost:8081/auth/login
```

+ Body:
```json    
{
    "email": "joao@example.com",
    "password": "securePassword123"
}
```

+ Example response:
```json    
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

## Update User Profile [PUT /profile]

### Update logged-in user profile:
- **Authentication:** Required
  - **Type:** Bearer Token
  - **Header:** `Authorization: Bearer <token>`

#### Note: All fields are optional. You can update any of them or leave them unchanged.

```http
PUT http://localhost:8081/profile
```

+ Body:
```json    
{
    "name": "João Silva Updated",
    "email": "joao.updated@example.com",
    "password": "newPassword123",
    "biography": "Updated biography text",
    "birthDate": "1990-05-15",
    "imgUrl": "https://example.com/new-profile.jpg"
}
```

+ Example response:
```json
{
    "message": "User edited."
}
```

## Rate User [PATCH /profile/{id}]

### Rate another user (cannot rate yourself):
- **Authentication:** Required
  - **Type:** Bearer Token
  - **Header:** `Authorization: Bearer <token>`

```http
PATCH http://localhost:8081/profile/2
```

+ Body:
```json    
4.5
```

+ Example response (Success):
```json
{
    "message": "Sucess."
}
```

+ Example response (Self-rating attempt):
```json
{
    "message": "User can't rate himself."
}
```

## Delete User [DELETE /profile]

### Delete logged-in user account:
- **Authentication:** Required
  - **Type:** Bearer Token
  - **Header:** `Authorization: Bearer <token>`

```http
DELETE http://localhost:8081/profile
```

+ Example response:
```json
{
    "message": "User deleted"
}
```

## Event Requests (All endpoints require Bearer Token)
- **Authentication:** Required
  - **Type:** Bearer Token
  - **Header:** `Authorization: Bearer <token>`

Requests to the API should follow these standards:
| Method | Description |
|---|---|
| `GET` | Returns information about one or more events |
| `POST` | Used to create a new event with address |
| `PUT` | Updates existing event information |
| `DELETE` | Removes an event |

## Read Events [GET /event]

### Read all events with pagination:

```http
GET http://localhost:8081/event?page=0&items=10
```

+ Example response:
```json    
[
    {
        "eventId": 1,
        "name": "Tech Conference 2024",
        "description": "Annual technology conference",
        "date": "2024-08-15T10:00:00",
        "category": "Technology",
        "imgUrl": "https://example.com/event1.jpg"
    },
    {
        "eventId": 2,
        "name": "Community Meetup",
        "description": "Monthly community gathering",
        "date": "2024-08-20T18:00:00",
        "category": "Community",
        "imgUrl": "https://example.com/event2.jpg"
    }
]
```

### Read events by category:

```http
GET http://localhost:8081/event/category?category=Technology
```

### Read specific event by ID:

```http
GET http://localhost:8081/event/1
```

+ Example response:
```json    
{
    "eventId": 1,
    "name": "Tech Conference 2024",
    "description": "Annual technology conference with industry leaders",
    "date": "2024-08-15T10:00:00",
    "category": "Technology",
    "userId": 1,
    "imgUrl": "https://example.com/event1.jpg",
    "address": {
        "addressId": 1,
        "street": "Rua das Tecnologias",
        "number": 123,
        "district": "Centro",
        "city": "São Paulo",
        "state": "SP",
        "country": "Brazil",
        "cep": "01234-567"
    }
}
```

## Create Event [POST /event]

### Create a new event (address is created automatically):

```http
POST http://localhost:8081/event
```

+ Body:
```json    
{
    "name": "Summer Music Festival",
    "description": "Outdoor music festival featuring local artists",
    "date": "2024-07-15T14:00:00",
    "category": "Music",
    "street": "Avenida da Música",
    "number": 789,
    "district": "Vila Musical",
    "city": "Rio de Janeiro",
    "state": "RJ",
    "country": "Brazil",
    "cep": "22000-000",
    "imgUrl": "https://example.com/music-festival.jpg"
}
```

+ Example response:
```json
{
    "message": "Event created"
}
```

## Update Event [PUT /event/{id}]

### Update an existing event and its address:

#### Note: All fields are optional. You can update any of them or leave them unchanged.

```http
PUT http://localhost:8081/event/1
```

+ Body:
```json    
{
    "name": "Updated Summer Music Festival",
    "description": "Updated description with more details",
    "date": "2024-07-16T15:00:00",
    "category": "Music & Arts",
    "street": "Nova Avenida da Música",
    "number": 790,
    "district": "Vila Musical",
    "city": "Rio de Janeiro",
    "state": "RJ",
    "country": "Brazil",
    "cep": "22000-001",
    "imgUrl": "https://example.com/updated-music-festival.jpg"
}
```

+ Example response:
```json
{
    "message": "Event edited"
}
```

## Delete Event [DELETE /event/{id}]

### Remove an event by ID (only by creator):

```http
DELETE http://localhost:8081/event/1
```

+ Example response:
```json
{
    "message": "Event deleted"
}
```

## Address Requests

### Create Address [POST /address]

### Create a standalone address:

```http
POST http://localhost:8081/address
```

+ Body:
```json    
{
    "street": "Rua das Flores",
    "number": 456,
    "district": "Centro",
    "city": "São Paulo",
    "state": "SP",
    "country": "Brazil",
    "cep": "01000-000"
}
```

+ Example response:
```json
{
    "message": "Success"
}
```

### Update Address [PUT /address/{id}]

### Update an existing address:

```http
PUT http://localhost:8081/address/1
```

+ Body:
```json    
{
    "addressId": 1,
    "street": "Rua das Flores Updated",
    "number": 457,
    "district": "Centro",
    "city": "São Paulo",
    "state": "SP",
    "country": "Brazil",
    "cep": "01000-001"
}
```

+ Example response:
```json
{
    "message": "Success"
}
```

## Technology Stack

- **Java 17**
- **Spring Boot 3.4.4**
- **Spring Security** with JWT authentication
- **Spring Data JPA** for database operations
- **PostgreSQL** for production database
- **H2 Database** for testing
- **Maven** for dependency management
- **ModelMapper** for object mapping
- **Auth0 Java JWT** for token handling
- **Docker** support for containerization

## Project Structure

```
src/main/java/com/HanglyGroup/HanglyBackend/
├── controllers/
│   ├── AddressController.java
│   ├── AuthenticationController.java
│   ├── EventController.java
│   └── UserController.java
├── dto/
│   ├── AddressCreateDTO.java
│   ├── EventCreateDTO.java
│   ├── EventEditDTO.java
│   ├── LoginResponseDTO.java
│   ├── UserCreateDTO.java
│   ├── UserEditDTO.java
│   └── UserLoginDTO.java
├── entities/
│   ├── Address.java
│   ├── Event.java
│   └── User.java
├── exceptions/
│   ├── EventNotFoundException.java
│   ├── JWTErrorException.java
│   ├── RegisterException.java
│   ├── UserNotFoundException.java
│   └── UserRegisteredException.java
├── infra/
│   ├── SecurityConfiguration.java
│   ├── SecurityFilter.java
│   └── TokenService.java
├── projections/
│   ├── EventDetailsProjection.java
│   ├── EventMinProjection.java
│   └── UserMinProjection.java
├── repositories/
│   ├── AddressRepository.java
│   ├── EventRepository.java
│   └── UserRepository.java
├── services/
│   ├── AddressService.java
│   ├── AuthenticationService.java
│   ├── AuthorizationService.java
│   ├── EventService.java
│   └── UserService.java
└── HanglyApplication.java
```
