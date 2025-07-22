package com.bvanseg.just.serialization.codec;

import com.bvanseg.just.functional.function.Function;
import com.bvanseg.just.functional.result.Result;
import com.bvanseg.just.serialization.codec.schema.CodecSchema;

public record FieldCodec<S, V>(
    String fieldName,
    Function<S, V> getter,
    Codec<V> codec
) implements Codec<V> {

    @Override
    public <T> Result<V, T> decode(CodecSchema<T> codecSchema, T input) {
        return codec.decode(codecSchema, input);
    }

    @Override
    public <T> T encode(CodecSchema<T> codecSchema, V value) {
        return codec.encode(codecSchema, value);
    }

}
