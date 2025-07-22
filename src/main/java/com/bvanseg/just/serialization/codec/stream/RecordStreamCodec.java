package com.bvanseg.just.serialization.codec.stream;

import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;

import com.bvanseg.just.functional.function.*;

public final class RecordStreamCodec {

    public static <A1, R> StreamCodec<ByteBuffer, R> of(
        StreamCodec<ByteBuffer, A1> codec1,
        Function<R, A1> getter1,
        Function<A1, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public void encode(@NotNull ByteBuffer buffer, @NotNull R value) {
                codec1.encode(buffer, getter1.apply(value));
            }

            @Override
            public @NotNull R decode(@NotNull ByteBuffer buffer) {
                var a1 = codec1.decode(buffer);
                return constructor.apply(a1);
            }
        };
    }

    public static <A1, A2, R> StreamCodec<ByteBuffer, R> of(
        StreamCodec<ByteBuffer, A1> codec1,
        Function<R, A1> getter1,
        StreamCodec<ByteBuffer, A2> codec2,
        Function<R, A2> getter2,
        Function2<A1, A2, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public void encode(@NotNull ByteBuffer buffer, @NotNull R value) {
                codec1.encode(buffer, getter1.apply(value));
                codec2.encode(buffer, getter2.apply(value));
            }

            @Override
            public @NotNull R decode(@NotNull ByteBuffer buffer) {
                var a1 = codec1.decode(buffer);
                var a2 = codec2.decode(buffer);
                return constructor.apply(a1, a2);
            }
        };
    }

    public static <A1, A2, A3, R> StreamCodec<ByteBuffer, R> of(
        StreamCodec<ByteBuffer, A1> codec1,
        Function<R, A1> getter1,
        StreamCodec<ByteBuffer, A2> codec2,
        Function<R, A2> getter2,
        StreamCodec<ByteBuffer, A3> codec3,
        Function<R, A3> getter3,
        Function3<A1, A2, A3, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public void encode(@NotNull ByteBuffer buffer, @NotNull R value) {
                codec1.encode(buffer, getter1.apply(value));
                codec2.encode(buffer, getter2.apply(value));
                codec3.encode(buffer, getter3.apply(value));
            }

            @Override
            public @NotNull R decode(@NotNull ByteBuffer buffer) {
                var a1 = codec1.decode(buffer);
                var a2 = codec2.decode(buffer);
                var a3 = codec3.decode(buffer);
                return constructor.apply(a1, a2, a3);
            }
        };
    }

    public static <A1, A2, A3, A4, R> StreamCodec<ByteBuffer, R> of(
        StreamCodec<ByteBuffer, A1> codec1,
        Function<R, A1> getter1,
        StreamCodec<ByteBuffer, A2> codec2,
        Function<R, A2> getter2,
        StreamCodec<ByteBuffer, A3> codec3,
        Function<R, A3> getter3,
        StreamCodec<ByteBuffer, A4> codec4,
        Function<R, A4> getter4,
        Function4<A1, A2, A3, A4, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public void encode(@NotNull ByteBuffer buffer, @NotNull R value) {
                codec1.encode(buffer, getter1.apply(value));
                codec2.encode(buffer, getter2.apply(value));
                codec3.encode(buffer, getter3.apply(value));
                codec4.encode(buffer, getter4.apply(value));
            }

            @Override
            public @NotNull R decode(@NotNull ByteBuffer buffer) {
                var a1 = codec1.decode(buffer);
                var a2 = codec2.decode(buffer);
                var a3 = codec3.decode(buffer);
                var a4 = codec4.decode(buffer);
                return constructor.apply(a1, a2, a3, a4);
            }
        };
    }

    public static <A1, A2, A3, A4, A5, R> StreamCodec<ByteBuffer, R> of(
        StreamCodec<ByteBuffer, A1> codec1,
        Function<R, A1> getter1,
        StreamCodec<ByteBuffer, A2> codec2,
        Function<R, A2> getter2,
        StreamCodec<ByteBuffer, A3> codec3,
        Function<R, A3> getter3,
        StreamCodec<ByteBuffer, A4> codec4,
        Function<R, A4> getter4,
        StreamCodec<ByteBuffer, A5> codec5,
        Function<R, A5> getter5,
        Function5<A1, A2, A3, A4, A5, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public void encode(@NotNull ByteBuffer buffer, @NotNull R value) {
                codec1.encode(buffer, getter1.apply(value));
                codec2.encode(buffer, getter2.apply(value));
                codec3.encode(buffer, getter3.apply(value));
                codec4.encode(buffer, getter4.apply(value));
                codec5.encode(buffer, getter5.apply(value));
            }

            @Override
            public @NotNull R decode(@NotNull ByteBuffer buffer) {
                var a1 = codec1.decode(buffer);
                var a2 = codec2.decode(buffer);
                var a3 = codec3.decode(buffer);
                var a4 = codec4.decode(buffer);
                var a5 = codec5.decode(buffer);
                return constructor.apply(a1, a2, a3, a4, a5);
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, R> StreamCodec<ByteBuffer, R> of(
        StreamCodec<ByteBuffer, A1> codec1,
        Function<R, A1> getter1,
        StreamCodec<ByteBuffer, A2> codec2,
        Function<R, A2> getter2,
        StreamCodec<ByteBuffer, A3> codec3,
        Function<R, A3> getter3,
        StreamCodec<ByteBuffer, A4> codec4,
        Function<R, A4> getter4,
        StreamCodec<ByteBuffer, A5> codec5,
        Function<R, A5> getter5,
        StreamCodec<ByteBuffer, A6> codec6,
        Function<R, A6> getter6,
        Function6<A1, A2, A3, A4, A5, A6, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public void encode(@NotNull ByteBuffer buffer, @NotNull R value) {
                codec1.encode(buffer, getter1.apply(value));
                codec2.encode(buffer, getter2.apply(value));
                codec3.encode(buffer, getter3.apply(value));
                codec4.encode(buffer, getter4.apply(value));
                codec5.encode(buffer, getter5.apply(value));
                codec6.encode(buffer, getter6.apply(value));
            }

            @Override
            public @NotNull R decode(@NotNull ByteBuffer buffer) {
                var a1 = codec1.decode(buffer);
                var a2 = codec2.decode(buffer);
                var a3 = codec3.decode(buffer);
                var a4 = codec4.decode(buffer);
                var a5 = codec5.decode(buffer);
                var a6 = codec6.decode(buffer);
                return constructor.apply(a1, a2, a3, a4, a5, a6);
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, A7, R> StreamCodec<ByteBuffer, R> of(
        StreamCodec<ByteBuffer, A1> codec1,
        Function<R, A1> getter1,
        StreamCodec<ByteBuffer, A2> codec2,
        Function<R, A2> getter2,
        StreamCodec<ByteBuffer, A3> codec3,
        Function<R, A3> getter3,
        StreamCodec<ByteBuffer, A4> codec4,
        Function<R, A4> getter4,
        StreamCodec<ByteBuffer, A5> codec5,
        Function<R, A5> getter5,
        StreamCodec<ByteBuffer, A6> codec6,
        Function<R, A6> getter6,
        StreamCodec<ByteBuffer, A7> codec7,
        Function<R, A7> getter7,
        Function7<A1, A2, A3, A4, A5, A6, A7, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public void encode(@NotNull ByteBuffer buffer, @NotNull R value) {
                codec1.encode(buffer, getter1.apply(value));
                codec2.encode(buffer, getter2.apply(value));
                codec3.encode(buffer, getter3.apply(value));
                codec4.encode(buffer, getter4.apply(value));
                codec5.encode(buffer, getter5.apply(value));
                codec6.encode(buffer, getter6.apply(value));
                codec7.encode(buffer, getter7.apply(value));
            }

            @Override
            public @NotNull R decode(@NotNull ByteBuffer buffer) {
                var a1 = codec1.decode(buffer);
                var a2 = codec2.decode(buffer);
                var a3 = codec3.decode(buffer);
                var a4 = codec4.decode(buffer);
                var a5 = codec5.decode(buffer);
                var a6 = codec6.decode(buffer);
                var a7 = codec7.decode(buffer);
                return constructor.apply(a1, a2, a3, a4, a5, a6, a7);
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, A7, A8, R> StreamCodec<ByteBuffer, R> of(
        StreamCodec<ByteBuffer, A1> codec1,
        Function<R, A1> getter1,
        StreamCodec<ByteBuffer, A2> codec2,
        Function<R, A2> getter2,
        StreamCodec<ByteBuffer, A3> codec3,
        Function<R, A3> getter3,
        StreamCodec<ByteBuffer, A4> codec4,
        Function<R, A4> getter4,
        StreamCodec<ByteBuffer, A5> codec5,
        Function<R, A5> getter5,
        StreamCodec<ByteBuffer, A6> codec6,
        Function<R, A6> getter6,
        StreamCodec<ByteBuffer, A7> codec7,
        Function<R, A7> getter7,
        StreamCodec<ByteBuffer, A8> codec8,
        Function<R, A8> getter8,
        Function8<A1, A2, A3, A4, A5, A6, A7, A8, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public void encode(@NotNull ByteBuffer buffer, @NotNull R value) {
                codec1.encode(buffer, getter1.apply(value));
                codec2.encode(buffer, getter2.apply(value));
                codec3.encode(buffer, getter3.apply(value));
                codec4.encode(buffer, getter4.apply(value));
                codec5.encode(buffer, getter5.apply(value));
                codec6.encode(buffer, getter6.apply(value));
                codec7.encode(buffer, getter7.apply(value));
                codec8.encode(buffer, getter8.apply(value));
            }

            @Override
            public @NotNull R decode(@NotNull ByteBuffer buffer) {
                var a1 = codec1.decode(buffer);
                var a2 = codec2.decode(buffer);
                var a3 = codec3.decode(buffer);
                var a4 = codec4.decode(buffer);
                var a5 = codec5.decode(buffer);
                var a6 = codec6.decode(buffer);
                var a7 = codec7.decode(buffer);
                var a8 = codec8.decode(buffer);
                return constructor.apply(a1, a2, a3, a4, a5, a6, a7, a8);
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, R> StreamCodec<ByteBuffer, R> of(
        StreamCodec<ByteBuffer, A1> codec1,
        Function<R, A1> getter1,
        StreamCodec<ByteBuffer, A2> codec2,
        Function<R, A2> getter2,
        StreamCodec<ByteBuffer, A3> codec3,
        Function<R, A3> getter3,
        StreamCodec<ByteBuffer, A4> codec4,
        Function<R, A4> getter4,
        StreamCodec<ByteBuffer, A5> codec5,
        Function<R, A5> getter5,
        StreamCodec<ByteBuffer, A6> codec6,
        Function<R, A6> getter6,
        StreamCodec<ByteBuffer, A7> codec7,
        Function<R, A7> getter7,
        StreamCodec<ByteBuffer, A8> codec8,
        Function<R, A8> getter8,
        StreamCodec<ByteBuffer, A9> codec9,
        Function<R, A9> getter9,
        Function9<A1, A2, A3, A4, A5, A6, A7, A8, A9, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public void encode(@NotNull ByteBuffer buffer, @NotNull R value) {
                codec1.encode(buffer, getter1.apply(value));
                codec2.encode(buffer, getter2.apply(value));
                codec3.encode(buffer, getter3.apply(value));
                codec4.encode(buffer, getter4.apply(value));
                codec5.encode(buffer, getter5.apply(value));
                codec6.encode(buffer, getter6.apply(value));
                codec7.encode(buffer, getter7.apply(value));
                codec8.encode(buffer, getter8.apply(value));
                codec9.encode(buffer, getter9.apply(value));
            }

            @Override
            public @NotNull R decode(@NotNull ByteBuffer buffer) {
                var a1 = codec1.decode(buffer);
                var a2 = codec2.decode(buffer);
                var a3 = codec3.decode(buffer);
                var a4 = codec4.decode(buffer);
                var a5 = codec5.decode(buffer);
                var a6 = codec6.decode(buffer);
                var a7 = codec7.decode(buffer);
                var a8 = codec8.decode(buffer);
                var a9 = codec9.decode(buffer);
                return constructor.apply(a1, a2, a3, a4, a5, a6, a7, a8, a9);
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, R> StreamCodec<ByteBuffer, R> of(
        StreamCodec<ByteBuffer, A1> codec1,
        Function<R, A1> getter1,
        StreamCodec<ByteBuffer, A2> codec2,
        Function<R, A2> getter2,
        StreamCodec<ByteBuffer, A3> codec3,
        Function<R, A3> getter3,
        StreamCodec<ByteBuffer, A4> codec4,
        Function<R, A4> getter4,
        StreamCodec<ByteBuffer, A5> codec5,
        Function<R, A5> getter5,
        StreamCodec<ByteBuffer, A6> codec6,
        Function<R, A6> getter6,
        StreamCodec<ByteBuffer, A7> codec7,
        Function<R, A7> getter7,
        StreamCodec<ByteBuffer, A8> codec8,
        Function<R, A8> getter8,
        StreamCodec<ByteBuffer, A9> codec9,
        Function<R, A9> getter9,
        StreamCodec<ByteBuffer, A10> codec10,
        Function<R, A10> getter10,
        Function10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public void encode(@NotNull ByteBuffer buffer, @NotNull R value) {
                codec1.encode(buffer, getter1.apply(value));
                codec2.encode(buffer, getter2.apply(value));
                codec3.encode(buffer, getter3.apply(value));
                codec4.encode(buffer, getter4.apply(value));
                codec5.encode(buffer, getter5.apply(value));
                codec6.encode(buffer, getter6.apply(value));
                codec7.encode(buffer, getter7.apply(value));
                codec8.encode(buffer, getter8.apply(value));
                codec9.encode(buffer, getter9.apply(value));
                codec10.encode(buffer, getter10.apply(value));
            }

            @Override
            public @NotNull R decode(@NotNull ByteBuffer buffer) {
                var a1 = codec1.decode(buffer);
                var a2 = codec2.decode(buffer);
                var a3 = codec3.decode(buffer);
                var a4 = codec4.decode(buffer);
                var a5 = codec5.decode(buffer);
                var a6 = codec6.decode(buffer);
                var a7 = codec7.decode(buffer);
                var a8 = codec8.decode(buffer);
                var a9 = codec9.decode(buffer);
                var a10 = codec10.decode(buffer);
                return constructor.apply(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10);
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, R> StreamCodec<ByteBuffer, R> of(
        StreamCodec<ByteBuffer, A1> codec1,
        Function<R, A1> getter1,
        StreamCodec<ByteBuffer, A2> codec2,
        Function<R, A2> getter2,
        StreamCodec<ByteBuffer, A3> codec3,
        Function<R, A3> getter3,
        StreamCodec<ByteBuffer, A4> codec4,
        Function<R, A4> getter4,
        StreamCodec<ByteBuffer, A5> codec5,
        Function<R, A5> getter5,
        StreamCodec<ByteBuffer, A6> codec6,
        Function<R, A6> getter6,
        StreamCodec<ByteBuffer, A7> codec7,
        Function<R, A7> getter7,
        StreamCodec<ByteBuffer, A8> codec8,
        Function<R, A8> getter8,
        StreamCodec<ByteBuffer, A9> codec9,
        Function<R, A9> getter9,
        StreamCodec<ByteBuffer, A10> codec10,
        Function<R, A10> getter10,
        StreamCodec<ByteBuffer, A11> codec11,
        Function<R, A11> getter11,
        Function11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public void encode(@NotNull ByteBuffer buffer, @NotNull R value) {
                codec1.encode(buffer, getter1.apply(value));
                codec2.encode(buffer, getter2.apply(value));
                codec3.encode(buffer, getter3.apply(value));
                codec4.encode(buffer, getter4.apply(value));
                codec5.encode(buffer, getter5.apply(value));
                codec6.encode(buffer, getter6.apply(value));
                codec7.encode(buffer, getter7.apply(value));
                codec8.encode(buffer, getter8.apply(value));
                codec9.encode(buffer, getter9.apply(value));
                codec10.encode(buffer, getter10.apply(value));
                codec11.encode(buffer, getter11.apply(value));
            }

            @Override
            public @NotNull R decode(@NotNull ByteBuffer buffer) {
                var a1 = codec1.decode(buffer);
                var a2 = codec2.decode(buffer);
                var a3 = codec3.decode(buffer);
                var a4 = codec4.decode(buffer);
                var a5 = codec5.decode(buffer);
                var a6 = codec6.decode(buffer);
                var a7 = codec7.decode(buffer);
                var a8 = codec8.decode(buffer);
                var a9 = codec9.decode(buffer);
                var a10 = codec10.decode(buffer);
                var a11 = codec11.decode(buffer);
                return constructor.apply(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11);
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, R> StreamCodec<ByteBuffer, R> of(
        StreamCodec<ByteBuffer, A1> codec1,
        Function<R, A1> getter1,
        StreamCodec<ByteBuffer, A2> codec2,
        Function<R, A2> getter2,
        StreamCodec<ByteBuffer, A3> codec3,
        Function<R, A3> getter3,
        StreamCodec<ByteBuffer, A4> codec4,
        Function<R, A4> getter4,
        StreamCodec<ByteBuffer, A5> codec5,
        Function<R, A5> getter5,
        StreamCodec<ByteBuffer, A6> codec6,
        Function<R, A6> getter6,
        StreamCodec<ByteBuffer, A7> codec7,
        Function<R, A7> getter7,
        StreamCodec<ByteBuffer, A8> codec8,
        Function<R, A8> getter8,
        StreamCodec<ByteBuffer, A9> codec9,
        Function<R, A9> getter9,
        StreamCodec<ByteBuffer, A10> codec10,
        Function<R, A10> getter10,
        StreamCodec<ByteBuffer, A11> codec11,
        Function<R, A11> getter11,
        StreamCodec<ByteBuffer, A12> codec12,
        Function<R, A12> getter12,
        Function12<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public void encode(@NotNull ByteBuffer buffer, @NotNull R value) {
                codec1.encode(buffer, getter1.apply(value));
                codec2.encode(buffer, getter2.apply(value));
                codec3.encode(buffer, getter3.apply(value));
                codec4.encode(buffer, getter4.apply(value));
                codec5.encode(buffer, getter5.apply(value));
                codec6.encode(buffer, getter6.apply(value));
                codec7.encode(buffer, getter7.apply(value));
                codec8.encode(buffer, getter8.apply(value));
                codec9.encode(buffer, getter9.apply(value));
                codec10.encode(buffer, getter10.apply(value));
                codec11.encode(buffer, getter11.apply(value));
                codec12.encode(buffer, getter12.apply(value));
            }

            @Override
            public @NotNull R decode(@NotNull ByteBuffer buffer) {
                var a1 = codec1.decode(buffer);
                var a2 = codec2.decode(buffer);
                var a3 = codec3.decode(buffer);
                var a4 = codec4.decode(buffer);
                var a5 = codec5.decode(buffer);
                var a6 = codec6.decode(buffer);
                var a7 = codec7.decode(buffer);
                var a8 = codec8.decode(buffer);
                var a9 = codec9.decode(buffer);
                var a10 = codec10.decode(buffer);
                var a11 = codec11.decode(buffer);
                var a12 = codec12.decode(buffer);
                return constructor.apply(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12);
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, R> StreamCodec<ByteBuffer, R> of(
        StreamCodec<ByteBuffer, A1> codec1,
        Function<R, A1> getter1,
        StreamCodec<ByteBuffer, A2> codec2,
        Function<R, A2> getter2,
        StreamCodec<ByteBuffer, A3> codec3,
        Function<R, A3> getter3,
        StreamCodec<ByteBuffer, A4> codec4,
        Function<R, A4> getter4,
        StreamCodec<ByteBuffer, A5> codec5,
        Function<R, A5> getter5,
        StreamCodec<ByteBuffer, A6> codec6,
        Function<R, A6> getter6,
        StreamCodec<ByteBuffer, A7> codec7,
        Function<R, A7> getter7,
        StreamCodec<ByteBuffer, A8> codec8,
        Function<R, A8> getter8,
        StreamCodec<ByteBuffer, A9> codec9,
        Function<R, A9> getter9,
        StreamCodec<ByteBuffer, A10> codec10,
        Function<R, A10> getter10,
        StreamCodec<ByteBuffer, A11> codec11,
        Function<R, A11> getter11,
        StreamCodec<ByteBuffer, A12> codec12,
        Function<R, A12> getter12,
        StreamCodec<ByteBuffer, A13> codec13,
        Function<R, A13> getter13,
        Function13<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public void encode(@NotNull ByteBuffer buffer, @NotNull R value) {
                codec1.encode(buffer, getter1.apply(value));
                codec2.encode(buffer, getter2.apply(value));
                codec3.encode(buffer, getter3.apply(value));
                codec4.encode(buffer, getter4.apply(value));
                codec5.encode(buffer, getter5.apply(value));
                codec6.encode(buffer, getter6.apply(value));
                codec7.encode(buffer, getter7.apply(value));
                codec8.encode(buffer, getter8.apply(value));
                codec9.encode(buffer, getter9.apply(value));
                codec10.encode(buffer, getter10.apply(value));
                codec11.encode(buffer, getter11.apply(value));
                codec12.encode(buffer, getter12.apply(value));
                codec13.encode(buffer, getter13.apply(value));
            }

            @Override
            public @NotNull R decode(@NotNull ByteBuffer buffer) {
                var a1 = codec1.decode(buffer);
                var a2 = codec2.decode(buffer);
                var a3 = codec3.decode(buffer);
                var a4 = codec4.decode(buffer);
                var a5 = codec5.decode(buffer);
                var a6 = codec6.decode(buffer);
                var a7 = codec7.decode(buffer);
                var a8 = codec8.decode(buffer);
                var a9 = codec9.decode(buffer);
                var a10 = codec10.decode(buffer);
                var a11 = codec11.decode(buffer);
                var a12 = codec12.decode(buffer);
                var a13 = codec13.decode(buffer);
                return constructor.apply(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13);
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, R> StreamCodec<ByteBuffer, R> of(
        StreamCodec<ByteBuffer, A1> codec1,
        Function<R, A1> getter1,
        StreamCodec<ByteBuffer, A2> codec2,
        Function<R, A2> getter2,
        StreamCodec<ByteBuffer, A3> codec3,
        Function<R, A3> getter3,
        StreamCodec<ByteBuffer, A4> codec4,
        Function<R, A4> getter4,
        StreamCodec<ByteBuffer, A5> codec5,
        Function<R, A5> getter5,
        StreamCodec<ByteBuffer, A6> codec6,
        Function<R, A6> getter6,
        StreamCodec<ByteBuffer, A7> codec7,
        Function<R, A7> getter7,
        StreamCodec<ByteBuffer, A8> codec8,
        Function<R, A8> getter8,
        StreamCodec<ByteBuffer, A9> codec9,
        Function<R, A9> getter9,
        StreamCodec<ByteBuffer, A10> codec10,
        Function<R, A10> getter10,
        StreamCodec<ByteBuffer, A11> codec11,
        Function<R, A11> getter11,
        StreamCodec<ByteBuffer, A12> codec12,
        Function<R, A12> getter12,
        StreamCodec<ByteBuffer, A13> codec13,
        Function<R, A13> getter13,
        StreamCodec<ByteBuffer, A14> codec14,
        Function<R, A14> getter14,
        Function14<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public void encode(@NotNull ByteBuffer buffer, @NotNull R value) {
                codec1.encode(buffer, getter1.apply(value));
                codec2.encode(buffer, getter2.apply(value));
                codec3.encode(buffer, getter3.apply(value));
                codec4.encode(buffer, getter4.apply(value));
                codec5.encode(buffer, getter5.apply(value));
                codec6.encode(buffer, getter6.apply(value));
                codec7.encode(buffer, getter7.apply(value));
                codec8.encode(buffer, getter8.apply(value));
                codec9.encode(buffer, getter9.apply(value));
                codec10.encode(buffer, getter10.apply(value));
                codec11.encode(buffer, getter11.apply(value));
                codec12.encode(buffer, getter12.apply(value));
                codec13.encode(buffer, getter13.apply(value));
                codec14.encode(buffer, getter14.apply(value));
            }

            @Override
            public @NotNull R decode(@NotNull ByteBuffer buffer) {
                var a1 = codec1.decode(buffer);
                var a2 = codec2.decode(buffer);
                var a3 = codec3.decode(buffer);
                var a4 = codec4.decode(buffer);
                var a5 = codec5.decode(buffer);
                var a6 = codec6.decode(buffer);
                var a7 = codec7.decode(buffer);
                var a8 = codec8.decode(buffer);
                var a9 = codec9.decode(buffer);
                var a10 = codec10.decode(buffer);
                var a11 = codec11.decode(buffer);
                var a12 = codec12.decode(buffer);
                var a13 = codec13.decode(buffer);
                var a14 = codec14.decode(buffer);
                return constructor.apply(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14);
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, R> StreamCodec<ByteBuffer, R> of(
        StreamCodec<ByteBuffer, A1> codec1,
        Function<R, A1> getter1,
        StreamCodec<ByteBuffer, A2> codec2,
        Function<R, A2> getter2,
        StreamCodec<ByteBuffer, A3> codec3,
        Function<R, A3> getter3,
        StreamCodec<ByteBuffer, A4> codec4,
        Function<R, A4> getter4,
        StreamCodec<ByteBuffer, A5> codec5,
        Function<R, A5> getter5,
        StreamCodec<ByteBuffer, A6> codec6,
        Function<R, A6> getter6,
        StreamCodec<ByteBuffer, A7> codec7,
        Function<R, A7> getter7,
        StreamCodec<ByteBuffer, A8> codec8,
        Function<R, A8> getter8,
        StreamCodec<ByteBuffer, A9> codec9,
        Function<R, A9> getter9,
        StreamCodec<ByteBuffer, A10> codec10,
        Function<R, A10> getter10,
        StreamCodec<ByteBuffer, A11> codec11,
        Function<R, A11> getter11,
        StreamCodec<ByteBuffer, A12> codec12,
        Function<R, A12> getter12,
        StreamCodec<ByteBuffer, A13> codec13,
        Function<R, A13> getter13,
        StreamCodec<ByteBuffer, A14> codec14,
        Function<R, A14> getter14,
        StreamCodec<ByteBuffer, A15> codec15,
        Function<R, A15> getter15,
        Function15<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public void encode(@NotNull ByteBuffer buffer, @NotNull R value) {
                codec1.encode(buffer, getter1.apply(value));
                codec2.encode(buffer, getter2.apply(value));
                codec3.encode(buffer, getter3.apply(value));
                codec4.encode(buffer, getter4.apply(value));
                codec5.encode(buffer, getter5.apply(value));
                codec6.encode(buffer, getter6.apply(value));
                codec7.encode(buffer, getter7.apply(value));
                codec8.encode(buffer, getter8.apply(value));
                codec9.encode(buffer, getter9.apply(value));
                codec10.encode(buffer, getter10.apply(value));
                codec11.encode(buffer, getter11.apply(value));
                codec12.encode(buffer, getter12.apply(value));
                codec13.encode(buffer, getter13.apply(value));
                codec14.encode(buffer, getter14.apply(value));
                codec15.encode(buffer, getter15.apply(value));
            }

            @Override
            public @NotNull R decode(@NotNull ByteBuffer buffer) {
                var a1 = codec1.decode(buffer);
                var a2 = codec2.decode(buffer);
                var a3 = codec3.decode(buffer);
                var a4 = codec4.decode(buffer);
                var a5 = codec5.decode(buffer);
                var a6 = codec6.decode(buffer);
                var a7 = codec7.decode(buffer);
                var a8 = codec8.decode(buffer);
                var a9 = codec9.decode(buffer);
                var a10 = codec10.decode(buffer);
                var a11 = codec11.decode(buffer);
                var a12 = codec12.decode(buffer);
                var a13 = codec13.decode(buffer);
                var a14 = codec14.decode(buffer);
                var a15 = codec15.decode(buffer);
                return constructor.apply(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15);
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, R> StreamCodec<ByteBuffer, R> of(
        StreamCodec<ByteBuffer, A1> codec1,
        Function<R, A1> getter1,
        StreamCodec<ByteBuffer, A2> codec2,
        Function<R, A2> getter2,
        StreamCodec<ByteBuffer, A3> codec3,
        Function<R, A3> getter3,
        StreamCodec<ByteBuffer, A4> codec4,
        Function<R, A4> getter4,
        StreamCodec<ByteBuffer, A5> codec5,
        Function<R, A5> getter5,
        StreamCodec<ByteBuffer, A6> codec6,
        Function<R, A6> getter6,
        StreamCodec<ByteBuffer, A7> codec7,
        Function<R, A7> getter7,
        StreamCodec<ByteBuffer, A8> codec8,
        Function<R, A8> getter8,
        StreamCodec<ByteBuffer, A9> codec9,
        Function<R, A9> getter9,
        StreamCodec<ByteBuffer, A10> codec10,
        Function<R, A10> getter10,
        StreamCodec<ByteBuffer, A11> codec11,
        Function<R, A11> getter11,
        StreamCodec<ByteBuffer, A12> codec12,
        Function<R, A12> getter12,
        StreamCodec<ByteBuffer, A13> codec13,
        Function<R, A13> getter13,
        StreamCodec<ByteBuffer, A14> codec14,
        Function<R, A14> getter14,
        StreamCodec<ByteBuffer, A15> codec15,
        Function<R, A15> getter15,
        StreamCodec<ByteBuffer, A16> codec16,
        Function<R, A16> getter16,
        Function16<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public void encode(@NotNull ByteBuffer buffer, @NotNull R value) {
                codec1.encode(buffer, getter1.apply(value));
                codec2.encode(buffer, getter2.apply(value));
                codec3.encode(buffer, getter3.apply(value));
                codec4.encode(buffer, getter4.apply(value));
                codec5.encode(buffer, getter5.apply(value));
                codec6.encode(buffer, getter6.apply(value));
                codec7.encode(buffer, getter7.apply(value));
                codec8.encode(buffer, getter8.apply(value));
                codec9.encode(buffer, getter9.apply(value));
                codec10.encode(buffer, getter10.apply(value));
                codec11.encode(buffer, getter11.apply(value));
                codec12.encode(buffer, getter12.apply(value));
                codec13.encode(buffer, getter13.apply(value));
                codec14.encode(buffer, getter14.apply(value));
                codec15.encode(buffer, getter15.apply(value));
                codec16.encode(buffer, getter16.apply(value));
            }

            @Override
            public @NotNull R decode(@NotNull ByteBuffer buffer) {
                var a1 = codec1.decode(buffer);
                var a2 = codec2.decode(buffer);
                var a3 = codec3.decode(buffer);
                var a4 = codec4.decode(buffer);
                var a5 = codec5.decode(buffer);
                var a6 = codec6.decode(buffer);
                var a7 = codec7.decode(buffer);
                var a8 = codec8.decode(buffer);
                var a9 = codec9.decode(buffer);
                var a10 = codec10.decode(buffer);
                var a11 = codec11.decode(buffer);
                var a12 = codec12.decode(buffer);
                var a13 = codec13.decode(buffer);
                var a14 = codec14.decode(buffer);
                var a15 = codec15.decode(buffer);
                var a16 = codec16.decode(buffer);
                return constructor.apply(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16);
            }
        };
    }
}
