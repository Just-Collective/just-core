package com.bvanseg.just.serialization.codec.stream.impl;

import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import com.bvanseg.just.functional.option.Option;
import com.bvanseg.just.serialization.codec.stream.StreamCodec;

public class StreamCodecs {

    public static final StreamCodec<ByteBuffer, Byte> BYTE = StreamCodec.of(ByteBuffer::put, ByteBuffer::get);

    public static final StreamCodec<ByteBuffer, Character> CHAR = StreamCodec.of(
        ByteBuffer::putChar,
        ByteBuffer::getChar
    );

    public static final StreamCodec<ByteBuffer, Short> SHORT = StreamCodec.of(
        ByteBuffer::putShort,
        ByteBuffer::getShort
    );

    public static final StreamCodec<ByteBuffer, Integer> INT = StreamCodec.of(ByteBuffer::putInt, ByteBuffer::getInt);

    public static final StreamCodec<ByteBuffer, Long> LONG = StreamCodec.of(ByteBuffer::putLong, ByteBuffer::getLong);

    public static final StreamCodec<ByteBuffer, Float> FLOAT = StreamCodec.of(
        ByteBuffer::putFloat,
        ByteBuffer::getFloat
    );

    public static final StreamCodec<ByteBuffer, Double> DOUBLE = StreamCodec.of(
        ByteBuffer::putDouble,
        ByteBuffer::getDouble
    );

    public static final StreamCodec<ByteBuffer, UUID> UUID = StreamCodec.of((b, v) -> {
        b.putLong(v.getMostSignificantBits());
        b.putLong(v.getLeastSignificantBits());
    }, b -> new UUID(b.getLong(), b.getLong()));

    public static final StreamCodec<ByteBuffer, Integer> VAR_INT = new StreamCodec<>() {

        @Override
        public void encode(@NotNull ByteBuffer buffer, @NotNull Integer value) {
            // While more than 7 bits remain...
            while ((value & 0xFFFFFF80) != 0) {
                // Write lower 7 bits and set MSB to 1.
                buffer.put((byte) ((value & 0x7F) | 0x80));
                // Logical shift right by 7 (removing written bits).
                value >>>= 7;
            }

            // Write last 7 bits with MSB 0 (final byte).
            buffer.put((byte) (value & 0x7F));
        }

        @Override
        public @NotNull Integer decode(@NotNull ByteBuffer buffer) {
            var value = 0;
            var position = 0;
            byte currentByte;

            do {
                currentByte = buffer.get();
                // Extract 7 bits and shift into place.
                value |= (currentByte & 0x7F) << position;
                // Move to the next 7-bit chunk.
                position += 7;
                // Continue if MSB is set.
            } while ((currentByte & 0x80) != 0);

            return value;
        }
    };

    public static final StreamCodec<ByteBuffer, String> STRING = new StreamCodec<>() {

        @Override
        public void encode(@NotNull ByteBuffer buffer, @NotNull String value) {
            var messageBytes = value.getBytes(StandardCharsets.UTF_8);
            VAR_INT.encode(buffer, messageBytes.length);
            buffer.put(messageBytes);
        }

        @Override
        public @NotNull String decode(@NotNull ByteBuffer buffer) {
            var length = VAR_INT.decode(buffer);
            return sliceAndTransform(
                buffer,
                length,
                bufferSlice -> StandardCharsets.UTF_8.decode(bufferSlice).toString()
            );
        }
    };

    // Option Codecs

    public static final StreamCodec<ByteBuffer, Option<Byte>> OPTION_BYTE = StreamCodecs.BYTE.asOption();

    public static final StreamCodec<ByteBuffer, Option<Character>> OPTION_CHAR = StreamCodecs.CHAR.asOption();

    public static final StreamCodec<ByteBuffer, Option<Short>> OPTION_SHORT = StreamCodecs.SHORT.asOption();

    public static final StreamCodec<ByteBuffer, Option<Integer>> OPTION_INT = StreamCodecs.INT.asOption();

    public static final StreamCodec<ByteBuffer, Option<Integer>> OPTION_VAR_INT = StreamCodecs.VAR_INT.asOption();

    public static final StreamCodec<ByteBuffer, Option<Long>> OPTION_LONG = StreamCodecs.LONG.asOption();

    public static final StreamCodec<ByteBuffer, Option<Float>> OPTION_FLOAT = StreamCodecs.FLOAT.asOption();

    public static final StreamCodec<ByteBuffer, Option<Double>> OPTION_DOUBLE = StreamCodecs.DOUBLE.asOption();

    public static final StreamCodec<ByteBuffer, Option<UUID>> OPTION_UUID = StreamCodecs.UUID.asOption();

    public static final StreamCodec<ByteBuffer, Option<String>> OPTION_STRING = StreamCodecs.STRING.asOption();

    // Optional Codecs

    public static final StreamCodec<ByteBuffer, Optional<Byte>> OPTIONAL_BYTE = StreamCodecs.BYTE.asOptional();

    public static final StreamCodec<ByteBuffer, Optional<Character>> OPTIONAL_CHAR = StreamCodecs.CHAR.asOptional();

    public static final StreamCodec<ByteBuffer, Optional<Short>> OPTIONAL_SHORT = StreamCodecs.SHORT.asOptional();

    public static final StreamCodec<ByteBuffer, Optional<Integer>> OPTIONAL_INT = StreamCodecs.INT.asOptional();

    public static final StreamCodec<ByteBuffer, Optional<Integer>> OPTIONAL_VAR_INT = StreamCodecs.VAR_INT.asOptional();

    public static final StreamCodec<ByteBuffer, Optional<Long>> OPTIONAL_LONG = StreamCodecs.LONG.asOptional();

    public static final StreamCodec<ByteBuffer, Optional<Float>> OPTIONAL_FLOAT = StreamCodecs.FLOAT.asOptional();

    public static final StreamCodec<ByteBuffer, Optional<Double>> OPTIONAL_DOUBLE = StreamCodecs.DOUBLE.asOptional();

    public static final StreamCodec<ByteBuffer, Optional<UUID>> OPTIONAL_UUID = StreamCodecs.UUID.asOptional();

    public static final StreamCodec<ByteBuffer, Optional<String>> OPTIONAL_STRING = StreamCodecs.STRING.asOptional();

    private static <O> O sliceAndTransform(ByteBuffer buffer, int length, Function<ByteBuffer, O> transformer) {
        // Use ByteBuffer slice to create a view, avoiding new byte[].
        var byteBuffer = buffer.slice();
        // Set length boundary.
        byteBuffer.limit(length);

        var result = transformer.apply(byteBuffer);

        // Move the original buffer's position forward.
        buffer.position(buffer.position() + length);

        return result;
    }
}
