package com.bvanseg.just.serialization.codec.stream;

import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.bvanseg.just.functional.option.Option;
import com.bvanseg.just.serialization.codec.stream.impl.StreamCodecs;

public interface StreamCodec<B extends ByteBuffer, V> extends StreamEncoder<B, V>, StreamDecoder<B, V> {

    default NullWrapperStreamCodec<B, V, Option<V>> asOption() {
        return new NullWrapperStreamCodec<>(this, Option::ofNullable, option -> option.unwrapOr(null));
    }

    default NullWrapperStreamCodec<B, V, Optional<V>> asOptional() {
        return new NullWrapperStreamCodec<>(this, Optional::ofNullable, optional -> optional.orElse(null));
    }

    default <C extends Collection<V>> StreamCodec<B, C> asCollection(
        Function<Integer, C> collectionFactory
    ) {
        return new StreamCodec<>() {

            @Override
            public void encode(@NotNull B buffer, @NotNull C collection) {
                StreamCodecs.VAR_INT.encode(buffer, collection.size());

                for (var item : collection) {
                    StreamCodec.this.encode(buffer, item);
                }
            }

            @Override
            public @NotNull C decode(@NotNull B buffer) {
                int size = StreamCodecs.VAR_INT.decode(buffer);
                var collection = collectionFactory.apply(size);

                for (var i = 0; i < size; i++) {
                    collection.add(StreamCodec.this.decode(buffer));
                }

                return collection;
            }
        };
    }

    default StreamCodec<B, List<V>> asList() {
        return asCollection(ArrayList::new);
    }

    static <B extends ByteBuffer, V> StreamCodec<B, V> of(
        StreamEncoder<B, V> streamEncoder,
        StreamDecoder<B, V> decoder
    ) {
        return new StreamCodec<>() {

            @Override
            public void encode(@NotNull B buffer, @NotNull V value) {
                streamEncoder.encode(buffer, value);
            }

            @Override
            public @NotNull V decode(@NotNull B buffer) {
                return decoder.decode(buffer);
            }
        };
    }
}
