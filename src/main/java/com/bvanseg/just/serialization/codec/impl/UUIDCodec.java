package com.bvanseg.just.serialization.codec.impl;

import java.util.UUID;
import java.util.stream.IntStream;

import com.bvanseg.just.functional.result.Result;
import com.bvanseg.just.serialization.codec.Codec;
import com.bvanseg.just.serialization.codec.schema.CodecSchema;

public class UUIDCodec implements Codec<UUID> {

    @Override
    public <T> Result<UUID, T> decode(CodecSchema<T> codecSchema, T input) {
        return codecSchema.getIntStream(input)
            .andThen(stream -> {
                var ints = stream.toArray();

                if (ints.length != 4) {
                    return Result.err(input);
                }

                var mostSigBits = ((long) ints[0] << 32) | (ints[1] & 0xFFFFFFFFL);
                var leastSigBits = ((long) ints[2] << 32) | (ints[3] & 0xFFFFFFFFL);

                return Result.ok(new UUID(mostSigBits, leastSigBits));
            });
    }

    @Override
    public <T> T encode(CodecSchema<T> codecSchema, UUID value) {
        var most = value.getMostSignificantBits();
        var least = value.getLeastSignificantBits();

        var mostHigh = (int) (most >>> 32);
        var mostLow = (int) most;
        var leastHigh = (int) (least >>> 32);
        var leastLow = (int) least;

        return codecSchema.createIntList(IntStream.of(mostHigh, mostLow, leastHigh, leastLow));
    }

    @Override
    public String toString() {
        return "UUID";
    }
}
