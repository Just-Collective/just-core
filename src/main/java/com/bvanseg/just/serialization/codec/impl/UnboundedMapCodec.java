package com.bvanseg.just.serialization.codec.impl;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

import com.bvanseg.just.functional.result.Result;
import com.bvanseg.just.serialization.codec.BaseMapCodec;
import com.bvanseg.just.serialization.codec.Codec;
import com.bvanseg.just.serialization.codec.schema.CodecSchema;

public record UnboundedMapCodec<K, V>(
    Codec<K> keyCodec,
    Codec<V> valueCodec
) implements BaseMapCodec<K, V>, Codec<Map<K, V>> {

    @Override
    public <T> Result<Map<K, V>, T> decode(CodecSchema<T> schema, T input) {
        return BaseMapCodec.super.decode(schema, input);
    }

    @Override
    public <T> T encode(CodecSchema<T> schema, Map<K, V> input) {
        return BaseMapCodec.super.encode(schema, input);
    }

    @Override
    public @NotNull String toString() {
        return "UnboundedMapCodec[" + keyCodec + " -> " + valueCodec + ']';
    }
}
