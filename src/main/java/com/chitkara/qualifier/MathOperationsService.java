package com.chitkara.qualifier;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for mathematical operations
 * Handles fibonacci, prime, lcm, and hcf calculations
 */
@Service
public class MathOperationsService {

    /**
     * Generate Fibonacci sequence
     * @param n Number of terms
     * @return List of fibonacci numbers
     */
    public List<Long> generateFibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input must be a non-negative integer");
        }

        List<Long> fibonacci = new ArrayList<>();
        
        if (n == 0) return fibonacci;
        if (n == 1) {
            fibonacci.add(0L);
            return fibonacci;
        }

        fibonacci.add(0L);
        fibonacci.add(1L);

        for (int i = 2; i < n; i++) {
            long next = fibonacci.get(i - 1) + fibonacci.get(i - 2);
            fibonacci.add(next);
        }

        return fibonacci;
    }

    /**
     * Filter prime numbers from a list
     * @param numbers List of numbers to filter
     * @return List of prime numbers
     */
    public List<Integer> filterPrimes(List<Number> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("Input must be an array");
        }

        return numbers.stream()
                .map(Number::intValue)
                .filter(this::isPrime)
                .collect(Collectors.toList());
    }

    /**
     * Check if a number is prime
     */
    private boolean isPrime(int num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;

        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }

    /**
     * Calculate Least Common Multiple (LCM) of numbers
     * @param numbers List of numbers
     * @return LCM value
     */
    public long calculateLCM(List<Number> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("Input must be a non-empty array");
        }

        List<Long> longNumbers = numbers.stream()
                .map(Number::longValue)
                .collect(Collectors.toList());

        // Validate all positive integers
        for (Long num : longNumbers) {
            if (num <= 0) {
                throw new IllegalArgumentException("All elements must be positive integers");
            }
        }

        return longNumbers.stream()
                .reduce(this::lcm)
                .orElseThrow();
    }

    /**
     * Calculate LCM of two numbers
     */
    private long lcm(long a, long b) {
        return Math.abs(a * b) / gcd(a, b);
    }

    /**
     * Calculate Highest Common Factor (HCF/GCD) of numbers
     * @param numbers List of numbers
     * @return HCF value
     */
    public long calculateHCF(List<Number> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("Input must be a non-empty array");
        }

        List<Long> longNumbers = numbers.stream()
                .map(Number::longValue)
                .collect(Collectors.toList());

        // Validate all positive integers
        for (Long num : longNumbers) {
            if (num <= 0) {
                throw new IllegalArgumentException("All elements must be positive integers");
            }
        }

        return longNumbers.stream()
                .reduce(this::gcd)
                .orElseThrow();
    }

    /**
     * Calculate GCD of two numbers using Euclidean algorithm
     */
    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
