package com.bvanseg.just.serialization.codec.impl;

import org.jetbrains.annotations.NotNull;

import com.bvanseg.just.serialization.codec.BaseMapCodec;
import com.bvanseg.just.serialization.codec.Codec;

public record UnboundedMapCodec<K, V>(
    Codec<K> keyCodec,
    Codec<V> valueCodec
) implements BaseMapCodec<K, V> {

    @Override
    public @NotNull String toString() {
        return "UnboundedMapCodec[" + keyCodec + " -> " + valueCodec + ']';
    }
}
