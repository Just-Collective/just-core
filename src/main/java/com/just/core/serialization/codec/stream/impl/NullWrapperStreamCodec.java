package com.just.core.serialization.codec.stream.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.just.core.functional.function.Function;
import com.just.core.serialization.codec.stream.StreamCodec;
import com.just.core.serialization.codec.stream.schema.StreamCodecSchema;

public class NullWrapperStreamCodec<ValueType, WrapperType> implements StreamCodec<WrapperType> {

    private final StreamCodec<ValueType> streamCodec;

    private final Function<@Nullable ValueType, WrapperType> wrap;

    private final Function<WrapperType, @Nullable ValueType> unwrap;

    public NullWrapperStreamCodec(
        @NotNull StreamCodec<ValueType> streamCodec,
        Function<@Nullable ValueType, WrapperType> wrap,
        Function<WrapperType, @Nullable ValueType> unwrap
    ) {
        this.streamCodec = streamCodec;
        this.wrap = wrap;
        this.unwrap = unwrap;
    }

    @Override
    public <T> @NotNull WrapperType decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
        return wrap.apply(decodeOrNull(streamCodecSchema, input));
    }

    @Override
    public <T> void encode(
        @NotNull StreamCodecSchema<T> streamCodecSchema,
        @NotNull T input,
        @NotNull WrapperType value
    ) {
        encodeNullable(streamCodecSchema, input, unwrap.apply(value));
    }

    private <T> void encodeNullable(StreamCodecSchema<T> streamCodecSchema, T input, @Nullable ValueType value) {
        var isPresent = value != null;

        // Mark as present.
        streamCodecSchema.writeBoolean(input, isPresent);

        if (isPresent) {
            // Encode value normally.
            streamCodec.encode(streamCodecSchema, input, value);
        }
    }

    public <T> @Nullable ValueType decodeOrNull(StreamCodecSchema<T> streamCodecSchema, T input) {
        return streamCodecSchema.readBoolean(input)
            ? streamCodec.decode(streamCodecSchema, input)
            : null;
    }
}
