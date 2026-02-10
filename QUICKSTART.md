# 🚀 Quick Start & Deployment Guide (Java)

## ⚡ Quick Start (5 Minutes)

### Step 1: Prerequisites Check
```bash
# Check Java version (should be 17+)
java -version

# Check Maven version
mvn -version

# If not installed, download from:
# Java: https://adoptium.net/
# Maven: https://maven.apache.org/download.cgi
```

### Step 2: Get Your API Key
1. Visit https://aistudio.google.com
2. Sign in with Google
3. Click "Get API Key" → "Create API key in new project"
4. Copy the API key (starts with `AIza...`)

### Step 3: Setup Environment
```bash
# Navigate to project
cd chitkara-qualifier-java-api

# Create .env file
cp .env.example .env

# Edit .env and add:
# OFFICIAL_EMAIL=your.email@chitkara.edu.in
# GEMINI_API_KEY=AIza...your-key-here
```

### Step 4: Build and Run
```bash
# Install dependencies and build
mvn clean install

# Run the application
mvn spring-boot:run

# Or build JAR and run
mvn clean package
java -jar target/qualifier-api-1.0.0.jar
```

### Step 5: Test Locally
```bash
# In another terminal, test health endpoint
curl http://localhost:8080/health

# Test fibonacci
curl -X POST http://localhost:8080/bfhl \
  -H "Content-Type: application/json" \
  -d '{"fibonacci": 7}'

# Run all tests
mvn test
```

---

## ✅ Pre-Submission Checklist

### Code Quality
- [ ] All files properly formatted
- [ ] No hardcoded credentials
- [ ] .gitignore includes .env
- [ ] All dependencies in pom.xml

### Testing
- [ ] All tests pass (`mvn test`)
- [ ] Tested each operation manually
- [ ] Verified error handling
- [ ] Response structure matches requirements

### Configuration
- [ ] .env.example file exists
- [ ] README.md is complete
- [ ] Dockerfile present
- [ ] Official email configured

### GitHub
- [ ] Repository is PUBLIC
- [ ] All source files committed
- [ ] README.md visible
- [ ] .env NOT committed

### Deployment
- [ ] App deployed and accessible
- [ ] Environment variables set
- [ ] GET /health returns 200
- [ ] POST /bfhl works with all operations

---

## 🌐 Deployment Options

### Option 1: Railway (Easiest for Java)

1. **Push code to GitHub**
2. **Visit https://railway.app**
3. **New Project → Deploy from GitHub**
4. **Select your repository**
5. **Railway auto-detects Maven and Spring Boot**
6. **Add environment variables:**
   - Click "Variables"
   - Add `OFFICIAL_EMAIL`
   - Add `GEMINI_API_KEY`
7. **Deploy automatically**

Your API URL: `https://your-app.railway.app`

---

### Option 2: Render

1. **Push to GitHub**
2. **Visit https://render.com**
3. **New → Web Service**
4. **Connect GitHub repository**
5. **Configure:**
   - **Build Command:** `mvn clean package`
   - **Start Command:** `java -jar target/qualifier-api-1.0.0.jar`
6. **Environment Variables:**
   - Add `OFFICIAL_EMAIL`
   - Add `GEMINI_API_KEY`
7. **Create Web Service**

Your API URL: `https://your-service.onrender.com`

---

### Option 3: Heroku

```bash
# Install Heroku CLI
# macOS: brew tap heroku/brew && brew install heroku
# Windows: Download from https://devcenter.heroku.com/articles/heroku-cli

# Login
heroku login

# Create app
heroku create your-app-name

# Set Java buildpack
heroku buildpacks:set heroku/java

# Set environment variables
heroku config:set OFFICIAL_EMAIL=your.email@chitkara.edu.in
heroku config:set GEMINI_API_KEY=your_key_here

# Deploy
git push heroku main

# Open app
heroku open
```

Your API URL: `https://your-app-name.herokuapp.com`

---

### Option 4: Docker

