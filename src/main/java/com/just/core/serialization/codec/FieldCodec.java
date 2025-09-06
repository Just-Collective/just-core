package com.just.core.serialization.codec;

import com.just.core.functional.function.Function;
import com.just.core.functional.result.Result;
import com.just.core.serialization.codec.schema.CodecSchema;

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
