package com.chitkara.qualifier;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for Chitkara Qualifier API
 * Tests all endpoints, operations, edge cases, and error scenarios
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QualifierApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port;
    }

    // ==================== HEALTH ENDPOINT TESTS ====================

    @Test
    void testHealthEndpoint_Returns200() {
        ResponseEntity<Map> response = restTemplate.getForEntity(
            getBaseUrl() + "/health",
            Map.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testHealthEndpoint_HasIsSuccessTrue() {
        ResponseEntity<Map> response = restTemplate.getForEntity(
            getBaseUrl() + "/health",
            Map.class
        );
        assertTrue((Boolean) response.getBody().get("is_success"));
    }

    @Test
    void testHealthEndpoint_HasOfficialEmail() {
        ResponseEntity<Map> response = restTemplate.getForEntity(
            getBaseUrl() + "/health",
            Map.class
        );
        assertNotNull(response.getBody().get("official_email"));
    }

    // ==================== FIBONACCI TESTS ====================

    @Test
    void testFibonacci_N7_ReturnsCorrectSequence() {
        Map<String, Object> request = new HashMap<>();
        request.put("fibonacci", 7);

        ResponseEntity<Map> response = restTemplate.postForEntity(
            getBaseUrl() + "/bfhl",
            request,
            Map.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Integer> expected = Arrays.asList(0, 1, 1, 2, 3, 5, 8);
        assertEquals(expected, response.getBody().get("data"));
    }

    @Test
    void testFibonacci_N0_ReturnsEmptyList() {
        Map<String, Object> request = new HashMap<>();
        request.put("fibonacci", 0);

        ResponseEntity<Map> response = restTemplate.postForEntity(
            getBaseUrl() + "/bfhl",
            request,
            Map.class
        );

        assertTrue(((List<?>) response.getBody().get("data")).isEmpty());
    }

    @Test
    void testFibonacci_N1_ReturnsSingleElement() {
        Map<String, Object> request = new HashMap<>();
        request.put("fibonacci", 1);

        ResponseEntity<Map> response = restTemplate.postForEntity(
            getBaseUrl() + "/bfhl",
            request,
            Map.class
        );

        List<Integer> expected = Arrays.asList(0);
        assertEquals(expected, response.getBody().get("data"));
    }

    @Test
    void testFibonacci_NegativeNumber_Returns400() {
        Map<String, Object> request = new HashMap<>();
        request.put("fibonacci", -5);

        ResponseEntity<Map> response = restTemplate.postForEntity(
            getBaseUrl() + "/bfhl",
            request,
            Map.class
        );

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertFalse((Boolean) response.getBody().get("is_success"));
    }

    // ==================== PRIME TESTS ====================

    @Test
    void testPrime_FiltersCorrectly() {
        Map<String, Object> request = new HashMap<>();
        request.put("prime", Arrays.asList(2, 4, 7, 9, 11));

        ResponseEntity<Map> response = restTemplate.postForEntity(
            getBaseUrl() + "/bfhl",
            request,
            Map.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Integer> expected = Arrays.asList(2, 7, 11);
        assertEquals(expected, response.getBody().get("data"));
    }

    @Test
    void testPrime_Excludes1() {
        Map<String, Object> request = new HashMap<>();
        request.put("prime", Arrays.asList(1, 2, 3, 4, 5));

        ResponseEntity<Map> response = restTemplate.postForEntity(
            getBaseUrl() + "/bfhl",
            request,
            Map.class
        );

        List<Integer> result = (List<Integer>) response.getBody().get("data");
        assertFalse(result.contains(1));
    }

    @Test
    void testPrime_AllPrimes_ReturnsAll() {
        Map<String, Object> request = new HashMap<>();
        request.put("prime", Arrays.asList(13, 17, 19, 23));

        ResponseEntity<Map> response = restTemplate.postForEntity(
            getBaseUrl() + "/bfhl",
            request,
            Map.class
        );

        List<Integer> result = (List<Integer>) response.getBody().get("data");
        assertEquals(4, result.size());
    }

    // ==================== LCM TESTS ====================

    @Test
    void testLCM_CalculatesCorrectly() {
        Map<String, Object> request = new HashMap<>();
        request.put("lcm", Arrays.asList(12, 18, 24));

        ResponseEntity<Map> response = restTemplate.postForEntity(
            getBaseUrl() + "/bfhl",
            request,
            Map.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(72, response.getBody().get("data"));
    }

    @Test
    void testLCM_TwoNumbers() {
        Map<String, Object> request = new HashMap<>();
        request.put("lcm", Arrays.asList(4, 6));

        ResponseEntity<Map> response = restTemplate.postForEntity(
            getBaseUrl() + "/bfhl",
            request,
            Map.class
        );

        assertEquals(12, response.getBody().get("data"));
    }

    // ==================== HCF TESTS ====================

    @Test
    void testHCF_CalculatesCorrectly() {
        Map<String, Object> request = new HashMap<>();
        request.put("hcf", Arrays.asList(24, 36, 60));

        ResponseEntity<Map> response = restTemplate.postForEntity(
            getBaseUrl() + "/bfhl",
            request,
            Map.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(12, response.getBody().get("data"));
    }

    @Test
    void testHCF_TwoNumbers() {
        Map<String, Object> request = new HashMap<>();
        request.put("hcf", Arrays.asList(48, 18));

        ResponseEntity<Map> response = restTemplate.postForEntity(
            getBaseUrl() + "/bfhl",
            request,
            Map.class
        );

        assertEquals(6, response.getBody().get("data"));
    }

    // ==================== ERROR HANDLING TESTS ====================

    @Test
    void testNoOperationKey_Returns400() {
        Map<String, Object> request = new HashMap<>();

        ResponseEntity<Map> response = restTemplate.postForEntity(
            getBaseUrl() + "/bfhl",
            request,
            Map.class
        );

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertFalse((Boolean) response.getBody().get("is_success"));
    }

    @Test
    void testMultipleOperationKeys_Returns400() {
        Map<String, Object> request = new HashMap<>();
        request.put("fibonacci", 5);
        request.put("prime", Arrays.asList(1, 2, 3));

        ResponseEntity<Map> response = restTemplate.postForEntity(
            getBaseUrl() + "/bfhl",
            request,
            Map.class
        );

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertFalse((Boolean) response.getBody().get("is_success"));
    }

    @Test
    void testInvalidPrimeInput_Returns400() {
        Map<String, Object> request = new HashMap<>();
        request.put("prime", "not an array");

        ResponseEntity<Map> response = restTemplate.postForEntity(
            getBaseUrl() + "/bfhl",
            request,
            Map.class
        );

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    // ==================== RESPONSE STRUCTURE TESTS ====================

    @Test
    void testResponseStructure_HasIsSuccess() {
        Map<String, Object> request = new HashMap<>();
        request.put("fibonacci", 5);

        ResponseEntity<Map> response = restTemplate.postForEntity(
            getBaseUrl() + "/bfhl",
            request,
            Map.class
        );

        assertTrue(response.getBody().containsKey("is_success"));
    }

    @Test
    void testResponseStructure_HasOfficialEmail() {
        Map<String, Object> request = new HashMap<>();
        request.put("fibonacci", 5);

        ResponseEntity<Map> response = restTemplate.postForEntity(
            getBaseUrl() + "/bfhl",
            request,
            Map.class
        );

        assertTrue(response.getBody().containsKey("official_email"));
    }

    @Test
    void testResponseStructure_HasData() {
        Map<String, Object> request = new HashMap<>();
        request.put("fibonacci", 5);

        ResponseEntity<Map> response = restTemplate.postForEntity(
            getBaseUrl() + "/bfhl",
            request,
            Map.class
        );

        assertTrue(response.getBody().containsKey("data"));
    }

    @Test
    void testErrorResponse_HasErrorField() {
        Map<String, Object> request = new HashMap<>();

        ResponseEntity<Map> response = restTemplate.postForEntity(
            getBaseUrl() + "/bfhl",
            request,
            Map.class
        );

        assertTrue(response.getBody().containsKey("error"));
    }
}
