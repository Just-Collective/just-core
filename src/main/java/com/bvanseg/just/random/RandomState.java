package com.bvanseg.just.random;

import com.bvanseg.just.functional.tuple.Tuple2;

public final class RandomState {

    // Java's LCG constants
    private static final long MULTIPLIER = 0x5DEECE66DL; // 25214903917

    private static final long ADDEND = 0xBL; // 11

    private static final long MASK = (1L << 48) - 1; // 48-bit mask

    private static final double DOUBLE_UNIT = 0x1.0p-53; // 1.0 / (1L << Double.PRECISION)

    private static final float FLOAT_UNIT = 0x1.0p-24f; // 1.0f / (1 << Float.PRECISION)

    public static RandomState seed(long seed) {
        // XOR like java.util.Random.
        return new RandomState(seed ^ MULTIPLIER);
    }

    private final long seed;

    private RandomState(long seed) {
        this.seed = seed & MASK;
    }

    /**
     * Advances the RNG and returns the next state + raw bits.
     */
    public Tuple2<Integer, RandomState> next(int bits) {
        var nextSeed = (seed * MULTIPLIER + ADDEND) & MASK;
        var value = (int) (nextSeed >>> (48 - bits));

        return new Tuple2<>(value, new RandomState(nextSeed));
    }

    public Tuple2<Double, RandomState> nextDouble() {
        var firstState = next(Double.PRECISION - 27);
        var secondState = firstState.v2().next(27);
        var value = (((long) (firstState.v1()) << 27) + secondState.v1()) * DOUBLE_UNIT;

        return new Tuple2<>(value, secondState.v2());
    }

    public Tuple2<Float, RandomState> nextFloat() {
        var firstState = next(Float.PRECISION);

        return new Tuple2<>(firstState.v1() * FLOAT_UNIT, firstState.v2());
    }

    public Tuple2<Integer, RandomState> nextInt() {
        return next(32);
    }

    public Tuple2<Integer, RandomState> nextInt(int bound) {
        if (bound <= 0) {
            throw new IllegalArgumentException("Bound must be positive.");
        }

        var firstState = next(31);
        var randomState = firstState.v2();
        var r = firstState.v1();
        var m = bound - 1;

        // i.e., bound is a power of 2.
        if ((bound & m) == 0) {
            r = (int) ((bound * (long) r) >> 31);
        } else {
            // reject over-represented candidates.
            for (int u = r; u - (r = u % bound) + m < 0;) {
                var state = next(31);
                u = state.v1();
                randomState = state.v2();
            }
        }

        return new Tuple2<>(r, randomState);
    }

    public Tuple2<Long, RandomState> nextLong() {
        var firstState = next(32);
        var secondState = firstState.v2().next(32);
        var value = ((long) (firstState.v1()) << 32) + secondState.v1();

        return new Tuple2<>(value, secondState.v2());
    }

    public long seed() {
        return seed;
    }

    @Override
    public String toString() {
        return "RandomState(seed=" + seed + ")";
    }
}
