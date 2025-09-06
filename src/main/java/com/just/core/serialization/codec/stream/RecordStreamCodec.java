package com.just.core.serialization.codec.stream;

import com.just.core.functional.function.Function;
import com.just.core.functional.function.Function10;
import com.just.core.functional.function.Function11;
import com.just.core.functional.function.Function12;
import com.just.core.functional.function.Function13;
import com.just.core.functional.function.Function14;
import com.just.core.functional.function.Function15;
import com.just.core.functional.function.Function16;
import com.just.core.functional.function.Function2;
import com.just.core.functional.function.Function3;
import com.just.core.functional.function.Function4;
import com.just.core.functional.function.Function5;
import com.just.core.functional.function.Function6;
import com.just.core.functional.function.Function7;
import com.just.core.functional.function.Function8;
import com.just.core.functional.function.Function9;
import org.jetbrains.annotations.NotNull;

import com.bvanseg.just.functional.function.*;
import com.just.core.serialization.codec.stream.schema.StreamCodecSchema;

public final class RecordStreamCodec {

    public static <A1, R> StreamCodec<R> of(
        StreamCodec<A1> codec1,
        Function<R, A1> getter1,
        Function<A1, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public <T> void encode(
                @NotNull StreamCodecSchema<T> streamCodecSchema,
                @NotNull T input,
                @NotNull R value
            ) {
                codec1.encode(streamCodecSchema, input, getter1.apply(value));
            }

            @Override
            public <T> @NotNull R decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
                var a1 = codec1.decode(streamCodecSchema, input);
                return constructor.apply(a1);
            }
        };
    }

    public static <A1, A2, R> StreamCodec<R> of(
        StreamCodec<A1> codec1,
        Function<R, A1> getter1,
        StreamCodec<A2> codec2,
        Function<R, A2> getter2,
        Function2<A1, A2, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public <T> void encode(
                @NotNull StreamCodecSchema<T> streamCodecSchema,
                @NotNull T input,
                @NotNull R value
            ) {
                codec1.encode(streamCodecSchema, input, getter1.apply(value));
                codec2.encode(streamCodecSchema, input, getter2.apply(value));
            }

            @Override
            public <T> @NotNull R decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
                var a1 = codec1.decode(streamCodecSchema, input);
                var a2 = codec2.decode(streamCodecSchema, input);
                return constructor.apply(a1, a2);
            }
        };
    }

    public static <A1, A2, A3, R> StreamCodec<R> of(
        StreamCodec<A1> codec1,
        Function<R, A1> getter1,
        StreamCodec<A2> codec2,
        Function<R, A2> getter2,
        StreamCodec<A3> codec3,
        Function<R, A3> getter3,
        Function3<A1, A2, A3, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public <T> void encode(
                @NotNull StreamCodecSchema<T> streamCodecSchema,
                @NotNull T input,
                @NotNull R value
            ) {
                codec1.encode(streamCodecSchema, input, getter1.apply(value));
                codec2.encode(streamCodecSchema, input, getter2.apply(value));
                codec3.encode(streamCodecSchema, input, getter3.apply(value));
            }

            @Override
            public <T> @NotNull R decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
                var a1 = codec1.decode(streamCodecSchema, input);
                var a2 = codec2.decode(streamCodecSchema, input);
                var a3 = codec3.decode(streamCodecSchema, input);
                return constructor.apply(a1, a2, a3);
            }
        };
    }

    public static <A1, A2, A3, A4, R> StreamCodec<R> of(
        StreamCodec<A1> codec1,
        Function<R, A1> getter1,
        StreamCodec<A2> codec2,
        Function<R, A2> getter2,
        StreamCodec<A3> codec3,
        Function<R, A3> getter3,
        StreamCodec<A4> codec4,
        Function<R, A4> getter4,
        Function4<A1, A2, A3, A4, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public <T> void encode(
                @NotNull StreamCodecSchema<T> streamCodecSchema,
                @NotNull T input,
                @NotNull R value
            ) {
                codec1.encode(streamCodecSchema, input, getter1.apply(value));
                codec2.encode(streamCodecSchema, input, getter2.apply(value));
                codec3.encode(streamCodecSchema, input, getter3.apply(value));
                codec4.encode(streamCodecSchema, input, getter4.apply(value));
            }

            @Override
            public <T> @NotNull R decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
                var a1 = codec1.decode(streamCodecSchema, input);
                var a2 = codec2.decode(streamCodecSchema, input);
                var a3 = codec3.decode(streamCodecSchema, input);
                var a4 = codec4.decode(streamCodecSchema, input);
                return constructor.apply(a1, a2, a3, a4);
            }
        };
    }

    public static <A1, A2, A3, A4, A5, R> StreamCodec<R> of(
        StreamCodec<A1> codec1,
        Function<R, A1> getter1,
        StreamCodec<A2> codec2,
        Function<R, A2> getter2,
        StreamCodec<A3> codec3,
        Function<R, A3> getter3,
        StreamCodec<A4> codec4,
        Function<R, A4> getter4,
        StreamCodec<A5> codec5,
        Function<R, A5> getter5,
        Function5<A1, A2, A3, A4, A5, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public <T> void encode(
                @NotNull StreamCodecSchema<T> streamCodecSchema,
                @NotNull T input,
                @NotNull R value
            ) {
                codec1.encode(streamCodecSchema, input, getter1.apply(value));
                codec2.encode(streamCodecSchema, input, getter2.apply(value));
                codec3.encode(streamCodecSchema, input, getter3.apply(value));
                codec4.encode(streamCodecSchema, input, getter4.apply(value));
                codec5.encode(streamCodecSchema, input, getter5.apply(value));
            }

            @Override
            public <T> @NotNull R decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
                var a1 = codec1.decode(streamCodecSchema, input);
                var a2 = codec2.decode(streamCodecSchema, input);
                var a3 = codec3.decode(streamCodecSchema, input);
                var a4 = codec4.decode(streamCodecSchema, input);
                var a5 = codec5.decode(streamCodecSchema, input);
                return constructor.apply(a1, a2, a3, a4, a5);
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, R> StreamCodec<R> of(
        StreamCodec<A1> codec1,
        Function<R, A1> getter1,
        StreamCodec<A2> codec2,
        Function<R, A2> getter2,
        StreamCodec<A3> codec3,
        Function<R, A3> getter3,
        StreamCodec<A4> codec4,
        Function<R, A4> getter4,
        StreamCodec<A5> codec5,
        Function<R, A5> getter5,
        StreamCodec<A6> codec6,
        Function<R, A6> getter6,
        Function6<A1, A2, A3, A4, A5, A6, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public <T> void encode(
                @NotNull StreamCodecSchema<T> streamCodecSchema,
                @NotNull T input,
                @NotNull R value
            ) {
                codec1.encode(streamCodecSchema, input, getter1.apply(value));
                codec2.encode(streamCodecSchema, input, getter2.apply(value));
                codec3.encode(streamCodecSchema, input, getter3.apply(value));
                codec4.encode(streamCodecSchema, input, getter4.apply(value));
                codec5.encode(streamCodecSchema, input, getter5.apply(value));
                codec6.encode(streamCodecSchema, input, getter6.apply(value));
            }

            @Override
            public <T> @NotNull R decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
                var a1 = codec1.decode(streamCodecSchema, input);
                var a2 = codec2.decode(streamCodecSchema, input);
                var a3 = codec3.decode(streamCodecSchema, input);
                var a4 = codec4.decode(streamCodecSchema, input);
                var a5 = codec5.decode(streamCodecSchema, input);
                var a6 = codec6.decode(streamCodecSchema, input);
                return constructor.apply(a1, a2, a3, a4, a5, a6);
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, A7, R> StreamCodec<R> of(
        StreamCodec<A1> codec1,
        Function<R, A1> getter1,
        StreamCodec<A2> codec2,
        Function<R, A2> getter2,
        StreamCodec<A3> codec3,
        Function<R, A3> getter3,
        StreamCodec<A4> codec4,
        Function<R, A4> getter4,
        StreamCodec<A5> codec5,
        Function<R, A5> getter5,
        StreamCodec<A6> codec6,
        Function<R, A6> getter6,
        StreamCodec<A7> codec7,
        Function<R, A7> getter7,
        Function7<A1, A2, A3, A4, A5, A6, A7, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public <T> void encode(
                @NotNull StreamCodecSchema<T> streamCodecSchema,
                @NotNull T input,
                @NotNull R value
            ) {
                codec1.encode(streamCodecSchema, input, getter1.apply(value));
                codec2.encode(streamCodecSchema, input, getter2.apply(value));
                codec3.encode(streamCodecSchema, input, getter3.apply(value));
                codec4.encode(streamCodecSchema, input, getter4.apply(value));
                codec5.encode(streamCodecSchema, input, getter5.apply(value));
                codec6.encode(streamCodecSchema, input, getter6.apply(value));
                codec7.encode(streamCodecSchema, input, getter7.apply(value));
            }

            @Override
            public <T> @NotNull R decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
                var a1 = codec1.decode(streamCodecSchema, input);
                var a2 = codec2.decode(streamCodecSchema, input);
                var a3 = codec3.decode(streamCodecSchema, input);
                var a4 = codec4.decode(streamCodecSchema, input);
                var a5 = codec5.decode(streamCodecSchema, input);
                var a6 = codec6.decode(streamCodecSchema, input);
                var a7 = codec7.decode(streamCodecSchema, input);
                return constructor.apply(a1, a2, a3, a4, a5, a6, a7);
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, A7, A8, R> StreamCodec<R> of(
        StreamCodec<A1> codec1,
        Function<R, A1> getter1,
        StreamCodec<A2> codec2,
        Function<R, A2> getter2,
        StreamCodec<A3> codec3,
        Function<R, A3> getter3,
        StreamCodec<A4> codec4,
        Function<R, A4> getter4,
        StreamCodec<A5> codec5,
        Function<R, A5> getter5,
        StreamCodec<A6> codec6,
        Function<R, A6> getter6,
        StreamCodec<A7> codec7,
        Function<R, A7> getter7,
        StreamCodec<A8> codec8,
        Function<R, A8> getter8,
        Function8<A1, A2, A3, A4, A5, A6, A7, A8, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public <T> void encode(
                @NotNull StreamCodecSchema<T> streamCodecSchema,
                @NotNull T input,
                @NotNull R value
            ) {
                codec1.encode(streamCodecSchema, input, getter1.apply(value));
                codec2.encode(streamCodecSchema, input, getter2.apply(value));
                codec3.encode(streamCodecSchema, input, getter3.apply(value));
                codec4.encode(streamCodecSchema, input, getter4.apply(value));
                codec5.encode(streamCodecSchema, input, getter5.apply(value));
                codec6.encode(streamCodecSchema, input, getter6.apply(value));
                codec7.encode(streamCodecSchema, input, getter7.apply(value));
                codec8.encode(streamCodecSchema, input, getter8.apply(value));
            }

            @Override
            public <T> @NotNull R decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
                var a1 = codec1.decode(streamCodecSchema, input);
                var a2 = codec2.decode(streamCodecSchema, input);
                var a3 = codec3.decode(streamCodecSchema, input);
                var a4 = codec4.decode(streamCodecSchema, input);
                var a5 = codec5.decode(streamCodecSchema, input);
                var a6 = codec6.decode(streamCodecSchema, input);
                var a7 = codec7.decode(streamCodecSchema, input);
                var a8 = codec8.decode(streamCodecSchema, input);
                return constructor.apply(a1, a2, a3, a4, a5, a6, a7, a8);
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, R> StreamCodec<R> of(
        StreamCodec<A1> codec1,
        Function<R, A1> getter1,
        StreamCodec<A2> codec2,
        Function<R, A2> getter2,
        StreamCodec<A3> codec3,
        Function<R, A3> getter3,
        StreamCodec<A4> codec4,
        Function<R, A4> getter4,
        StreamCodec<A5> codec5,
        Function<R, A5> getter5,
        StreamCodec<A6> codec6,
        Function<R, A6> getter6,
        StreamCodec<A7> codec7,
        Function<R, A7> getter7,
        StreamCodec<A8> codec8,
        Function<R, A8> getter8,
        StreamCodec<A9> codec9,
        Function<R, A9> getter9,
        Function9<A1, A2, A3, A4, A5, A6, A7, A8, A9, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public <T> void encode(
                @NotNull StreamCodecSchema<T> streamCodecSchema,
                @NotNull T input,
                @NotNull R value
            ) {
                codec1.encode(streamCodecSchema, input, getter1.apply(value));
                codec2.encode(streamCodecSchema, input, getter2.apply(value));
                codec3.encode(streamCodecSchema, input, getter3.apply(value));
                codec4.encode(streamCodecSchema, input, getter4.apply(value));
                codec5.encode(streamCodecSchema, input, getter5.apply(value));
                codec6.encode(streamCodecSchema, input, getter6.apply(value));
                codec7.encode(streamCodecSchema, input, getter7.apply(value));
                codec8.encode(streamCodecSchema, input, getter8.apply(value));
                codec9.encode(streamCodecSchema, input, getter9.apply(value));
            }

            @Override
            public <T> @NotNull R decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
                var a1 = codec1.decode(streamCodecSchema, input);
                var a2 = codec2.decode(streamCodecSchema, input);
                var a3 = codec3.decode(streamCodecSchema, input);
                var a4 = codec4.decode(streamCodecSchema, input);
                var a5 = codec5.decode(streamCodecSchema, input);
                var a6 = codec6.decode(streamCodecSchema, input);
                var a7 = codec7.decode(streamCodecSchema, input);
                var a8 = codec8.decode(streamCodecSchema, input);
                var a9 = codec9.decode(streamCodecSchema, input);
                return constructor.apply(a1, a2, a3, a4, a5, a6, a7, a8, a9);
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, R> StreamCodec<R> of(
        StreamCodec<A1> codec1,
        Function<R, A1> getter1,
        StreamCodec<A2> codec2,
        Function<R, A2> getter2,
        StreamCodec<A3> codec3,
        Function<R, A3> getter3,
        StreamCodec<A4> codec4,
        Function<R, A4> getter4,
        StreamCodec<A5> codec5,
        Function<R, A5> getter5,
        StreamCodec<A6> codec6,
        Function<R, A6> getter6,
        StreamCodec<A7> codec7,
        Function<R, A7> getter7,
        StreamCodec<A8> codec8,
        Function<R, A8> getter8,
        StreamCodec<A9> codec9,
        Function<R, A9> getter9,
        StreamCodec<A10> codec10,
        Function<R, A10> getter10,
        Function10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public <T> void encode(
                @NotNull StreamCodecSchema<T> streamCodecSchema,
                @NotNull T input,
                @NotNull R value
            ) {
                codec1.encode(streamCodecSchema, input, getter1.apply(value));
                codec2.encode(streamCodecSchema, input, getter2.apply(value));
                codec3.encode(streamCodecSchema, input, getter3.apply(value));
                codec4.encode(streamCodecSchema, input, getter4.apply(value));
                codec5.encode(streamCodecSchema, input, getter5.apply(value));
                codec6.encode(streamCodecSchema, input, getter6.apply(value));
                codec7.encode(streamCodecSchema, input, getter7.apply(value));
                codec8.encode(streamCodecSchema, input, getter8.apply(value));
                codec9.encode(streamCodecSchema, input, getter9.apply(value));
                codec10.encode(streamCodecSchema, input, getter10.apply(value));
            }

            @Override
            public <T> @NotNull R decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
                var a1 = codec1.decode(streamCodecSchema, input);
                var a2 = codec2.decode(streamCodecSchema, input);
                var a3 = codec3.decode(streamCodecSchema, input);
                var a4 = codec4.decode(streamCodecSchema, input);
                var a5 = codec5.decode(streamCodecSchema, input);
                var a6 = codec6.decode(streamCodecSchema, input);
                var a7 = codec7.decode(streamCodecSchema, input);
                var a8 = codec8.decode(streamCodecSchema, input);
                var a9 = codec9.decode(streamCodecSchema, input);
                var a10 = codec10.decode(streamCodecSchema, input);
                return constructor.apply(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10);
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, R> StreamCodec<R> of(
        StreamCodec<A1> codec1,
        Function<R, A1> getter1,
        StreamCodec<A2> codec2,
        Function<R, A2> getter2,
        StreamCodec<A3> codec3,
        Function<R, A3> getter3,
        StreamCodec<A4> codec4,
        Function<R, A4> getter4,
        StreamCodec<A5> codec5,
        Function<R, A5> getter5,
        StreamCodec<A6> codec6,
        Function<R, A6> getter6,
        StreamCodec<A7> codec7,
        Function<R, A7> getter7,
        StreamCodec<A8> codec8,
        Function<R, A8> getter8,
        StreamCodec<A9> codec9,
        Function<R, A9> getter9,
        StreamCodec<A10> codec10,
        Function<R, A10> getter10,
        StreamCodec<A11> codec11,
        Function<R, A11> getter11,
        Function11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public <T> void encode(
                @NotNull StreamCodecSchema<T> streamCodecSchema,
                @NotNull T input,
                @NotNull R value
            ) {
                codec1.encode(streamCodecSchema, input, getter1.apply(value));
                codec2.encode(streamCodecSchema, input, getter2.apply(value));
                codec3.encode(streamCodecSchema, input, getter3.apply(value));
                codec4.encode(streamCodecSchema, input, getter4.apply(value));
                codec5.encode(streamCodecSchema, input, getter5.apply(value));
                codec6.encode(streamCodecSchema, input, getter6.apply(value));
                codec7.encode(streamCodecSchema, input, getter7.apply(value));
                codec8.encode(streamCodecSchema, input, getter8.apply(value));
                codec9.encode(streamCodecSchema, input, getter9.apply(value));
                codec10.encode(streamCodecSchema, input, getter10.apply(value));
                codec11.encode(streamCodecSchema, input, getter11.apply(value));
            }

            @Override
            public <T> @NotNull R decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
                var a1 = codec1.decode(streamCodecSchema, input);
                var a2 = codec2.decode(streamCodecSchema, input);
                var a3 = codec3.decode(streamCodecSchema, input);
                var a4 = codec4.decode(streamCodecSchema, input);
                var a5 = codec5.decode(streamCodecSchema, input);
                var a6 = codec6.decode(streamCodecSchema, input);
                var a7 = codec7.decode(streamCodecSchema, input);
                var a8 = codec8.decode(streamCodecSchema, input);
                var a9 = codec9.decode(streamCodecSchema, input);
                var a10 = codec10.decode(streamCodecSchema, input);
                var a11 = codec11.decode(streamCodecSchema, input);
                return constructor.apply(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11);
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, R> StreamCodec<R> of(
        StreamCodec<A1> codec1,
        Function<R, A1> getter1,
        StreamCodec<A2> codec2,
        Function<R, A2> getter2,
        StreamCodec<A3> codec3,
        Function<R, A3> getter3,
        StreamCodec<A4> codec4,
        Function<R, A4> getter4,
        StreamCodec<A5> codec5,
        Function<R, A5> getter5,
        StreamCodec<A6> codec6,
        Function<R, A6> getter6,
        StreamCodec<A7> codec7,
        Function<R, A7> getter7,
        StreamCodec<A8> codec8,
        Function<R, A8> getter8,
        StreamCodec<A9> codec9,
        Function<R, A9> getter9,
        StreamCodec<A10> codec10,
        Function<R, A10> getter10,
        StreamCodec<A11> codec11,
        Function<R, A11> getter11,
        StreamCodec<A12> codec12,
        Function<R, A12> getter12,
        Function12<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public <T> void encode(
                @NotNull StreamCodecSchema<T> streamCodecSchema,
                @NotNull T input,
                @NotNull R value
            ) {
                codec1.encode(streamCodecSchema, input, getter1.apply(value));
                codec2.encode(streamCodecSchema, input, getter2.apply(value));
                codec3.encode(streamCodecSchema, input, getter3.apply(value));
                codec4.encode(streamCodecSchema, input, getter4.apply(value));
                codec5.encode(streamCodecSchema, input, getter5.apply(value));
                codec6.encode(streamCodecSchema, input, getter6.apply(value));
                codec7.encode(streamCodecSchema, input, getter7.apply(value));
                codec8.encode(streamCodecSchema, input, getter8.apply(value));
                codec9.encode(streamCodecSchema, input, getter9.apply(value));
                codec10.encode(streamCodecSchema, input, getter10.apply(value));
                codec11.encode(streamCodecSchema, input, getter11.apply(value));
                codec12.encode(streamCodecSchema, input, getter12.apply(value));
            }

            @Override
            public <T> @NotNull R decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
                var a1 = codec1.decode(streamCodecSchema, input);
                var a2 = codec2.decode(streamCodecSchema, input);
                var a3 = codec3.decode(streamCodecSchema, input);
                var a4 = codec4.decode(streamCodecSchema, input);
                var a5 = codec5.decode(streamCodecSchema, input);
                var a6 = codec6.decode(streamCodecSchema, input);
                var a7 = codec7.decode(streamCodecSchema, input);
                var a8 = codec8.decode(streamCodecSchema, input);
                var a9 = codec9.decode(streamCodecSchema, input);
                var a10 = codec10.decode(streamCodecSchema, input);
                var a11 = codec11.decode(streamCodecSchema, input);
                var a12 = codec12.decode(streamCodecSchema, input);
                return constructor.apply(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12);
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, R> StreamCodec<R> of(
        StreamCodec<A1> codec1,
        Function<R, A1> getter1,
        StreamCodec<A2> codec2,
        Function<R, A2> getter2,
        StreamCodec<A3> codec3,
        Function<R, A3> getter3,
        StreamCodec<A4> codec4,
        Function<R, A4> getter4,
        StreamCodec<A5> codec5,
        Function<R, A5> getter5,
        StreamCodec<A6> codec6,
        Function<R, A6> getter6,
        StreamCodec<A7> codec7,
        Function<R, A7> getter7,
        StreamCodec<A8> codec8,
        Function<R, A8> getter8,
        StreamCodec<A9> codec9,
        Function<R, A9> getter9,
        StreamCodec<A10> codec10,
        Function<R, A10> getter10,
        StreamCodec<A11> codec11,
        Function<R, A11> getter11,
        StreamCodec<A12> codec12,
        Function<R, A12> getter12,
        StreamCodec<A13> codec13,
        Function<R, A13> getter13,
        Function13<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public <T> void encode(
                @NotNull StreamCodecSchema<T> streamCodecSchema,
                @NotNull T input,
                @NotNull R value
            ) {
                codec1.encode(streamCodecSchema, input, getter1.apply(value));
                codec2.encode(streamCodecSchema, input, getter2.apply(value));
                codec3.encode(streamCodecSchema, input, getter3.apply(value));
                codec4.encode(streamCodecSchema, input, getter4.apply(value));
                codec5.encode(streamCodecSchema, input, getter5.apply(value));
                codec6.encode(streamCodecSchema, input, getter6.apply(value));
                codec7.encode(streamCodecSchema, input, getter7.apply(value));
                codec8.encode(streamCodecSchema, input, getter8.apply(value));
                codec9.encode(streamCodecSchema, input, getter9.apply(value));
                codec10.encode(streamCodecSchema, input, getter10.apply(value));
                codec11.encode(streamCodecSchema, input, getter11.apply(value));
                codec12.encode(streamCodecSchema, input, getter12.apply(value));
                codec13.encode(streamCodecSchema, input, getter13.apply(value));
            }

            @Override
            public <T> @NotNull R decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
                var a1 = codec1.decode(streamCodecSchema, input);
                var a2 = codec2.decode(streamCodecSchema, input);
                var a3 = codec3.decode(streamCodecSchema, input);
                var a4 = codec4.decode(streamCodecSchema, input);
                var a5 = codec5.decode(streamCodecSchema, input);
                var a6 = codec6.decode(streamCodecSchema, input);
                var a7 = codec7.decode(streamCodecSchema, input);
                var a8 = codec8.decode(streamCodecSchema, input);
                var a9 = codec9.decode(streamCodecSchema, input);
                var a10 = codec10.decode(streamCodecSchema, input);
                var a11 = codec11.decode(streamCodecSchema, input);
                var a12 = codec12.decode(streamCodecSchema, input);
                var a13 = codec13.decode(streamCodecSchema, input);
                return constructor.apply(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13);
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, R> StreamCodec<R> of(
        StreamCodec<A1> codec1,
        Function<R, A1> getter1,
        StreamCodec<A2> codec2,
        Function<R, A2> getter2,
        StreamCodec<A3> codec3,
        Function<R, A3> getter3,
        StreamCodec<A4> codec4,
        Function<R, A4> getter4,
        StreamCodec<A5> codec5,
        Function<R, A5> getter5,
        StreamCodec<A6> codec6,
        Function<R, A6> getter6,
        StreamCodec<A7> codec7,
        Function<R, A7> getter7,
        StreamCodec<A8> codec8,
        Function<R, A8> getter8,
        StreamCodec<A9> codec9,
        Function<R, A9> getter9,
        StreamCodec<A10> codec10,
        Function<R, A10> getter10,
        StreamCodec<A11> codec11,
        Function<R, A11> getter11,
        StreamCodec<A12> codec12,
        Function<R, A12> getter12,
        StreamCodec<A13> codec13,
        Function<R, A13> getter13,
        StreamCodec<A14> codec14,
        Function<R, A14> getter14,
        Function14<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public <T> void encode(
                @NotNull StreamCodecSchema<T> streamCodecSchema,
                @NotNull T input,
                @NotNull R value
            ) {
                codec1.encode(streamCodecSchema, input, getter1.apply(value));
                codec2.encode(streamCodecSchema, input, getter2.apply(value));
                codec3.encode(streamCodecSchema, input, getter3.apply(value));
                codec4.encode(streamCodecSchema, input, getter4.apply(value));
                codec5.encode(streamCodecSchema, input, getter5.apply(value));
                codec6.encode(streamCodecSchema, input, getter6.apply(value));
                codec7.encode(streamCodecSchema, input, getter7.apply(value));
                codec8.encode(streamCodecSchema, input, getter8.apply(value));
                codec9.encode(streamCodecSchema, input, getter9.apply(value));
                codec10.encode(streamCodecSchema, input, getter10.apply(value));
                codec11.encode(streamCodecSchema, input, getter11.apply(value));
                codec12.encode(streamCodecSchema, input, getter12.apply(value));
                codec13.encode(streamCodecSchema, input, getter13.apply(value));
                codec14.encode(streamCodecSchema, input, getter14.apply(value));
            }

            @Override
            public <T> @NotNull R decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
                var a1 = codec1.decode(streamCodecSchema, input);
                var a2 = codec2.decode(streamCodecSchema, input);
                var a3 = codec3.decode(streamCodecSchema, input);
                var a4 = codec4.decode(streamCodecSchema, input);
                var a5 = codec5.decode(streamCodecSchema, input);
                var a6 = codec6.decode(streamCodecSchema, input);
                var a7 = codec7.decode(streamCodecSchema, input);
                var a8 = codec8.decode(streamCodecSchema, input);
                var a9 = codec9.decode(streamCodecSchema, input);
                var a10 = codec10.decode(streamCodecSchema, input);
                var a11 = codec11.decode(streamCodecSchema, input);
                var a12 = codec12.decode(streamCodecSchema, input);
                var a13 = codec13.decode(streamCodecSchema, input);
                var a14 = codec14.decode(streamCodecSchema, input);
                return constructor.apply(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14);
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, R> StreamCodec<R> of(
        StreamCodec<A1> codec1,
        Function<R, A1> getter1,
        StreamCodec<A2> codec2,
        Function<R, A2> getter2,
        StreamCodec<A3> codec3,
        Function<R, A3> getter3,
        StreamCodec<A4> codec4,
        Function<R, A4> getter4,
        StreamCodec<A5> codec5,
        Function<R, A5> getter5,
        StreamCodec<A6> codec6,
        Function<R, A6> getter6,
        StreamCodec<A7> codec7,
        Function<R, A7> getter7,
        StreamCodec<A8> codec8,
        Function<R, A8> getter8,
        StreamCodec<A9> codec9,
        Function<R, A9> getter9,
        StreamCodec<A10> codec10,
        Function<R, A10> getter10,
        StreamCodec<A11> codec11,
        Function<R, A11> getter11,
        StreamCodec<A12> codec12,
        Function<R, A12> getter12,
        StreamCodec<A13> codec13,
        Function<R, A13> getter13,
        StreamCodec<A14> codec14,
        Function<R, A14> getter14,
        StreamCodec<A15> codec15,
        Function<R, A15> getter15,
        Function15<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public <T> void encode(
                @NotNull StreamCodecSchema<T> streamCodecSchema,
                @NotNull T input,
                @NotNull R value
            ) {
                codec1.encode(streamCodecSchema, input, getter1.apply(value));
                codec2.encode(streamCodecSchema, input, getter2.apply(value));
                codec3.encode(streamCodecSchema, input, getter3.apply(value));
                codec4.encode(streamCodecSchema, input, getter4.apply(value));
                codec5.encode(streamCodecSchema, input, getter5.apply(value));
                codec6.encode(streamCodecSchema, input, getter6.apply(value));
                codec7.encode(streamCodecSchema, input, getter7.apply(value));
                codec8.encode(streamCodecSchema, input, getter8.apply(value));
                codec9.encode(streamCodecSchema, input, getter9.apply(value));
                codec10.encode(streamCodecSchema, input, getter10.apply(value));
                codec11.encode(streamCodecSchema, input, getter11.apply(value));
                codec12.encode(streamCodecSchema, input, getter12.apply(value));
                codec13.encode(streamCodecSchema, input, getter13.apply(value));
                codec14.encode(streamCodecSchema, input, getter14.apply(value));
                codec15.encode(streamCodecSchema, input, getter15.apply(value));
            }

            @Override
            public <T> @NotNull R decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
                var a1 = codec1.decode(streamCodecSchema, input);
                var a2 = codec2.decode(streamCodecSchema, input);
                var a3 = codec3.decode(streamCodecSchema, input);
                var a4 = codec4.decode(streamCodecSchema, input);
                var a5 = codec5.decode(streamCodecSchema, input);
                var a6 = codec6.decode(streamCodecSchema, input);
                var a7 = codec7.decode(streamCodecSchema, input);
                var a8 = codec8.decode(streamCodecSchema, input);
                var a9 = codec9.decode(streamCodecSchema, input);
                var a10 = codec10.decode(streamCodecSchema, input);
                var a11 = codec11.decode(streamCodecSchema, input);
                var a12 = codec12.decode(streamCodecSchema, input);
                var a13 = codec13.decode(streamCodecSchema, input);
                var a14 = codec14.decode(streamCodecSchema, input);
                var a15 = codec15.decode(streamCodecSchema, input);
                return constructor.apply(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15);
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, R> StreamCodec<R> of(
        StreamCodec<A1> codec1,
        Function<R, A1> getter1,
        StreamCodec<A2> codec2,
        Function<R, A2> getter2,
        StreamCodec<A3> codec3,
        Function<R, A3> getter3,
        StreamCodec<A4> codec4,
        Function<R, A4> getter4,
        StreamCodec<A5> codec5,
        Function<R, A5> getter5,
        StreamCodec<A6> codec6,
        Function<R, A6> getter6,
        StreamCodec<A7> codec7,
        Function<R, A7> getter7,
        StreamCodec<A8> codec8,
        Function<R, A8> getter8,
        StreamCodec<A9> codec9,
        Function<R, A9> getter9,
        StreamCodec<A10> codec10,
        Function<R, A10> getter10,
        StreamCodec<A11> codec11,
        Function<R, A11> getter11,
        StreamCodec<A12> codec12,
        Function<R, A12> getter12,
        StreamCodec<A13> codec13,
        Function<R, A13> getter13,
        StreamCodec<A14> codec14,
        Function<R, A14> getter14,
        StreamCodec<A15> codec15,
        Function<R, A15> getter15,
        StreamCodec<A16> codec16,
        Function<R, A16> getter16,
        Function16<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, R> constructor
    ) {
        return new StreamCodec<>() {

            @Override
            public <T> void encode(
                @NotNull StreamCodecSchema<T> streamCodecSchema,
                @NotNull T input,
                @NotNull R value
            ) {
                codec1.encode(streamCodecSchema, input, getter1.apply(value));
                codec2.encode(streamCodecSchema, input, getter2.apply(value));
                codec3.encode(streamCodecSchema, input, getter3.apply(value));
                codec4.encode(streamCodecSchema, input, getter4.apply(value));
                codec5.encode(streamCodecSchema, input, getter5.apply(value));
                codec6.encode(streamCodecSchema, input, getter6.apply(value));
                codec7.encode(streamCodecSchema, input, getter7.apply(value));
                codec8.encode(streamCodecSchema, input, getter8.apply(value));
                codec9.encode(streamCodecSchema, input, getter9.apply(value));
                codec10.encode(streamCodecSchema, input, getter10.apply(value));
                codec11.encode(streamCodecSchema, input, getter11.apply(value));
                codec12.encode(streamCodecSchema, input, getter12.apply(value));
                codec13.encode(streamCodecSchema, input, getter13.apply(value));
                codec14.encode(streamCodecSchema, input, getter14.apply(value));
                codec15.encode(streamCodecSchema, input, getter15.apply(value));
                codec16.encode(streamCodecSchema, input, getter16.apply(value));
            }

            @Override
            public <T> @NotNull R decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
                var a1 = codec1.decode(streamCodecSchema, input);
                var a2 = codec2.decode(streamCodecSchema, input);
                var a3 = codec3.decode(streamCodecSchema, input);
                var a4 = codec4.decode(streamCodecSchema, input);
                var a5 = codec5.decode(streamCodecSchema, input);
                var a6 = codec6.decode(streamCodecSchema, input);
                var a7 = codec7.decode(streamCodecSchema, input);
                var a8 = codec8.decode(streamCodecSchema, input);
                var a9 = codec9.decode(streamCodecSchema, input);
                var a10 = codec10.decode(streamCodecSchema, input);
                var a11 = codec11.decode(streamCodecSchema, input);
                var a12 = codec12.decode(streamCodecSchema, input);
                var a13 = codec13.decode(streamCodecSchema, input);
                var a14 = codec14.decode(streamCodecSchema, input);
                var a15 = codec15.decode(streamCodecSchema, input);
                var a16 = codec16.decode(streamCodecSchema, input);
                return constructor.apply(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16);
            }
        };
    }
}
