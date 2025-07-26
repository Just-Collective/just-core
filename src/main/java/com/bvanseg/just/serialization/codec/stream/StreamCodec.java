package com.bvanseg.just.serialization.codec.stream;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.bvanseg.just.functional.option.Option;
import com.bvanseg.just.serialization.codec.stream.impl.StreamCodecs;
import com.bvanseg.just.serialization.codec.stream.schema.StreamCodecSchema;

public interface StreamCodec<A> extends StreamEncoder<A>, StreamDecoder<A> {

    default NullWrapperStreamCodec<A, Option<A>> asOption() {
        return new NullWrapperStreamCodec<>(this, Option::ofNullable, option -> option.unwrapOr(null));
    }

    default NullWrapperStreamCodec<A, Optional<A>> asOptional() {
        return new NullWrapperStreamCodec<>(this, Optional::ofNullable, optional -> optional.orElse(null));
    }

    default <C extends Collection<A>> StreamCodec<C> asCollection(
        Function<Integer, C> collectionFactory
    ) {
        return new StreamCodec<>() {

            @Override
            public <T> void encode(
                @NotNull StreamCodecSchema<T> streamCodecSchema,
                @NotNull T input,
                @NotNull C value
            ) {
                StreamCodecs.VAR_INT.encode(streamCodecSchema, input, value.size());

                for (var item : value) {
                    StreamCodec.this.encode(streamCodecSchema, input, item);
                }
            }

            @Override
            public <T> @NotNull C decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
                int size = StreamCodecs.VAR_INT.decode(streamCodecSchema, input);
                var collection = collectionFactory.apply(size);

                for (var i = 0; i < size; i++) {
                    collection.add(StreamCodec.this.decode(streamCodecSchema, input));
                }

                return collection;
            }
        };
    }

    default StreamCodec<List<A>> asList() {
        return asCollection(ArrayList::new);
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
}
