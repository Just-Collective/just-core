package com.bvanseg.just.serialization.codec.stream;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.ByteBuffer;

import com.bvanseg.just.functional.function.Function;

public class NullWrapperStreamCodec<B extends ByteBuffer, V, O> implements StreamCodec<B, O> {

    private final StreamCodec<B, V> streamCodec;

    private final Function<@Nullable V, O> wrap;

    private final Function<O, @Nullable V> unwrap;

    protected NullWrapperStreamCodec(
        @NotNull StreamCodec<B, V> streamCodec,
        Function<@Nullable V, O> wrap,
        Function<O, @Nullable V> unwrap
    ) {
        this.streamCodec = streamCodec;
        this.wrap = wrap;
        this.unwrap = unwrap;
    }

    @Override
    public void encode(@NotNull B buffer, @NotNull O value) {
        encodeNullable(buffer, unwrap.apply(value));
    }

    @Override
    public @NotNull O decode(@NotNull B buffer) {
        return wrap.apply(decodeOrNull(buffer));
    }

    private void encodeNullable(@NotNull B buffer, @Nullable V value) {
        if (value != null) {
            // Mark as present.
            buffer.put((byte) 1);
            // Encode value normally.
            streamCodec.encode(buffer, value);
        } else {
            // Mark as absent.
            buffer.put((byte) 0);
        }
    }

    public @Nullable V decodeOrNull(@NotNull B buffer) {
        return buffer.get() == 1
            ? streamCodec.decode(buffer)
            : null;
    }
}