```bash
# Build Docker image
docker build -t chitkara-qualifier-api .

# Run container
docker run -p 8080:8080 \
  -e OFFICIAL_EMAIL=your.email@chitkara.edu.in \
  -e GEMINI_API_KEY=your_key_here \
  chitkara-qualifier-api

# Or use docker-compose
docker-compose up -d
```

---

## 🧪 Testing Commands

### Test Health Endpoint
```bash
curl http://localhost:8080/health
```

### Test All Operations
```bash
# Fibonacci
curl -X POST http://localhost:8080/bfhl \
  -H "Content-Type: application/json" \
  -d '{"fibonacci": 7}'

# Prime
curl -X POST http://localhost:8080/bfhl \
  -H "Content-Type: application/json" \
  -d '{"prime": [2,4,7,9,11]}'

# LCM
curl -X POST http://localhost:8080/bfhl \
  -H "Content-Type: application/json" \
  -d '{"lcm": [12,18,24]}'

# HCF
curl -X POST http://localhost:8080/bfhl \
  -H "Content-Type: application/json" \
  -d '{"hcf": [24,36,60]}'

# AI
curl -X POST http://localhost:8080/bfhl \
  -H "Content-Type: application/json" \
  -d '{"AI": "What is the capital city of Maharashtra?"}'
```

### Run JUnit Tests
```bash
mvn test
```

---

## 🐛 Common Issues & Fixes

### Issue: "Java version not supported"
**Fix:** Install Java 17 or higher from https://adoptium.net/

### Issue: "mvn command not found"
**Fix:** Install Maven from https://maven.apache.org/download.cgi

### Issue: "GEMINI_API_KEY not configured"
**Fix:** 
1. Check .env file exists
2. Verify API key is correct
3. Restart the application

### Issue: "Port 8080 already in use"
**Fix:** 
```bash
# Use different port
export PORT=8081
mvn spring-boot:run
```

### Issue: Tests fail
**Fix:**
1. Ensure application is NOT running when testing
2. Check environment variables
3. Verify API key is valid

### Issue: "Cannot find JAR file"
**Fix:**
```bash
# Clean and rebuild
mvn clean package
```

---

## 📋 Maven Useful Commands

```bash
# Clean build
mvn clean

# Compile only
mvn compile

# Run tests
mvn test

# Package without tests
mvn package -DskipTests

# Clean install
mvn clean install

# Run application
mvn spring-boot:run

# Generate JAR
mvn clean package

# Run JAR
java -jar target/qualifier-api-1.0.0.jar
```

---

## 📦 Project Files Overview

```
chitkara-qualifier-java-api/
├── src/main/java/          # Source code
│   ├── QualifierApplication.java
│   ├── ApiController.java
│   ├── MathOperationsService.java
│   └── AIService.java
├── src/main/resources/     # Configuration
│   └── application.properties
├── src/test/java/          # Tests
│   └── QualifierApplicationTests.java
├── pom.xml                 # Maven configuration
├── Dockerfile             # Docker configuration
├── .env.example           # Environment template
└── README.md              # Documentation
```

---

## 🎯 Final Submission

Submit these URLs:

1. **GitHub Repository URL:**
   ```
   https://github.com/yourusername/chitkara-qualifier-java-api
   ```

2. **Deployed API URL:**
   ```
   https://your-app.railway.app
   ```

3. **Test endpoints:**
   ```
   https://your-app.railway.app/health
   https://your-app.railway.app/bfhl
   ```

---

## ✨ Quick Commands Summary

```bash
# Setup
mvn clean install

# Run
mvn spring-boot:run

# Test
mvn test

# Build JAR
mvn clean package

# Run JAR
java -jar target/qualifier-api-1.0.0.jar

# Deploy (after pushing to GitHub)
# Railway: Auto-detects and deploys
# Render: Configure build and start commands
# Heroku: git push heroku main
```

---

## 🎓 You're Ready!

✅ Java 17+ installed  
✅ Maven configured  
✅ All tests passing  
✅ API running locally  
✅ Ready to deploy  

**Good luck with your Chitkara Qualifier! 🚀**
