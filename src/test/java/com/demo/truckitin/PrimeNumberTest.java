package com.demo.truckitin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
public class PrimeNumberTest {

    private static final List<Integer> primeList = List.of(
            2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 1021
    );

    private static final List<Integer> nonPrimes = List.of(
            4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25, 26, 27, 28,
            30, 32, 33, 34, 35, 36, 38, 39, 40, 42, 44, 45, 46, 48, 49, 50
    );

    @Test
    public void testIsPrime() {
        primeList.forEach(number -> assertTrue(isPrime(number), number + " is prime"));
    }

    @Test
    public void testNotPrime() {
        nonPrimes.forEach(number -> assertFalse(isPrime(number), number + " is not prime"));
    }

    /**
     * Checks if a number is prime.
     * @param number The number to check
     * @return {@link Boolean} true if number is a prime number
     */
    private boolean isPrime(int number) {
        //  1 and 2 are prime numbers
        if (number <= 2) {
            return true;
        }

        // Even number expect 2 are non primes.
        if (number % 2 == 0) {
            return false;
        }

        // Check the number by dividing it with every number
        // till number/2.
        for (int i = 3; i < number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
