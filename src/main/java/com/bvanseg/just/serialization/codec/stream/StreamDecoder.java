package com.bvanseg.just.serialization.codec.stream;

import org.jetbrains.annotations.NotNull;

import com.bvanseg.just.serialization.codec.stream.schema.StreamCodecSchema;

public interface StreamDecoder<A> {

    @NotNull
    <T> A decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input);
}
