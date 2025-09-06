package com.just.core.serialization.codec.impl;

import java.util.ArrayList;
import java.util.List;

import com.just.core.functional.result.Result;
import com.just.core.serialization.codec.Codec;
import com.just.core.serialization.codec.schema.CodecSchema;

public class ListCodec<A> implements Codec<List<A>> {

    private final Codec<A> elementCodec;

    public ListCodec(Codec<A> elementCodec) {
        this.elementCodec = elementCodec;
    }

    @Override
    public <T> Result<List<A>, T> decode(CodecSchema<T> codecSchema, T input) {
        return codecSchema.getList(input)
            .andThen(consumer -> {
                var elements = new ArrayList<T>();

                // Push each element into the list collector.
                consumer.accept(elements::add);

                var decodedList = new ArrayList<A>();

                for (var element : elements) {
                    var result = elementCodec.decode(codecSchema, element);

                    if (result.isErr()) {
                        return Result.err(result.unwrapErr());
                    }

                    decodedList.add(result.unwrap());
                }

                return Result.ok(decodedList);
            });
    }

    @Override
    public <T> T encode(CodecSchema<T> codecSchema, List<A> values) {
        return codecSchema.createList(
            values.stream()
                .map(value -> elementCodec.encode(codecSchema, value))
        );
    }

    @Override
    public String toString() {
        return "ListCodec[" + elementCodec + ']';
    }
}
