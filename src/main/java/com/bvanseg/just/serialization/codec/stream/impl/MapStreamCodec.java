package com.bvanseg.just.serialization.codec.stream.impl;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import com.bvanseg.just.serialization.codec.stream.StreamCodec;
import com.bvanseg.just.serialization.codec.stream.schema.StreamCodecSchema;

public class MapStreamCodec<K, V> implements StreamCodec<Map<K, V>> {

    private final StreamCodec<K> keyStreamCodec;

    private final StreamCodec<V> valueStreamCodec;

    public MapStreamCodec(StreamCodec<K> keyStreamCodec, StreamCodec<V> valueStreamCodec) {
        this.keyStreamCodec = keyStreamCodec;
        this.valueStreamCodec = valueStreamCodec;
    }

    @Override
    public <T> void encode(
        @NotNull StreamCodecSchema<T> streamCodecSchema,
        @NotNull T input,
        @NotNull Map<K, V> value
    ) {
        StreamCodecs.VAR_INT.encode(streamCodecSchema, input, value.size());

        for (var entry : value.entrySet()) {
            keyStreamCodec.encode(streamCodecSchema, input, entry.getKey());
            valueStreamCodec.encode(streamCodecSchema, input, entry.getValue());
        }
    }

    @Override
    public <T> @NotNull Map<K, V> decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
        int size = StreamCodecs.VAR_INT.decode(streamCodecSchema, input);
        var map = new HashMap<K, V>(size);

        for (var i = 0; i < size; i++) {
            var key = keyStreamCodec.decode(streamCodecSchema, input);
            var value = valueStreamCodec.decode(streamCodecSchema, input);

            map.put(key, value);
        }

        return map;
    }
}
