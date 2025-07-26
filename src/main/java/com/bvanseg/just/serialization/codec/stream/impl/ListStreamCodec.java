package com.bvanseg.just.serialization.codec.stream.impl;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import com.bvanseg.just.serialization.codec.stream.StreamCodec;
import com.bvanseg.just.serialization.codec.stream.schema.StreamCodecSchema;

public class ListStreamCodec<A> implements StreamCodec<List<A>> {

    private final StreamCodec<A> streamCodec;

    public ListStreamCodec(StreamCodec<A> streamCodec) {
        this.streamCodec = streamCodec;
    }

    @Override
    public <T> void encode(
        @NotNull StreamCodecSchema<T> streamCodecSchema,
        @NotNull T input,
        @NotNull List<A> value
    ) {
        StreamCodecs.VAR_INT.encode(streamCodecSchema, input, value.size());

        for (var item : value) {
            streamCodec.encode(streamCodecSchema, input, item);
        }
    }

    @Override
    public <T> @NotNull List<A> decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
        int size = StreamCodecs.VAR_INT.decode(streamCodecSchema, input);
        var arrayList = new ArrayList<A>(size);

        for (var i = 0; i < size; i++) {
            arrayList.add(streamCodec.decode(streamCodecSchema, input));
        }

        return arrayList;
    }
}
