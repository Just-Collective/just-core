package com.just.core.serialization.codec;

import com.bvanseg.just.functional.function.*;
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
import com.just.core.functional.result.Result;
import com.just.core.serialization.codec.schema.CodecSchema;

public class RecordCodec {

    private static <R, A, T> T encodeField(CodecSchema<T> schema, T map, FieldCodec<R, A> field, R value) {
        return schema.createField(map, field.fieldName(), field.codec().encode(schema, field.getter().apply(value)));
    }

    private static <R, A, T> Result<A, T> decodeField(CodecSchema<T> schema, T input, FieldCodec<R, A> field) {
        return schema.getField(input, field.fieldName()).andThen(val -> field.codec().decode(schema, val));
    }

    public static <A1, R> Codec<R> of(FieldCodec<R, A1> f1, Function<A1, R> constructor) {
        return new Codec<>() {

            @Override
            public <T> T encode(CodecSchema<T> codecSchema, R value) {
                var map = codecSchema.emptyMap();
                map = encodeField(codecSchema, map, f1, value);
                return map;
            }

            @Override
            public <T> Result<R, T> decode(CodecSchema<T> codecSchema, T input) {
                return decodeField(codecSchema, input, f1)
                    .map(constructor);
            }
        };
    }

    public static <A1, A2, R> Codec<R> of(
        FieldCodec<R, A1> f1,
        FieldCodec<R, A2> f2,
        Function2<A1, A2, R> constructor
    ) {
        return new Codec<>() {

            @Override
            public <T> T encode(CodecSchema<T> codecSchema, R value) {
                var map = codecSchema.emptyMap();
                map = encodeField(codecSchema, map, f1, value);
                map = encodeField(codecSchema, map, f2, value);
                return map;
            }

            @Override
            public <T> Result<R, T> decode(CodecSchema<T> codecSchema, T input) {
                return decodeField(codecSchema, input, f1)
                    .andThen(
                        a1 -> decodeField(codecSchema, input, f2)
                            .map(a2 -> constructor.apply(a1, a2))
                    );
            }
        };
    }

    public static <A1, A2, A3, R> Codec<R> of(
        FieldCodec<R, A1> f1,
        FieldCodec<R, A2> f2,
        FieldCodec<R, A3> f3,
        Function3<A1, A2, A3, R> constructor
    ) {
        return new Codec<>() {

            @Override
            public <T> T encode(CodecSchema<T> codecSchema, R value) {
                var map = codecSchema.emptyMap();
                map = encodeField(codecSchema, map, f1, value);
                map = encodeField(codecSchema, map, f2, value);
                map = encodeField(codecSchema, map, f3, value);
                return map;
            }

            @Override
            public <T> Result<R, T> decode(CodecSchema<T> codecSchema, T input) {
                return decodeField(codecSchema, input, f1)
                    .andThen(
                        a1 -> decodeField(codecSchema, input, f2)
                            .andThen(
                                a2 -> decodeField(codecSchema, input, f3)
                                    .map(a3 -> constructor.apply(a1, a2, a3))
                            )
                    );
            }
        };
    }

    public static <A1, A2, A3, A4, R> Codec<R> of(
        FieldCodec<R, A1> f1,
        FieldCodec<R, A2> f2,
        FieldCodec<R, A3> f3,
        FieldCodec<R, A4> f4,
        Function4<A1, A2, A3, A4, R> constructor
    ) {
        return new Codec<>() {

            @Override
            public <T> T encode(CodecSchema<T> codecSchema, R value) {
                var map = codecSchema.emptyMap();
                map = encodeField(codecSchema, map, f1, value);
                map = encodeField(codecSchema, map, f2, value);
                map = encodeField(codecSchema, map, f3, value);
                map = encodeField(codecSchema, map, f4, value);
                return map;
            }

            @Override
            public <T> Result<R, T> decode(CodecSchema<T> codecSchema, T input) {
                return decodeField(codecSchema, input, f1)
                    .andThen(
                        a1 -> decodeField(codecSchema, input, f2)
                            .andThen(
                                a2 -> decodeField(codecSchema, input, f3)
                                    .andThen(
                                        a3 -> decodeField(codecSchema, input, f4)
                                            .map(a4 -> constructor.apply(a1, a2, a3, a4))
                                    )
                            )
                    );
            }
        };
    }

