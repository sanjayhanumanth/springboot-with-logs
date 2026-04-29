# Spring Boot JWT Demo

A production-style Spring Boot project featuring:

- ✅ **Spring Boot 3.2** + **Java 17**
- ✅ **JWT Authentication** (JJWT 0.12.5)
- ✅ **Spring Security** with role-based access (`ROLE_USER`, `ROLE_ADMIN`)
- ✅ **Swagger / OpenAPI 3** (SpringDoc)
- ✅ **H2 In-Memory Database** with JPA
- ✅ **Separate Log Files** (app.log, error.log, security.log) with rolling policy
- ✅ **Global Exception Handler**
- ✅ **Validation** with jakarta.validation

---

## 🚀 Run the Project

```bash
./mvnw spring-boot:run
```

Or build and run the JAR:
```bash
./mvnw clean package
java -jar target/springboot-jwt-demo-1.0.0.jar
```

---

## 🔗 URLs

| URL | Description |
|-----|-------------|
| `http://localhost:8080/swagger-ui.html` | Swagger UI |
| `http://localhost:8080/api-docs` | OpenAPI JSON |
| `http://localhost:8080/h2-console` | H2 Database Console |

### H2 Console Settings
- JDBC URL: `jdbc:h2:mem:demodb`
- Username: `sa`
- Password: `password`

---

## 🔑 Default Users (seeded on startup)

| Username | Password | Role       |
|----------|----------|------------|
| `admin`  | `admin123` | ROLE_ADMIN |
| `user`   | `user123`  | ROLE_USER  |

---

## 📡 API Endpoints

### Auth (Public)
| Method | URL | Description |
|--------|-----|-------------|
| POST | `/api/auth/register` | Register new user |
| POST | `/api/auth/login` | Login & get JWT token |

### Protected (Requires Bearer Token)
| Method | URL | Role Required |
|--------|-----|--------------|
| GET | `/api/me` | Any authenticated user |
| GET | `/api/admin/users` | ROLE_ADMIN only |

---

## 📖 How to Use Swagger with JWT

1. Open `http://localhost:8080/swagger-ui.html`
2. Call **POST /api/auth/login** with `admin` / `admin123`
3. Copy the returned `token` value
4. Click the **Authorize 🔒** button at the top
5. Paste the token and click **Authorize**
6. Now all protected endpoints will include the Bearer token

---

## 📂 Log Files

| File | Contents |
|------|----------|
| `logs/app.log` | All application logs (rolling, max 10MB) |
| `logs/error.log` | ERROR-level only |
| `logs/security.log` | Auth / Spring Security logs |
| `logs/archived/` | Gzipped rotated log archives |

---

## 📁 Project Structure

```
src/main/java/com/demo/
├── DemoApplication.java
├── config/
│   ├── DataInitializer.java
│   ├── GlobalExceptionHandler.java
│   ├── SecurityConfig.java
│   └── SwaggerConfig.java
├── controller/
│   ├── AuthController.java
│   └── UserController.java
├── dto/
│   └── AuthDto.java
├── entity/
│   └── User.java
├── repository/
│   └── UserRepository.java
├── security/
│   ├── JwtAuthFilter.java
│   └── JwtUtil.java
└── service/
    ├── AuthService.java
    └── UserDetailsServiceImpl.java

src/main/resources/
├── application.yml
└── logback-spring.xml
```
