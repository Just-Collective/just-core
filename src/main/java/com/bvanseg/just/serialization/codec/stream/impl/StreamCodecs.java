package com.bvanseg.just.serialization.codec.stream.impl;

import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;

import com.bvanseg.just.functional.option.Option;
import com.bvanseg.just.serialization.codec.stream.StreamCodec;
import com.bvanseg.just.serialization.codec.stream.schema.StreamCodecSchema;

public class StreamCodecs {

    public static final StreamCodec<Byte> BYTE = new StreamCodec<>() {

        @Override
        public @NotNull <T> Byte decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
            return streamCodecSchema.readByte(input);
        }

        @Override
        public <T> void encode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input, @NotNull Byte value) {
            streamCodecSchema.writeByte(input, value);
        }
    };

    public static final StreamCodec<Character> CHAR = new StreamCodec<>() {

        @Override
        public @NotNull <T> Character decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
            return streamCodecSchema.readChar(input);
        }

        @Override
        public <T> void encode(
            @NotNull StreamCodecSchema<T> streamCodecSchema,
            @NotNull T input,
            @NotNull Character value
        ) {
            streamCodecSchema.writeChar(input, value);
        }
    };

    public static final StreamCodec<Short> SHORT = new StreamCodec<>() {

        @Override
        public @NotNull <T> Short decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
            return streamCodecSchema.readShort(input);
        }

        @Override
        public <T> void encode(
            @NotNull StreamCodecSchema<T> streamCodecSchema,
            @NotNull T input,
            @NotNull Short value
        ) {
            streamCodecSchema.writeShort(input, value);
        }
    };

    public static final StreamCodec<Integer> INT = new StreamCodec<>() {

        @Override
        public @NotNull <T> Integer decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
            return streamCodecSchema.readInt(input);
        }

        @Override
        public <T> void encode(
            @NotNull StreamCodecSchema<T> streamCodecSchema,
            @NotNull T input,
            @NotNull Integer value
        ) {
            streamCodecSchema.writeInt(input, value);
        }
    };

    public static final StreamCodec<Long> LONG = new StreamCodec<>() {

        @Override
        public @NotNull <T> Long decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
            return streamCodecSchema.readLong(input);
        }

        @Override
        public <T> void encode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input, @NotNull Long value) {
            streamCodecSchema.writeLong(input, value);
        }
    };

    public static final StreamCodec<Float> FLOAT = new StreamCodec<Float>() {

        @Override
        public @NotNull <T> Float decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
            return streamCodecSchema.readFloat(input);
        }

        @Override
        public <T> void encode(
            @NotNull StreamCodecSchema<T> streamCodecSchema,
            @NotNull T input,
            @NotNull Float value
        ) {
            streamCodecSchema.writeFloat(input, value);
        }
    };

    public static final StreamCodec<Double> DOUBLE = new StreamCodec<Double>() {

        @Override
        public @NotNull <T> Double decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
            return streamCodecSchema.readDouble(input);
        }

        @Override
        public <T> void encode(
            @NotNull StreamCodecSchema<T> streamCodecSchema,
            @NotNull T input,
            @NotNull Double value
        ) {
            streamCodecSchema.writeDouble(input, value);
        }
    };

    public static final StreamCodec<UUID> UUID = new StreamCodec<>() {

        @Override
        public @NotNull <T> UUID decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
            return new UUID(streamCodecSchema.readLong(input), streamCodecSchema.readLong(input));
        }

        @Override
        public <T> void encode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input, @NotNull UUID value) {
            streamCodecSchema.writeLong(input, value.getMostSignificantBits());
            streamCodecSchema.writeLong(input, value.getLeastSignificantBits());
        }
    };

    public static final StreamCodec<Integer> VAR_INT = new StreamCodec<>() {

        @Override
        public <T> void encode(
            @NotNull StreamCodecSchema<T> streamCodecSchema,
            @NotNull T input,
            @NotNull Integer value
        ) {
            // While more than 7 bits remain...
            while ((value & 0xFFFFFF80) != 0) {
                // Write lower 7 bits and set MSB to 1.
                streamCodecSchema.writeByte(input, (byte) ((value & 0x7F) | 0x80));
                // Logical shift right by 7 (removing written bits).
                value >>>= 7;
            }

            // Write last 7 bits with MSB 0 (final byte).
            streamCodecSchema.writeByte(input, (byte) (value & 0x7F));
        }

        @Override
        public @NotNull <T> Integer decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
            var value = 0;
            var position = 0;
            byte currentByte;

            do {
                currentByte = streamCodecSchema.readByte(input);
                // Extract 7 bits and shift into place.
                value |= (currentByte & 0x7F) << position;
                // Move to the next 7-bit chunk.
                position += 7;
                // Continue if MSB is set.
            } while ((currentByte & 0x80) != 0);

            return value;
        }
    };

    public static final StreamCodec<String> STRING_UTF8 = new StreamCodec<>() {

        @Override
        public <T> void encode(
            @NotNull StreamCodecSchema<T> streamCodecSchema,
            @NotNull T input,
            @NotNull String value
        ) {
            var messageBytes = value.getBytes(StandardCharsets.UTF_8);
            VAR_INT.encode(streamCodecSchema, input, messageBytes.length);
            streamCodecSchema.writeBytes(input, messageBytes);
        }

        @Override
        public @NotNull <T> String decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
            var length = VAR_INT.decode(streamCodecSchema, input);
            var bytes = streamCodecSchema.readBytes(input, length);
            return new String(bytes, StandardCharsets.UTF_8);
        }
    };

    // Option Codecs

    public static final StreamCodec<Option<Byte>> OPTION_BYTE = StreamCodecs.BYTE.asOption();

    public static final StreamCodec<Option<Character>> OPTION_CHAR = StreamCodecs.CHAR.asOption();

    public static final StreamCodec<Option<Short>> OPTION_SHORT = StreamCodecs.SHORT.asOption();

    public static final StreamCodec<Option<Integer>> OPTION_INT = StreamCodecs.INT.asOption();

    public static final StreamCodec<Option<Integer>> OPTION_VAR_INT = StreamCodecs.VAR_INT.asOption();

    public static final StreamCodec<Option<Long>> OPTION_LONG = StreamCodecs.LONG.asOption();

    public static final StreamCodec<Option<Float>> OPTION_FLOAT = StreamCodecs.FLOAT.asOption();

    public static final StreamCodec<Option<Double>> OPTION_DOUBLE = StreamCodecs.DOUBLE.asOption();

    public static final StreamCodec<Option<UUID>> OPTION_UUID = StreamCodecs.UUID.asOption();

    public static final StreamCodec<Option<String>> OPTION_STRING = StreamCodecs.STRING_UTF8.asOption();

    // Optional Codecs

    public static final StreamCodec<Optional<Byte>> OPTIONAL_BYTE = StreamCodecs.BYTE.asOptional();

    public static final StreamCodec<Optional<Character>> OPTIONAL_CHAR = StreamCodecs.CHAR.asOptional();

    public static final StreamCodec<Optional<Short>> OPTIONAL_SHORT = StreamCodecs.SHORT.asOptional();

    public static final StreamCodec<Optional<Integer>> OPTIONAL_INT = StreamCodecs.INT.asOptional();

    public static final StreamCodec<Optional<Integer>> OPTIONAL_VAR_INT = StreamCodecs.VAR_INT.asOptional();

    public static final StreamCodec<Optional<Long>> OPTIONAL_LONG = StreamCodecs.LONG.asOptional();

    public static final StreamCodec<Optional<Float>> OPTIONAL_FLOAT = StreamCodecs.FLOAT.asOptional();

    public static final StreamCodec<Optional<Double>> OPTIONAL_DOUBLE = StreamCodecs.DOUBLE.asOptional();

    public static final StreamCodec<Optional<UUID>> OPTIONAL_UUID = StreamCodecs.UUID.asOptional();

    public static final StreamCodec<Optional<String>> OPTIONAL_STRING = StreamCodecs.STRING_UTF8.asOptional();
}