    public static <A1, A2, A3, A4, A5, R> Codec<R> of(
        FieldCodec<R, A1> f1,
        FieldCodec<R, A2> f2,
        FieldCodec<R, A3> f3,
        FieldCodec<R, A4> f4,
        FieldCodec<R, A5> f5,
        Function5<A1, A2, A3, A4, A5, R> constructor
    ) {
        return new Codec<>() {

            @Override
            public <T> T encode(CodecSchema<T> codecSchema, R value) {
                var map = codecSchema.emptyMap();
                map = encodeField(codecSchema, map, f1, value);
                map = encodeField(codecSchema, map, f2, value);
                map = encodeField(codecSchema, map, f3, value);
                map = encodeField(codecSchema, map, f4, value);
                map = encodeField(codecSchema, map, f5, value);
                return map;
            }

            @Override
            public <T> Result<R, T> decode(CodecSchema<T> codecSchema, T input) {
                return decodeField(codecSchema, input, f1)
                    .andThen(
                        a1 -> decodeField(codecSchema, input, f2)
                            .andThen(
                                a2 -> decodeField(codecSchema, input, f3)
                                    .andThen(
                                        a3 -> decodeField(codecSchema, input, f4)
                                            .andThen(
                                                a4 -> decodeField(codecSchema, input, f5)
                                                    .map(a5 -> constructor.apply(a1, a2, a3, a4, a5))
                                            )
                                    )
                            )
                    );
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, R> Codec<R> of(
        FieldCodec<R, A1> f1,
        FieldCodec<R, A2> f2,
        FieldCodec<R, A3> f3,
        FieldCodec<R, A4> f4,
        FieldCodec<R, A5> f5,
        FieldCodec<R, A6> f6,
        Function6<A1, A2, A3, A4, A5, A6, R> constructor
    ) {
        return new Codec<>() {

            @Override
            public <T> T encode(CodecSchema<T> codecSchema, R value) {
                var map = codecSchema.emptyMap();
                map = encodeField(codecSchema, map, f1, value);
                map = encodeField(codecSchema, map, f2, value);
                map = encodeField(codecSchema, map, f3, value);
                map = encodeField(codecSchema, map, f4, value);
                map = encodeField(codecSchema, map, f5, value);
                map = encodeField(codecSchema, map, f6, value);
                return map;
            }

            @Override
            public <T> Result<R, T> decode(CodecSchema<T> codecSchema, T input) {
                return decodeField(codecSchema, input, f1)
                    .andThen(
                        a1 -> decodeField(codecSchema, input, f2)
                            .andThen(
                                a2 -> decodeField(codecSchema, input, f3)
                                    .andThen(
                                        a3 -> decodeField(codecSchema, input, f4)
                                            .andThen(
                                                a4 -> decodeField(codecSchema, input, f5)
                                                    .andThen(
                                                        a5 -> decodeField(codecSchema, input, f6)
                                                            .map(a6 -> constructor.apply(a1, a2, a3, a4, a5, a6))
                                                    )
                                            )
                                    )
                            )
                    );
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, A7, R> Codec<R> of(
        FieldCodec<R, A1> f1,
        FieldCodec<R, A2> f2,
        FieldCodec<R, A3> f3,
        FieldCodec<R, A4> f4,
        FieldCodec<R, A5> f5,
        FieldCodec<R, A6> f6,
        FieldCodec<R, A7> f7,
        Function7<A1, A2, A3, A4, A5, A6, A7, R> constructor
    ) {
        return new Codec<>() {

            @Override
            public <T> T encode(CodecSchema<T> codecSchema, R value) {
                var map = codecSchema.emptyMap();
                map = encodeField(codecSchema, map, f1, value);
                map = encodeField(codecSchema, map, f2, value);
                map = encodeField(codecSchema, map, f3, value);
                map = encodeField(codecSchema, map, f4, value);
                map = encodeField(codecSchema, map, f5, value);
                map = encodeField(codecSchema, map, f6, value);
                map = encodeField(codecSchema, map, f7, value);
                return map;
            }

            @Override
            public <T> Result<R, T> decode(CodecSchema<T> codecSchema, T input) {
                return decodeField(codecSchema, input, f1)
                    .andThen(
                        a1 -> decodeField(codecSchema, input, f2)
                            .andThen(
                                a2 -> decodeField(codecSchema, input, f3)
                                    .andThen(
                                        a3 -> decodeField(codecSchema, input, f4)
                                            .andThen(
                                                a4 -> decodeField(codecSchema, input, f5)
                                                    .andThen(
                                                        a5 -> decodeField(codecSchema, input, f6)
                                                            .andThen(
                                                                a6 -> decodeField(codecSchema, input, f7)
                                                                    .map(
                                                                        a7 -> constructor.apply(
                                                                            a1,
                                                                            a2,
                                                                            a3,
                                                                            a4,
                                                                            a5,
                                                                            a6,
                                                                            a7
                                                                        )
                                                                    )
                                                            )
                                                    )
                                            )
                                    )
                            )
                    );
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, A7, A8, R> Codec<R> of(
        FieldCodec<R, A1> f1,
        FieldCodec<R, A2> f2,
        FieldCodec<R, A3> f3,
        FieldCodec<R, A4> f4,
        FieldCodec<R, A5> f5,
        FieldCodec<R, A6> f6,
        FieldCodec<R, A7> f7,
        FieldCodec<R, A8> f8,
        Function8<A1, A2, A3, A4, A5, A6, A7, A8, R> constructor
    ) {
        return new Codec<>() {

            @Override
            public <T> T encode(CodecSchema<T> codecSchema, R value) {
                var map = codecSchema.emptyMap();
                map = encodeField(codecSchema, map, f1, value);
                map = encodeField(codecSchema, map, f2, value);
                map = encodeField(codecSchema, map, f3, value);
                map = encodeField(codecSchema, map, f4, value);
                map = encodeField(codecSchema, map, f5, value);
                map = encodeField(codecSchema, map, f6, value);
                map = encodeField(codecSchema, map, f7, value);
                map = encodeField(codecSchema, map, f8, value);
                return map;
            }

            @Override
            public <T> Result<R, T> decode(CodecSchema<T> codecSchema, T input) {
                return decodeField(codecSchema, input, f1)
                    .andThen(
                        a1 -> decodeField(codecSchema, input, f2)
                            .andThen(
                                a2 -> decodeField(codecSchema, input, f3)
                                    .andThen(
                                        a3 -> decodeField(codecSchema, input, f4)
                                            .andThen(
                                                a4 -> decodeField(codecSchema, input, f5)
                                                    .andThen(
                                                        a5 -> decodeField(codecSchema, input, f6)
                                                            .andThen(
                                                                a6 -> decodeField(codecSchema, input, f7)
                                                                    .andThen(
                                                                        a7 -> decodeField(codecSchema, input, f8)
                                                                            .map(
                                                                                a8 -> constructor.apply(
                                                                                    a1,
                                                                                    a2,
                                                                                    a3,
                                                                                    a4,
                                                                                    a5,
                                                                                    a6,
                                                                                    a7,
                                                                                    a8
                                                                                )
                                                                            )
                                                                    )
                                                            )
                                                    )
                                            )
                                    )
                            )
                    );
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, R> Codec<R> of(
        FieldCodec<R, A1> f1,
        FieldCodec<R, A2> f2,
        FieldCodec<R, A3> f3,
        FieldCodec<R, A4> f4,
        FieldCodec<R, A5> f5,
        FieldCodec<R, A6> f6,
        FieldCodec<R, A7> f7,
        FieldCodec<R, A8> f8,
        FieldCodec<R, A9> f9,
        Function9<A1, A2, A3, A4, A5, A6, A7, A8, A9, R> constructor
    ) {
        return new Codec<>() {

            @Override
            public <T> T encode(CodecSchema<T> codecSchema, R value) {
                var map = codecSchema.emptyMap();
                map = encodeField(codecSchema, map, f1, value);
                map = encodeField(codecSchema, map, f2, value);
                map = encodeField(codecSchema, map, f3, value);
                map = encodeField(codecSchema, map, f4, value);
                map = encodeField(codecSchema, map, f5, value);
                map = encodeField(codecSchema, map, f6, value);
                map = encodeField(codecSchema, map, f7, value);
                map = encodeField(codecSchema, map, f8, value);
                map = encodeField(codecSchema, map, f9, value);
                return map;
            }

            @Override
            public <T> Result<R, T> decode(CodecSchema<T> codecSchema, T input) {
                return decodeField(codecSchema, input, f1)
                    .andThen(
                        a1 -> decodeField(codecSchema, input, f2)
                            .andThen(
                                a2 -> decodeField(codecSchema, input, f3)
                                    .andThen(
                                        a3 -> decodeField(codecSchema, input, f4)
                                            .andThen(
                                                a4 -> decodeField(codecSchema, input, f5)
                                                    .andThen(
                                                        a5 -> decodeField(codecSchema, input, f6)
                                                            .andThen(
                                                                a6 -> decodeField(codecSchema, input, f7)
                                                                    .andThen(
                                                                        a7 -> decodeField(codecSchema, input, f8)
                                                                            .andThen(
                                                                                a8 -> decodeField(
                                                                                    codecSchema,
                                                                                    input,
                                                                                    f9
                                                                                )
                                                                                    .map(
                                                                                        a9 -> constructor.apply(
                                                                                            a1,
                                                                                            a2,
                                                                                            a3,
                                                                                            a4,
                                                                                            a5,
                                                                                            a6,
                                                                                            a7,
                                                                                            a8,
                                                                                            a9
                                                                                        )
                                                                                    )
                                                                            )
                                                                    )
                                                            )
                                                    )
                                            )
                                    )
                            )
                    );
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, R> Codec<R> of(
        FieldCodec<R, A1> f1,
        FieldCodec<R, A2> f2,
        FieldCodec<R, A3> f3,
        FieldCodec<R, A4> f4,
        FieldCodec<R, A5> f5,
        FieldCodec<R, A6> f6,
        FieldCodec<R, A7> f7,
        FieldCodec<R, A8> f8,
        FieldCodec<R, A9> f9,
        FieldCodec<R, A10> f10,
        Function10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, R> constructor
    ) {
        return new Codec<>() {

            @Override
            public <T> T encode(CodecSchema<T> codecSchema, R value) {
                var map = codecSchema.emptyMap();
                map = encodeField(codecSchema, map, f1, value);
                map = encodeField(codecSchema, map, f2, value);
                map = encodeField(codecSchema, map, f3, value);
                map = encodeField(codecSchema, map, f4, value);
                map = encodeField(codecSchema, map, f5, value);
                map = encodeField(codecSchema, map, f6, value);
                map = encodeField(codecSchema, map, f7, value);
                map = encodeField(codecSchema, map, f8, value);
                map = encodeField(codecSchema, map, f9, value);
                map = encodeField(codecSchema, map, f10, value);
                return map;
            }

            @Override
            public <T> Result<R, T> decode(CodecSchema<T> codecSchema, T input) {
                return decodeField(codecSchema, input, f1)
                    .andThen(
                        a1 -> decodeField(codecSchema, input, f2)
                            .andThen(
                                a2 -> decodeField(codecSchema, input, f3)
                                    .andThen(
                                        a3 -> decodeField(codecSchema, input, f4)
                                            .andThen(
                                                a4 -> decodeField(codecSchema, input, f5)
                                                    .andThen(
                                                        a5 -> decodeField(codecSchema, input, f6)
                                                            .andThen(
                                                                a6 -> decodeField(codecSchema, input, f7)
                                                                    .andThen(
                                                                        a7 -> decodeField(codecSchema, input, f8)
                                                                            .andThen(
                                                                                a8 -> decodeField(
                                                                                    codecSchema,
                                                                                    input,
                                                                                    f9
                                                                                )
                                                                                    .andThen(
                                                                                        a9 -> decodeField(
                                                                                            codecSchema,
                                                                                            input,
                                                                                            f10
                                                                                        )
                                                                                            .map(
                                                                                                a10 -> constructor
                                                                                                    .apply(
                                                                                                        a1,
                                                                                                        a2,
                                                                                                        a3,
                                                                                                        a4,
                                                                                                        a5,
                                                                                                        a6,
                                                                                                        a7,
                                                                                                        a8,
                                                                                                        a9,
                                                                                                        a10
                                                                                                    )
                                                                                            )
                                                                                    )
                                                                            )
                                                                    )
                                                            )
                                                    )
                                            )
                                    )
                            )
                    );
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, R> Codec<R> of(
        FieldCodec<R, A1> f1,
        FieldCodec<R, A2> f2,
        FieldCodec<R, A3> f3,
        FieldCodec<R, A4> f4,
        FieldCodec<R, A5> f5,
        FieldCodec<R, A6> f6,
        FieldCodec<R, A7> f7,
        FieldCodec<R, A8> f8,
        FieldCodec<R, A9> f9,
        FieldCodec<R, A10> f10,
        FieldCodec<R, A11> f11,
        Function11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, R> constructor
    ) {
        return new Codec<>() {

            @Override
            public <T> T encode(CodecSchema<T> codecSchema, R value) {
                var map = codecSchema.emptyMap();
                map = encodeField(codecSchema, map, f1, value);
                map = encodeField(codecSchema, map, f2, value);
                map = encodeField(codecSchema, map, f3, value);
                map = encodeField(codecSchema, map, f4, value);
                map = encodeField(codecSchema, map, f5, value);
                map = encodeField(codecSchema, map, f6, value);
                map = encodeField(codecSchema, map, f7, value);
                map = encodeField(codecSchema, map, f8, value);
                map = encodeField(codecSchema, map, f9, value);
                map = encodeField(codecSchema, map, f10, value);
                map = encodeField(codecSchema, map, f11, value);
                return map;
            }

            @Override
            public <T> Result<R, T> decode(CodecSchema<T> codecSchema, T input) {
                return decodeField(codecSchema, input, f1)
                    .andThen(
                        a1 -> decodeField(codecSchema, input, f2)
                            .andThen(
                                a2 -> decodeField(codecSchema, input, f3)
                                    .andThen(
                                        a3 -> decodeField(codecSchema, input, f4)
                                            .andThen(
                                                a4 -> decodeField(codecSchema, input, f5)
                                                    .andThen(
                                                        a5 -> decodeField(codecSchema, input, f6)
                                                            .andThen(
                                                                a6 -> decodeField(codecSchema, input, f7)
                                                                    .andThen(
                                                                        a7 -> decodeField(codecSchema, input, f8)
                                                                            .andThen(
                                                                                a8 -> decodeField(
                                                                                    codecSchema,
                                                                                    input,
                                                                                    f9
                                                                                )
                                                                                    .andThen(
                                                                                        a9 -> decodeField(
                                                                                            codecSchema,
                                                                                            input,
                                                                                            f10
                                                                                        )
                                                                                            .andThen(
                                                                                                a10 -> decodeField(
                                                                                                    codecSchema,
                                                                                                    input,
                                                                                                    f11
                                                                                                )
                                                                                                    .map(
                                                                                                        a11 -> constructor
                                                                                                            .apply(
                                                                                                                a1,
                                                                                                                a2,
                                                                                                                a3,
                                                                                                                a4,
                                                                                                                a5,
                                                                                                                a6,
                                                                                                                a7,
                                                                                                                a8,
                                                                                                                a9,
                                                                                                                a10,
                                                                                                                a11
                                                                                                            )
                                                                                                    )
                                                                                            )
                                                                                    )
                                                                            )
                                                                    )
                                                            )
                                                    )
                                            )
                                    )
                            )
                    );
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, R> Codec<R> of(
        FieldCodec<R, A1> f1,
        FieldCodec<R, A2> f2,
        FieldCodec<R, A3> f3,
        FieldCodec<R, A4> f4,
        FieldCodec<R, A5> f5,
        FieldCodec<R, A6> f6,
        FieldCodec<R, A7> f7,
        FieldCodec<R, A8> f8,
        FieldCodec<R, A9> f9,
        FieldCodec<R, A10> f10,
        FieldCodec<R, A11> f11,
        FieldCodec<R, A12> f12,
        Function12<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, R> constructor
    ) {
        return new Codec<>() {

            @Override
            public <T> T encode(CodecSchema<T> codecSchema, R value) {
                var map = codecSchema.emptyMap();
                map = encodeField(codecSchema, map, f1, value);
                map = encodeField(codecSchema, map, f2, value);
                map = encodeField(codecSchema, map, f3, value);
                map = encodeField(codecSchema, map, f4, value);
                map = encodeField(codecSchema, map, f5, value);
                map = encodeField(codecSchema, map, f6, value);
                map = encodeField(codecSchema, map, f7, value);
                map = encodeField(codecSchema, map, f8, value);
                map = encodeField(codecSchema, map, f9, value);
                map = encodeField(codecSchema, map, f10, value);
                map = encodeField(codecSchema, map, f11, value);
                map = encodeField(codecSchema, map, f12, value);
                return map;
            }

            @Override
            public <T> Result<R, T> decode(CodecSchema<T> codecSchema, T input) {
                return decodeField(codecSchema, input, f1)
                    .andThen(
                        a1 -> decodeField(codecSchema, input, f2)
                            .andThen(
                                a2 -> decodeField(codecSchema, input, f3)
                                    .andThen(
                                        a3 -> decodeField(codecSchema, input, f4)
                                            .andThen(
                                                a4 -> decodeField(codecSchema, input, f5)
                                                    .andThen(
                                                        a5 -> decodeField(codecSchema, input, f6)
                                                            .andThen(
                                                                a6 -> decodeField(codecSchema, input, f7)
                                                                    .andThen(
                                                                        a7 -> decodeField(codecSchema, input, f8)
                                                                            .andThen(
                                                                                a8 -> decodeField(
                                                                                    codecSchema,
                                                                                    input,
                                                                                    f9
                                                                                )
                                                                                    .andThen(
                                                                                        a9 -> decodeField(
                                                                                            codecSchema,
                                                                                            input,
                                                                                            f10
                                                                                        )
                                                                                            .andThen(
                                                                                                a10 -> decodeField(
                                                                                                    codecSchema,
                                                                                                    input,
                                                                                                    f11
                                                                                                )
                                                                                                    .andThen(
                                                                                                        a11 -> decodeField(
                                                                                                            codecSchema,
                                                                                                            input,
                                                                                                            f12
                                                                                                        )
                                                                                                            .map(
                                                                                                                a12 -> constructor
                                                                                                                    .apply(
                                                                                                                        a1,
                                                                                                                        a2,
                                                                                                                        a3,
                                                                                                                        a4,
                                                                                                                        a5,
                                                                                                                        a6,
                                                                                                                        a7,
                                                                                                                        a8,
                                                                                                                        a9,
                                                                                                                        a10,
                                                                                                                        a11,
                                                                                                                        a12
                                                                                                                    )
                                                                                                            )
                                                                                                    )
                                                                                            )
                                                                                    )
                                                                            )
                                                                    )
                                                            )
                                                    )
                                            )
                                    )
                            )
                    );
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, R> Codec<R> of(
        FieldCodec<R, A1> f1,
        FieldCodec<R, A2> f2,
        FieldCodec<R, A3> f3,
        FieldCodec<R, A4> f4,
        FieldCodec<R, A5> f5,
        FieldCodec<R, A6> f6,
        FieldCodec<R, A7> f7,
        FieldCodec<R, A8> f8,
        FieldCodec<R, A9> f9,
        FieldCodec<R, A10> f10,
        FieldCodec<R, A11> f11,
        FieldCodec<R, A12> f12,
        FieldCodec<R, A13> f13,
        Function13<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, R> constructor
    ) {
        return new Codec<>() {

            @Override
            public <T> T encode(CodecSchema<T> codecSchema, R value) {
                var map = codecSchema.emptyMap();
                map = encodeField(codecSchema, map, f1, value);
                map = encodeField(codecSchema, map, f2, value);
                map = encodeField(codecSchema, map, f3, value);
                map = encodeField(codecSchema, map, f4, value);
                map = encodeField(codecSchema, map, f5, value);
                map = encodeField(codecSchema, map, f6, value);
                map = encodeField(codecSchema, map, f7, value);
                map = encodeField(codecSchema, map, f8, value);
                map = encodeField(codecSchema, map, f9, value);
                map = encodeField(codecSchema, map, f10, value);
                map = encodeField(codecSchema, map, f11, value);
                map = encodeField(codecSchema, map, f12, value);
                map = encodeField(codecSchema, map, f13, value);
                return map;
            }

            @Override
            public <T> Result<R, T> decode(CodecSchema<T> codecSchema, T input) {
                return decodeField(codecSchema, input, f1)
                    .andThen(
                        a1 -> decodeField(codecSchema, input, f2)
                            .andThen(
                                a2 -> decodeField(codecSchema, input, f3)
                                    .andThen(
                                        a3 -> decodeField(codecSchema, input, f4)
                                            .andThen(
                                                a4 -> decodeField(codecSchema, input, f5)
                                                    .andThen(
                                                        a5 -> decodeField(codecSchema, input, f6)
                                                            .andThen(
                                                                a6 -> decodeField(codecSchema, input, f7)
                                                                    .andThen(
                                                                        a7 -> decodeField(codecSchema, input, f8)
                                                                            .andThen(
                                                                                a8 -> decodeField(
                                                                                    codecSchema,
                                                                                    input,
                                                                                    f9
                                                                                )
                                                                                    .andThen(
                                                                                        a9 -> decodeField(
                                                                                            codecSchema,
                                                                                            input,
                                                                                            f10
                                                                                        )
                                                                                            .andThen(
                                                                                                a10 -> decodeField(
                                                                                                    codecSchema,
                                                                                                    input,
                                                                                                    f11
                                                                                                )
                                                                                                    .andThen(
                                                                                                        a11 -> decodeField(
                                                                                                            codecSchema,
                                                                                                            input,
                                                                                                            f12
                                                                                                        )
                                                                                                            .andThen(
                                                                                                                a12 -> decodeField(
                                                                                                                    codecSchema,
                                                                                                                    input,
                                                                                                                    f13
                                                                                                                )
                                                                                                                    .map(
                                                                                                                        a13 -> constructor
                                                                                                                            .apply(
                                                                                                                                a1,
                                                                                                                                a2,
                                                                                                                                a3,
                                                                                                                                a4,
                                                                                                                                a5,
                                                                                                                                a6,
                                                                                                                                a7,
                                                                                                                                a8,
                                                                                                                                a9,
                                                                                                                                a10,
                                                                                                                                a11,
                                                                                                                                a12,
                                                                                                                                a13
                                                                                                                            )
                                                                                                                    )
                                                                                                            )
                                                                                                    )
                                                                                            )
                                                                                    )
                                                                            )
                                                                    )
                                                            )
                                                    )
                                            )
                                    )
                            )
                    );
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, R> Codec<R> of(
        FieldCodec<R, A1> f1,
        FieldCodec<R, A2> f2,
        FieldCodec<R, A3> f3,
        FieldCodec<R, A4> f4,
        FieldCodec<R, A5> f5,
        FieldCodec<R, A6> f6,
        FieldCodec<R, A7> f7,
        FieldCodec<R, A8> f8,
        FieldCodec<R, A9> f9,
        FieldCodec<R, A10> f10,
        FieldCodec<R, A11> f11,
        FieldCodec<R, A12> f12,
        FieldCodec<R, A13> f13,
        FieldCodec<R, A14> f14,
        Function14<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, R> constructor
    ) {
        return new Codec<>() {

            @Override
            public <T> T encode(CodecSchema<T> codecSchema, R value) {
                var map = codecSchema.emptyMap();
                map = encodeField(codecSchema, map, f1, value);
                map = encodeField(codecSchema, map, f2, value);
                map = encodeField(codecSchema, map, f3, value);
                map = encodeField(codecSchema, map, f4, value);
                map = encodeField(codecSchema, map, f5, value);
                map = encodeField(codecSchema, map, f6, value);
                map = encodeField(codecSchema, map, f7, value);
                map = encodeField(codecSchema, map, f8, value);
                map = encodeField(codecSchema, map, f9, value);
                map = encodeField(codecSchema, map, f10, value);
                map = encodeField(codecSchema, map, f11, value);
                map = encodeField(codecSchema, map, f12, value);
                map = encodeField(codecSchema, map, f13, value);
                map = encodeField(codecSchema, map, f14, value);
                return map;
            }

            @Override
            public <T> Result<R, T> decode(CodecSchema<T> codecSchema, T input) {
                return decodeField(codecSchema, input, f1)
                    .andThen(
                        a1 -> decodeField(codecSchema, input, f2)
                            .andThen(
                                a2 -> decodeField(codecSchema, input, f3)
                                    .andThen(
                                        a3 -> decodeField(codecSchema, input, f4)
                                            .andThen(
                                                a4 -> decodeField(codecSchema, input, f5)
                                                    .andThen(
                                                        a5 -> decodeField(codecSchema, input, f6)
                                                            .andThen(
                                                                a6 -> decodeField(codecSchema, input, f7)
                                                                    .andThen(
                                                                        a7 -> decodeField(codecSchema, input, f8)
                                                                            .andThen(
                                                                                a8 -> decodeField(
                                                                                    codecSchema,
                                                                                    input,
                                                                                    f9
                                                                                )
                                                                                    .andThen(
                                                                                        a9 -> decodeField(
                                                                                            codecSchema,
                                                                                            input,
                                                                                            f10
                                                                                        )
                                                                                            .andThen(
                                                                                                a10 -> decodeField(
                                                                                                    codecSchema,
                                                                                                    input,
                                                                                                    f11
                                                                                                )
                                                                                                    .andThen(
                                                                                                        a11 -> decodeField(
                                                                                                            codecSchema,
                                                                                                            input,
                                                                                                            f12
                                                                                                        )
                                                                                                            .andThen(
                                                                                                                a12 -> decodeField(
                                                                                                                    codecSchema,
                                                                                                                    input,
                                                                                                                    f13
                                                                                                                )
                                                                                                                    .andThen(
                                                                                                                        a13 -> decodeField(
                                                                                                                            codecSchema,
                                                                                                                            input,
                                                                                                                            f14
                                                                                                                        )
                                                                                                                            .map(
                                                                                                                                a14 -> constructor
                                                                                                                                    .apply(
                                                                                                                                        a1,
                                                                                                                                        a2,
                                                                                                                                        a3,
                                                                                                                                        a4,
                                                                                                                                        a5,
                                                                                                                                        a6,
                                                                                                                                        a7,
                                                                                                                                        a8,
                                                                                                                                        a9,
                                                                                                                                        a10,
                                                                                                                                        a11,
                                                                                                                                        a12,
                                                                                                                                        a13,
                                                                                                                                        a14
                                                                                                                                    )
                                                                                                                            )
                                                                                                                    )
                                                                                                            )
                                                                                                    )
                                                                                            )
                                                                                    )
                                                                            )
                                                                    )
                                                            )
                                                    )
                                            )
                                    )
                            )
                    );
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, R> Codec<R> of(
        FieldCodec<R, A1> f1,
        FieldCodec<R, A2> f2,
        FieldCodec<R, A3> f3,
        FieldCodec<R, A4> f4,
        FieldCodec<R, A5> f5,
        FieldCodec<R, A6> f6,
        FieldCodec<R, A7> f7,
        FieldCodec<R, A8> f8,
        FieldCodec<R, A9> f9,
        FieldCodec<R, A10> f10,
        FieldCodec<R, A11> f11,
        FieldCodec<R, A12> f12,
        FieldCodec<R, A13> f13,
        FieldCodec<R, A14> f14,
        FieldCodec<R, A15> f15,
        Function15<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, R> constructor
    ) {
        return new Codec<>() {

            @Override
            public <T> T encode(CodecSchema<T> codecSchema, R value) {
                var map = codecSchema.emptyMap();
                map = encodeField(codecSchema, map, f1, value);
                map = encodeField(codecSchema, map, f2, value);
                map = encodeField(codecSchema, map, f3, value);
                map = encodeField(codecSchema, map, f4, value);
                map = encodeField(codecSchema, map, f5, value);
                map = encodeField(codecSchema, map, f6, value);
                map = encodeField(codecSchema, map, f7, value);
                map = encodeField(codecSchema, map, f8, value);
                map = encodeField(codecSchema, map, f9, value);
                map = encodeField(codecSchema, map, f10, value);
                map = encodeField(codecSchema, map, f11, value);
                map = encodeField(codecSchema, map, f12, value);
                map = encodeField(codecSchema, map, f13, value);
                map = encodeField(codecSchema, map, f14, value);
                map = encodeField(codecSchema, map, f15, value);
                return map;
            }

            @Override
            public <T> Result<R, T> decode(CodecSchema<T> codecSchema, T input) {
                return decodeField(codecSchema, input, f1)
                    .andThen(
                        a1 -> decodeField(codecSchema, input, f2)
                            .andThen(
                                a2 -> decodeField(codecSchema, input, f3)
                                    .andThen(
                                        a3 -> decodeField(codecSchema, input, f4)
                                            .andThen(
                                                a4 -> decodeField(codecSchema, input, f5)
                                                    .andThen(
                                                        a5 -> decodeField(codecSchema, input, f6)
                                                            .andThen(
                                                                a6 -> decodeField(codecSchema, input, f7)
                                                                    .andThen(
                                                                        a7 -> decodeField(codecSchema, input, f8)
                                                                            .andThen(
                                                                                a8 -> decodeField(
                                                                                    codecSchema,
                                                                                    input,
                                                                                    f9
                                                                                )
                                                                                    .andThen(
                                                                                        a9 -> decodeField(
                                                                                            codecSchema,
                                                                                            input,
                                                                                            f10
                                                                                        )
                                                                                            .andThen(
                                                                                                a10 -> decodeField(
                                                                                                    codecSchema,
                                                                                                    input,
                                                                                                    f11
                                                                                                )
                                                                                                    .andThen(
                                                                                                        a11 -> decodeField(
                                                                                                            codecSchema,
                                                                                                            input,
                                                                                                            f12
                                                                                                        )
                                                                                                            .andThen(
                                                                                                                a12 -> decodeField(
                                                                                                                    codecSchema,
                                                                                                                    input,
                                                                                                                    f13
                                                                                                                )
                                                                                                                    .andThen(
                                                                                                                        a13 -> decodeField(
                                                                                                                            codecSchema,
                                                                                                                            input,
                                                                                                                            f14
                                                                                                                        )
                                                                                                                            .andThen(
                                                                                                                                a14 -> decodeField(
                                                                                                                                    codecSchema,
                                                                                                                                    input,
                                                                                                                                    f15
                                                                                                                                )
                                                                                                                                    .map(
                                                                                                                                        a15 -> constructor
                                                                                                                                            .apply(
                                                                                                                                                a1,
                                                                                                                                                a2,
                                                                                                                                                a3,
                                                                                                                                                a4,
                                                                                                                                                a5,
                                                                                                                                                a6,
                                                                                                                                                a7,
                                                                                                                                                a8,
                                                                                                                                                a9,
                                                                                                                                                a10,
                                                                                                                                                a11,
                                                                                                                                                a12,
                                                                                                                                                a13,
                                                                                                                                                a14,
                                                                                                                                                a15
                                                                                                                                            )
                                                                                                                                    )
                                                                                                                            )
                                                                                                                    )
                                                                                                            )
                                                                                                    )
                                                                                            )
                                                                                    )
                                                                            )
                                                                    )
                                                            )
                                                    )
                                            )
                                    )
                            )
                    );
            }
        };
    }

    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, R> Codec<R> of(
        FieldCodec<R, A1> f1,
        FieldCodec<R, A2> f2,
        FieldCodec<R, A3> f3,
        FieldCodec<R, A4> f4,
        FieldCodec<R, A5> f5,
        FieldCodec<R, A6> f6,
        FieldCodec<R, A7> f7,
        FieldCodec<R, A8> f8,
        FieldCodec<R, A9> f9,
        FieldCodec<R, A10> f10,
        FieldCodec<R, A11> f11,
        FieldCodec<R, A12> f12,
        FieldCodec<R, A13> f13,
        FieldCodec<R, A14> f14,
        FieldCodec<R, A15> f15,
        FieldCodec<R, A16> f16,
        Function16<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, R> constructor
    ) {
        return new Codec<>() {

            @Override
            public <T> T encode(CodecSchema<T> codecSchema, R value) {
                var map = codecSchema.emptyMap();
                map = encodeField(codecSchema, map, f1, value);
                map = encodeField(codecSchema, map, f2, value);
                map = encodeField(codecSchema, map, f3, value);
                map = encodeField(codecSchema, map, f4, value);
                map = encodeField(codecSchema, map, f5, value);
                map = encodeField(codecSchema, map, f6, value);
                map = encodeField(codecSchema, map, f7, value);
                map = encodeField(codecSchema, map, f8, value);
                map = encodeField(codecSchema, map, f9, value);
                map = encodeField(codecSchema, map, f10, value);
                map = encodeField(codecSchema, map, f11, value);
                map = encodeField(codecSchema, map, f12, value);
                map = encodeField(codecSchema, map, f13, value);
                map = encodeField(codecSchema, map, f14, value);
                map = encodeField(codecSchema, map, f15, value);
                map = encodeField(codecSchema, map, f16, value);
                return map;
            }

            @Override
            public <T> Result<R, T> decode(CodecSchema<T> codecSchema, T input) {
                return decodeField(codecSchema, input, f1)
                    .andThen(
                        a1 -> decodeField(codecSchema, input, f2)
                            .andThen(
                                a2 -> decodeField(codecSchema, input, f3)
                                    .andThen(
                                        a3 -> decodeField(codecSchema, input, f4)
                                            .andThen(
                                                a4 -> decodeField(codecSchema, input, f5)
                                                    .andThen(
                                                        a5 -> decodeField(codecSchema, input, f6)
                                                            .andThen(
                                                                a6 -> decodeField(codecSchema, input, f7)
                                                                    .andThen(
                                                                        a7 -> decodeField(codecSchema, input, f8)
                                                                            .andThen(
                                                                                a8 -> decodeField(
                                                                                    codecSchema,
                                                                                    input,
                                                                                    f9
                                                                                )
                                                                                    .andThen(
                                                                                        a9 -> decodeField(
                                                                                            codecSchema,
                                                                                            input,
                                                                                            f10
                                                                                        )
                                                                                            .andThen(
                                                                                                a10 -> decodeField(
                                                                                                    codecSchema,
                                                                                                    input,
                                                                                                    f11
                                                                                                )
                                                                                                    .andThen(
                                                                                                        a11 -> decodeField(
                                                                                                            codecSchema,
                                                                                                            input,
                                                                                                            f12
                                                                                                        )
                                                                                                            .andThen(
                                                                                                                a12 -> decodeField(
                                                                                                                    codecSchema,
                                                                                                                    input,
                                                                                                                    f13
                                                                                                                )
                                                                                                                    .andThen(
                                                                                                                        a13 -> decodeField(
                                                                                                                            codecSchema,
                                                                                                                            input,
                                                                                                                            f14
                                                                                                                        )
                                                                                                                            .andThen(
                                                                                                                                a14 -> decodeField(
                                                                                                                                    codecSchema,
                                                                                                                                    input,
                                                                                                                                    f15
                                                                                                                                )
                                                                                                                                    .andThen(
                                                                                                                                        a15 -> decodeField(
                                                                                                                                            codecSchema,
                                                                                                                                            input,
                                                                                                                                            f16
                                                                                                                                        )
                                                                                                                                            .map(
                                                                                                                                                a16 -> constructor
                                                                                                                                                    .apply(
                                                                                                                                                        a1,
                                                                                                                                                        a2,
                                                                                                                                                        a3,
                                                                                                                                                        a4,
                                                                                                                                                        a5,
                                                                                                                                                        a6,
                                                                                                                                                        a7,
                                                                                                                                                        a8,
                                                                                                                                                        a9,
                                                                                                                                                        a10,
                                                                                                                                                        a11,
                                                                                                                                                        a12,
                                                                                                                                                        a13,
                                                                                                                                                        a14,
                                                                                                                                                        a15,
                                                                                                                                                        a16
                                                                                                                                                    )
                                                                                                                                            )
                                                                                                                                    )
                                                                                                                            )
                                                                                                                    )
                                                                                                            )
                                                                                                    )
                                                                                            )
                                                                                    )
                                                                            )
                                                                    )
                                                            )
                                                    )
                                            )
                                    )
                            )
                    );
            }
        };
    }
}
