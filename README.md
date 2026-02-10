# Chitkara University Qualifier 1 - Java REST API

Production-ready REST API implementation using **Spring Boot 3.2** and **Java 17** for Chitkara University 2026 Qualifier Assessment.

##  Overview

This API provides mathematical computation and AI query capabilities through two endpoints:
- `POST /bfhl` - Main functional endpoint supporting fibonacci, prime, lcm, hcf, and AI operations
- `GET /health` - Health check endpoint

##  Features

Strict API response structure compliance
Correct HTTP status codes
Robust input validation
Graceful error handling (no crashes)
Security guardrails
Public accessibility (CORS enabled)
Comprehensive JUnit tests (25+ test cases)
Production-ready deployment configuration

##  Tech Stack

- **Language:** Java 17
- **Framework:** Spring Boot 3.2
- **Build Tool:** Maven
- **Testing:** JUnit 5
- **AI Integration:** Google Gemini API

##  Local Setup

### Prerequisites
- Java 17 or higher ([Download JDK](https://adoptium.net/))
- Maven 3.6+ ([Download Maven](https://maven.apache.org/download.cgi))
- Google Gemini API key

### Installation Steps

1. **Extract the project:**
```bash
cd chitkara-qualifier-java-api
```

2. **Install dependencies:**
```bash
mvn clean install
```

3. **Configure environment variables:**

Create a `.env` file or set environment variables:
```bash
export OFFICIAL_EMAIL=your.email@chitkara.edu.in
export GEMINI_API_KEY=your_gemini_api_key_here
export PORT=8080
```

Or copy the template:
```bash
cp .env.example .env
# Edit .env and add your values
```

4. **Get Google Gemini API Key:**
   - Visit https://aistudio.google.com
   - Sign in with Google account
   - Click "Get API Key"
   - Create API key in project
   - Copy the key

5. **Run the application:**

Using Maven:
```bash
mvn spring-boot:run
```

Or run the JAR:
```bash
mvn clean package
java -jar target/qualifier-api-1.0.0.jar
```

6. **Run tests:**
```bash
mvn test
```

The server will start at `http://localhost:8080`

## 📡 API Documentation

### POST /bfhl

Main endpoint supporting five operations. Each request must contain **exactly one** operation key.

#### Operations

**1. Fibonacci Sequence**
```json
// Request
POST http://localhost:8080/bfhl
Content-Type: application/json

{
  "fibonacci": 7
}

// Response
{
  "is_success": true,
  "official_email": "your.email@chitkara.edu.in",
  "data": [0, 1, 1, 2, 3, 5, 8]
}
```

**2. Prime Number Filter**
```json
// Request
{
  "prime": [2, 4, 7, 9, 11]
}

// Response
{
  "is_success": true,
  "official_email": "your.email@chitkara.edu.in",
  "data": [2, 7, 11]
}
```

**3. Least Common Multiple (LCM)**
```json
// Request
{
  "lcm": [12, 18, 24]
}

// Response
{
  "is_success": true,
  "official_email": "your.email@chitkara.edu.in",
  "data": 72
}
```

**4. Highest Common Factor (HCF/GCD)**
```json
// Request
{
  "hcf": [24, 36, 60]
}

// Response
{
  "is_success": true,
  "official_email": "your.email@chitkara.edu.in",
  "data": 12
}
```

**5. AI Query**
```json
// Request
{
  "AI": "What is the capital city of Maharashtra?"
}

// Response
{
  "is_success": true,
  "official_email": "your.email@chitkara.edu.in",
  "data": "Mumbai"
}
```

### GET /health

Health check endpoint to verify API availability.

```json
GET http://localhost:8080/health

// Response
{
  "is_success": true,
  "official_email": "your.email@chitkara.edu.in"
}
```

### Error Responses

**No operation key:**
```json
{
  "is_success": false,
  "official_email": "your.email@chitkara.edu.in",
  "error": "No valid operation key provided. Expected one of: fibonacci, prime, lcm, hcf, AI"
}
```
Status Code: `400`

**Multiple operation keys:**
```json
{
  "is_success": false,
  "official_email": "your.email@chitkara.edu.in",
  "error": "Multiple operation keys provided. Only one key is allowed per request"
}
```
Status Code: `400`

##  Deployment

### Option 1: Railway (Recommended for Java)

1. **Push to GitHub**
2. **Visit https://railway.app**
3. **Create New Project** → **Deploy from GitHub**
4. **Select repository**
5. **Add environment variables:**
   - `OFFICIAL_EMAIL`
   - `GEMINI_API_KEY`
6. **Deploy** (Railway auto-detects Maven/Spring Boot)

### Option 2: Render

1. **Push to GitHub**
2. **Visit https://render.com**
3. **New Web Service** → **Connect repository**
4. **Configure:**
   - **Build Command:** `mvn clean package`
   - **Start Command:** `java -jar target/qualifier-api-1.0.0.jar`
5. **Add environment variables**
6. **Deploy**

### Option 3: Heroku

```bash
# Install Heroku CLI and login
heroku login

# Create app
heroku create your-app-name

# Set buildpack
heroku buildpacks:set heroku/java

# Set environment variables
heroku config:set OFFICIAL_EMAIL=your.email@chitkara.edu.in
heroku config:set GEMINI_API_KEY=your_key_here

# Deploy
git push heroku main

# Open app
heroku open
```

### Option 4: Docker

Build and run using Docker:

```bash
# Build image
docker build -t chitkara-qualifier-api .

# Run container
docker run -p 8080:8080 \
  -e OFFICIAL_EMAIL=your.email@chitkara.edu.in \
  -e GEMINI_API_KEY=your_key_here \
  chitkara-qualifier-api
```

##  Testing

The project includes comprehensive JUnit tests:

### Run all tests:
```bash
mvn test
```

### Run specific test class:
```bash
mvn test -Dtest=QualifierApplicationTests
```

### Test Coverage:
-  Health endpoint (3 tests)
-  Fibonacci operations (4 tests)
-  Prime filtering (3 tests)
-  LCM calculations (2 tests)
-  HCF calculations (2 tests)
-  Error handling (4 tests)
-  Response structure (4 tests)



## Project Structure

```
chitkara-qualifier-java-api/
│
├── src/
│   ├── main/
│   │   ├── java/com/chitkara/qualifier/
│   │   │   ├── QualifierApplication.java      # Main Spring Boot app
│   │   │   ├── ApiController.java              # REST endpoints
│   │   │   ├── MathOperationsService.java      # Math operations
│   │   │   └── AIService.java                  # Google Gemini integration
│   │   └── resources/
│   │       └── application.properties          # Configuration
│   │
│   └── test/
│       └── java/com/chitkara/qualifier/
│           └── QualifierApplicationTests.java  # JUnit tests
│
├── pom.xml                 # Maven configuration
├── .env.example            # Environment template
├── .gitignore             # Git ignore rules
├── Dockerfile             # Docker configuration
└── README.md              # This file
```

##  Maven Commands

```bash
# Clean and build
mvn clean install

# Run application
mvn spring-boot:run

# Run tests
mvn test

# Package as JAR
mvn clean package

# Skip tests during build
mvn clean package -DskipTests

# Run with specific profile
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

##  Security Features

- Environment variable protection
- Input validation and type checking
- CORS configuration for public access
- No sensitive data in error messages
- Spring Boot security best practices

##  Key Implementation Details

### Input Validation
- Validates exactly one operation key per request
- Type checking for all inputs (Integer, List, String)
- Range validation for numeric inputs
- Array validation for list operations

### Error Handling
- Appropriate HTTP status codes (200, 400, 500, 503)
- Consistent error response structure
- Graceful degradation
- No server crashes on invalid input

### AI Integration
- Google Gemini API integration using RestTemplate
- Configurable temperature and token limits
- Error handling for API failures
- Returns 503 when AI service unavailable

##  Submission Checklist

- Public GitHub repository with full source code
-  All required endpoints implemented
-  Comprehensive test coverage (25+ tests)
-  Environment variable configuration
- Deployment-ready configuration
-  Complete documentation
-  Security best practices
- Maven build successful

##  Quick Deployment Commands

```bash
# Build the project
mvn clean package

# Run locally
java -jar target/qualifier-api-1.0.0.jar

# Test the API
curl http://localhost:8080/health

curl -X POST http://localhost:8080/bfhl \
  -H "Content-Type: application/json" \
  -d '{"fibonacci": 7}'
```

##  Support

For issues or questions:
- Check the API documentation above
- Review test cases in `QualifierApplicationTests.java`
- Verify environment variables are set correctly
- Check application logs for detailed error messages
##  License

MIT License - feel free to use this code for your learning and projects.

---


# ChitkaraBajaj
