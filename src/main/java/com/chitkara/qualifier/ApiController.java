package com.chitkara.qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST Controller handling all API endpoints
 * POST /bfhl - Main functional endpoint with 5 operations
 * GET /health - Health check endpoint
 */
@RestController
@CrossOrigin(origins = "*")
public class ApiController {

    @Value("${app.official.email}")
    private String officialEmail;

    @Autowired
    private MathOperationsService mathService;

    @Autowired
    private AIService aiService;

    /**
     * Health check endpoint
     * GET /health
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("is_success", true);
        response.put("official_email", officialEmail);
        return ResponseEntity.ok(response);
    }

    /**
     * Main functional endpoint supporting 5 operations
     * POST /bfhl
     */
    @PostMapping("/bfhl")
    public ResponseEntity<Map<String, Object>> bfhl(@RequestBody Map<String, Object> request) {
        try {
            // Validate exactly one operation key
            String[] validKeys = {"fibonacci", "prime", "lcm", "hcf", "AI"};
            long keyCount = request.keySet().stream()
                    .filter(key -> List.of(validKeys).contains(key))
                    .count();

            if (keyCount == 0) {
                return createErrorResponse(
                    "No valid operation key provided. Expected one of: fibonacci, prime, lcm, hcf, AI",
                    HttpStatus.BAD_REQUEST
                );
            }

            if (keyCount > 1) {
                return createErrorResponse(
                    "Multiple operation keys provided. Only one key is allowed per request",
                    HttpStatus.BAD_REQUEST
                );
            }

            // Process based on operation type
            Object data = null;

            if (request.containsKey("fibonacci")) {
                Object input = request.get("fibonacci");
                if (!(input instanceof Number)) {
                    return createErrorResponse("Fibonacci input must be a number", HttpStatus.BAD_REQUEST);
                }
                int n = ((Number) input).intValue();
                data = mathService.generateFibonacci(n);

            } else if (request.containsKey("prime")) {
                Object input = request.get("prime");
                if (!(input instanceof List)) {
                    return createErrorResponse("Prime input must be an array", HttpStatus.BAD_REQUEST);
                }
                @SuppressWarnings("unchecked")
                List<Number> numbers = (List<Number>) input;
                data = mathService.filterPrimes(numbers);

            } else if (request.containsKey("lcm")) {
                Object input = request.get("lcm");
                if (!(input instanceof List)) {
                    return createErrorResponse("LCM input must be an array", HttpStatus.BAD_REQUEST);
                }
                @SuppressWarnings("unchecked")
                List<Number> numbers = (List<Number>) input;
                data = mathService.calculateLCM(numbers);

            } else if (request.containsKey("hcf")) {
                Object input = request.get("hcf");
                if (!(input instanceof List)) {
                    return createErrorResponse("HCF input must be an array", HttpStatus.BAD_REQUEST);
                }
                @SuppressWarnings("unchecked")
                List<Number> numbers = (List<Number>) input;
                data = mathService.calculateHCF(numbers);

            } else if (request.containsKey("AI")) {
                Object input = request.get("AI");
                if (!(input instanceof String)) {
                    return createErrorResponse("AI input must be a string", HttpStatus.BAD_REQUEST);
                }
                String question = (String) input;
                data = aiService.queryAI(question);
            }

            // Success response
            Map<String, Object> response = new HashMap<>();
            response.put("is_success", true);
            response.put("official_email", officialEmail);
            response.put("data", data);

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            return createErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return createErrorResponse(
                e.getMessage().contains("unavailable") ? e.getMessage() : "Internal server error",
                e.getMessage().contains("unavailable") ? HttpStatus.SERVICE_UNAVAILABLE : HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    /**
     * Helper method to create error responses
     */
    private ResponseEntity<Map<String, Object>> createErrorResponse(String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("is_success", false);
        response.put("official_email", officialEmail);
        response.put("error", message);
        return ResponseEntity.status(status).body(response);
    }
}
