package com.bvanseg.just.serialization.codec;

import java.util.HashMap;
import java.util.Map;

import com.bvanseg.just.functional.result.Result;
import com.bvanseg.just.functional.tuple.Tuple2;
import com.bvanseg.just.serialization.codec.schema.CodecSchema;

public interface BaseMapCodec<K, V> extends Codec<Map<K, V>> {

    Codec<K> keyCodec();

    Codec<V> valueCodec();

    @Override
    default <T> Result<Map<K, V>, T> decode(CodecSchema<T> schema, T input) {
        return schema.getMap(input)
            .andThen(stream -> {
                var map = new HashMap<K, V>();

                for (var tuple2 : stream.toList()) {
                    var keyResult = keyCodec().decode(schema, tuple2.v1());
                    var valueResult = valueCodec().decode(schema, tuple2.v2());

                    if (keyResult.isErr())
                        return Result.err(keyResult.unwrapErr());
                    if (valueResult.isErr())
                        return Result.err(valueResult.unwrapErr());

                    map.put(keyResult.unwrap(), valueResult.unwrap());
                }

                return Result.ok(map);
            });
    }

    @Override
    default <T> T encode(CodecSchema<T> schema, Map<K, V> input) {
        var stream = input.entrySet().stream().map(entry -> {
            var key = keyCodec().encode(schema, entry.getKey());
            var value = valueCodec().encode(schema, entry.getValue());
            return new Tuple2<>(key, value);
        });

        return schema.createMap(stream);
    }
}
