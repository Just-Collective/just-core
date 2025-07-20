package com.bvanseg.just.serialization.codec;

import java.util.function.Function;

import com.bvanseg.just.functional.result.Result;
import com.bvanseg.just.serialization.codec.schema.CodecSchema;

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
}
