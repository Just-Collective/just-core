package com.bvanseg.just.serialization.codec.stream;

import org.jetbrains.annotations.NotNull;

public interface StreamDecoder<B, V> {

    @NotNull
    V decode(@NotNull B buffer);
}
