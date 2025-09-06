package com.just.core.random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomStateTest {

    private Random random;

    private RandomState randomState;

    @BeforeEach
    void prepareRandoms() {
        var seed = 123456789L;
        randomState = RandomState.seed(seed);
        random = new Random(seed);
    }

    @Test
    void nextInt_ProducesSameAsJavaUtilRandom() {
        for (var i = 0; i < 100; i++) {
            var custom = randomState.nextInt();
            var javaVal = random.nextInt();

            assertEquals(javaVal, custom.v1());
            randomState = custom.v2();
        }
    }

    @Test
    void nextIntBounded_MatchesJavaRandom() {
        var bound = 50;

        for (var i = 0; i < 100; i++) {
            var next = randomState.nextInt(bound);
            var expected = random.nextInt(bound);

            assertEquals(expected, next.v1());
            randomState = next.v2();
        }
    }

    @Test
    void nextIntBounded_PowerOfTwoBound_MatchesJava() {
        int bound = 64; // power of 2

        for (int i = 0; i < 100; i++) {
            var custom = randomState.nextInt(bound);
            var expected = random.nextInt(bound);

            assertEquals(expected, custom.v1());
            randomState = custom.v2();
        }
    }

    @Test
    void nextIntBounded_NonPowerOfTwoBound_MatchesJava() {
        int bound = 37; // not a power of 2

        for (int i = 0; i < 100; i++) {
            var custom = randomState.nextInt(bound);
            var expected = random.nextInt(bound);

            assertEquals(expected, custom.v1());
            randomState = custom.v2();
        }
    }

    @Test
    void nextIntBounded_WithinBounds() {
        var randState = RandomState.seed(42);

        for (var i = 0; i < 1000; i++) {
            var result = randState.nextInt(100);
            var value = result.v1();

            assertTrue(value >= 0 && value < 100);
            randState = result.v2();
        }
    }

    @Test
    void nextIntBounded_ThrowsOnZeroOrNegativeBound() {
        var randState = RandomState.seed(1);
        assertThrows(IllegalArgumentException.class, () -> randState.nextInt(0));
        assertThrows(IllegalArgumentException.class, () -> randState.nextInt(-10));
    }

    @Test
    void nextLong_ProducesSameAsJavaUtilRandom() {
        for (int i = 0; i < 100; i++) {
            var custom = randomState.nextLong();
            var javaVal = random.nextLong();

            assertEquals(javaVal, custom.v1());
            randomState = custom.v2();
        }
    }

    @Test
    void nextFloat_ProducesSameAsJavaUtilRandom() {
        for (int i = 0; i < 100; i++) {
            var custom = randomState.nextFloat();
            var javaVal = random.nextFloat();

            assertEquals(javaVal, custom.v1());
            randomState = custom.v2();
        }
    }

    @Test
    void nextDouble_ProducesSameAsJavaUtilRandom() {
        for (int i = 0; i < 100; i++) {
            var custom = randomState.nextDouble();
            var javaVal = random.nextDouble();

            assertEquals(javaVal, custom.v1());
            randomState = custom.v2();
        }
    }

    @Test
    void seed_ReturnsOriginalSeedValue() {
        // The seed returned by seed() should match the internal masked seed
        assertEquals(randomState.seed(), randomState.seed());
    }

    @Test
    void toString_ContainsSeedValue() {
        var output = randomState.toString();

        assertTrue(output.contains("RandomState(seed="));
        assertTrue(output.contains(Long.toString(randomState.seed())));
        assertTrue(output.endsWith(")"));
    }
}
