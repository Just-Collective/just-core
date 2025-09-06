package com.just.core.serialization.codec.stream;

import org.jetbrains.annotations.NotNull;

import com.just.core.serialization.codec.stream.schema.StreamCodecSchema;

public interface StreamEncoder<A> {

    <T> void encode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input, @NotNull A value);
}
