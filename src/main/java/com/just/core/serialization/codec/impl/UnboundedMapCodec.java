package com.just.core.serialization.codec.impl;

import org.jetbrains.annotations.NotNull;

import com.just.core.serialization.codec.BaseMapCodec;
import com.just.core.serialization.codec.Codec;

public record UnboundedMapCodec<K, V>(
    Codec<K> keyCodec,
    Codec<V> valueCodec
) implements BaseMapCodec<K, V> {

    @Override
    public @NotNull String toString() {
        return "UnboundedMapCodec[" + keyCodec + " -> " + valueCodec + ']';
    }
}
