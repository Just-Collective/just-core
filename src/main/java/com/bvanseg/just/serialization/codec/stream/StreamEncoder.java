package com.bvanseg.just.serialization.codec.stream;

import org.jetbrains.annotations.NotNull;

public interface StreamEncoder<B, V> {

    void encode(@NotNull B buffer, @NotNull V value);
}
