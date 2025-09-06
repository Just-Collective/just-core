package com.just.core.serialization.codec;

import java.util.function.Function;

import com.just.core.functional.result.Result;
import com.just.core.serialization.codec.schema.CodecSchema;

public interface Decoder<A> {

    <T> Result<A, T> decode(CodecSchema<T> codecSchema, T input);

    default <B> Decoder<B> map(Function<? super A, ? extends B> function) {
        return new Decoder<>() {

            @Override
            public <T> Result<B, T> decode(CodecSchema<T> codecSchema, T input) {
                return Decoder.this.decode(codecSchema, input).map(function);
            }

            @Override
            public String toString() {
                return Decoder.this + "[mapped]";
            }
        };
    }

    default <B> Decoder<B> flatMap(Function<? super A, ? extends B> function) {
        return new Decoder<>() {

            @Override
            public <T> Result<B, T> decode(CodecSchema<T> codecSchema, T input) {
                return Decoder.this.decode(codecSchema, input)
                    .andThen(a -> Result.ok(function.apply(a)));
            }

            @Override
            public String toString() {
                return Decoder.this + "[flatMapped]";
            }
        };
    }
}
