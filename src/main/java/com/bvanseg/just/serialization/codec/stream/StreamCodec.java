package com.bvanseg.just.serialization.codec.stream;

import org.jetbrains.annotations.NotNull;

import java.util.*;

import com.bvanseg.just.functional.option.Option;
import com.bvanseg.just.serialization.codec.stream.impl.ListStreamCodec;
import com.bvanseg.just.serialization.codec.stream.impl.MapStreamCodec;
import com.bvanseg.just.serialization.codec.stream.impl.NullWrapperStreamCodec;
import com.bvanseg.just.serialization.codec.stream.schema.StreamCodecSchema;

public interface StreamCodec<A> extends StreamEncoder<A>, StreamDecoder<A> {

    static <V> StreamCodec<V> unit(V value) {
        return new StreamCodec<>() {

            @Override
            public <T> @NotNull V decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
                return value;
            }

            @Override
            public <T> void encode(
                @NotNull StreamCodecSchema<T> streamCodecSchema,
                @NotNull T input,
                @NotNull V value
            ) {}
        };
    }

    static <V> StreamCodec<V> of(
        StreamDecoder<V> streamDecoder,
        StreamEncoder<V> streamEncoder
    ) {
        return new StreamCodec<>() {

            @Override
            public <T> @NotNull V decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
                return streamDecoder.decode(streamCodecSchema, input);
            }

            @Override
            public <T> void encode(
                @NotNull StreamCodecSchema<T> streamCodecSchema,
                @NotNull T input,
                @NotNull V value
            ) {
                streamEncoder.encode(streamCodecSchema, input, value);
            }
        };
    }

    static <K, V> MapStreamCodec<K, V> unboundedMap(
        StreamCodec<K> keyStreamCodec,
        StreamCodec<V> valueStreamCodec
    ) {
        return new MapStreamCodec<>(keyStreamCodec, valueStreamCodec);
    }

    default NullWrapperStreamCodec<A, Option<A>> asOption() {
        return new NullWrapperStreamCodec<>(this, Option::ofNullable, option -> option.unwrapOr(null));
    }

    default NullWrapperStreamCodec<A, Optional<A>> asOptional() {
        return new NullWrapperStreamCodec<>(this, Optional::ofNullable, optional -> optional.orElse(null));
    }

    default StreamCodec<List<A>> asList() {
        return new ListStreamCodec<>(this);
    }
}
