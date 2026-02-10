# Chitkara University Qualifier 1 - Java REST API

Production-ready REST API implementation using Spring Boot 3.2 and Java 17 for Chitkara University 2026 Qualifier Assessment.

## Overview

This API provides mathematical computation and AI query capabilities through two endpoints:
- POST /bfhl - Main functional endpoint supporting fibonacci, prime, lcm, hcf, and AI operations
- GET /health - Health check endpoint

## Features

Strict API response structure compliance
Correct HTTP status codes
Robust input validation
Graceful error handling (no crashes)
Security guardrails
Public accessibility (CORS enabled)
Comprehensive JUnit tests (25+ test cases)
Production-ready deployment configuration

## Tech Stack

Language: Java 17  
Framework: Spring Boot 3.2  
Build Tool: Maven  
Testing: JUnit 5  
AI Integration: Google Gemini API  

## Local Setup

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- Google Gemini API key

### Installation Steps

1. Extract the project:
cd chitkara-qualifier-java-api

2. Install dependencies:
mvn clean install

3. Configure environment variables:

export OFFICIAL_EMAIL=gaurav1201.be23@chitkarauniversity.edu.in
export GEMINI_API_KEY=AIzaSyC-UseQUssda9jau73W_5vlqwLp9hXKbqI
export PORT=8080

Or copy template:
cp .env.example .env

4. Get Google Gemini API Key:
Visit https://aistudio.google.com
Sign in with Google account
Click "Get API Key"
Create API key and copy it

5. Run the application:

mvn spring-boot:run

OR

mvn clean package
java -jar target/qualifier-api-1.0.0.jar

6. Run tests:
mvn test

Server runs at:
http://localhost:8080

## API Documentation

### POST /bfhl

Only ONE operation key allowed per request.

#### Fibonacci
Request:
{
  "fibonacci": 7
}

Response:
{
  "is_success": true,
  "official_email": "gaurav1201.be23@chitkarauniversity.edu.in",
  "data": [0, 1, 1, 2, 3, 5, 8]
}

#### Prime Filter
Request:
{
  "prime": [2, 4, 7, 9, 11]
}

Response:
{
  "is_success": true,
  "official_email": "gaurav1201.be23@chitkarauniversity.edu.in",
  "data": [2, 7, 11]
}

#### LCM
Request:
{
  "lcm": [12, 18, 24]
}

Response:
{
  "is_success": true,
  "official_email": "gaurav1201.be23@chitkarauniversity.edu.in",
  "data": 72
}

#### HCF
Request:
{
  "hcf": [24, 36, 60]
}

Response:
{
  "is_success": true,
  "official_email": "gaurav1201.be23@chitkarauniversity.edu.in",
  "data": 12
}

#### AI Query
Request:
{
  "AI": "What is the capital city of Maharashtra?"
}

Response:
{
  "is_success": true,
  "official_email": "gaurav1201.be23@chitkarauniversity.edu.in",
  "data": "Mumbai"
}

### GET /health

Response:
{
  "is_success": true,
  "official_email": "gaurav1201.be23@chitkarauniversity.edu.in"
}

## Error Responses

No operation key:
Status 400

{
  "is_success": false,
  "official_email": "gaurav1201.be23@chitkarauniversity.edu.in",
  "error": "No valid operation key provided. Expected one of: fibonacci, prime, lcm, hcf, AI"
}

Multiple keys:
Status 400

{
  "is_success": false,
  "official_email": "gaurav1201.be23@chitkarauniversity.edu.in",
  "error": "Multiple operation keys provided. Only one key is allowed per request"
}

## Deployment

### Railway
Environment Variables:
OFFICIAL_EMAIL=gaurav1201.be23@chitkarauniversity.edu.in
GEMINI_API_KEY=AIzaSyC-UseQUssda9jau73W_5vlqwLp9hXKbqI

### Render
Build Command:
mvn clean package

Start Command:
java -jar target/qualifier-api-1.0.0.jar

### Docker

docker build -t chitkara-qualifier-api .

docker run -p 8080:8080 \
-e OFFICIAL_EMAIL=gaurav1201.be23@chitkarauniversity.edu.in \
-e GEMINI_API_KEY=AIzaSyC-UseQUssda9jau73W_5vlqwLp9hXKbqI \
chitkara-qualifier-api

## Project Structure

chitkara-qualifier-java-api/
├── src/main/java/com/chitkara/qualifier/
│   ├── QualifierApplication.java
│   ├── ApiController.java
│   ├── MathOperationsService.java
│   └── AIService.java
├── src/main/resources/application.properties
├── src/test/java/com/chitkara/qualifier/QualifierApplicationTests.java
├── pom.xml
├── .env.example
├── Dockerfile
└── README.md

## License

MIT License
gi