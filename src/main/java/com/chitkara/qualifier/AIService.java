package com.chitkara.qualifier;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service class for AI integration using Google Gemini API
 */
@Service
public class AIService {

    @Value("${app.gemini.api.key}")
    private String geminiApiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Query Google Gemini AI with a question
     * @param question Question to ask the AI
     * @return AI response as a string
     */
    public String queryAI(String question) {
        if (question == null || question.trim().isEmpty()) {
            throw new IllegalArgumentException("Question must be a non-empty string");
        }

        if (geminiApiKey == null || geminiApiKey.isEmpty()) {
            throw new RuntimeException("GEMINI_API_KEY not configured");
        }

        try {
            String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=" + geminiApiKey;

            // Build request body
            Map<String, Object> requestBody = new HashMap<>();
            
            Map<String, Object> part = new HashMap<>();
            part.put("text", "Answer the following question with a single word or very brief phrase (maximum 2-3 words): " + question);
            
            Map<String, Object> content = new HashMap<>();
            content.put("parts", List.of(part));
            
            requestBody.put("contents", List.of(content));
            
            Map<String, Object> generationConfig = new HashMap<>();
            generationConfig.put("temperature", 0.1);
            generationConfig.put("maxOutputTokens", 10);
            requestBody.put("generationConfig", generationConfig);

            // Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

            // Make API call
            ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                String.class
            );

            // Parse response
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            String answer = rootNode
                    .path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText()
                    .trim();

            return answer;

        } catch (Exception e) {
            throw new RuntimeException("AI service unavailable");
        }
    }
}
