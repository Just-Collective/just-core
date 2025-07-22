package com.bvanseg.just.serialization.codec.impl;

import java.util.Optional;
import java.util.UUID;

import com.bvanseg.just.functional.option.Option;
import com.bvanseg.just.functional.result.Result;
import com.bvanseg.just.serialization.codec.Codec;
import com.bvanseg.just.serialization.codec.Decoder;
import com.bvanseg.just.serialization.codec.Encoder;
import com.bvanseg.just.serialization.codec.schema.CodecSchema;

public class Codecs {

    public static final Codec<Boolean> BOOLEAN = Codec.of("Boolean", new Decoder<>() {

        @Override
        public <T> Result<Boolean, T> decode(CodecSchema<T> codecSchema, T input) {
            return codecSchema.getBooleanValue(input);
        }
    }, new Encoder<>() {

        @Override
        public <T> T encode(CodecSchema<T> codecSchema, Boolean value) {
            return codecSchema.createBooleanValue(value);
        }
    });

    public static final Codec<Byte> BYTE = Codec.of("Byte", new Decoder<>() {

        @Override
        public <T> Result<Byte, T> decode(CodecSchema<T> codecSchema, T input) {
            return codecSchema.getNumberValue(input)
                .map(Number::byteValue);
        }
    }, new Encoder<>() {

        @Override
        public <T> T encode(CodecSchema<T> codecSchema, Byte value) {
            return codecSchema.createByteValue(value);
        }
    });

    public static final Codec<Short> SHORT = Codec.of("Short", new Decoder<>() {

        @Override
        public <T> Result<Short, T> decode(CodecSchema<T> codecSchema, T input) {
            return codecSchema.getNumberValue(input)
                .map(Number::shortValue);
        }
    }, new Encoder<>() {

        @Override
        public <T> T encode(CodecSchema<T> codecSchema, Short value) {
            return codecSchema.createShortValue(value);
        }
    });

    public static final Codec<Integer> INT = Codec.of("Int", new Decoder<>() {

        @Override
        public <T> Result<Integer, T> decode(CodecSchema<T> codecSchema, T input) {
            return codecSchema.getNumberValue(input)
                .map(Number::intValue);
        }
    }, new Encoder<>() {

        @Override
        public <T> T encode(CodecSchema<T> codecSchema, Integer value) {
            return codecSchema.createIntValue(value);
        }
    });

    public static final Codec<Float> FLOAT = Codec.of("Float", new Decoder<>() {

        @Override
        public <T> Result<Float, T> decode(CodecSchema<T> codecSchema, T input) {
            return codecSchema.getNumberValue(input)
                .map(Number::floatValue);
        }
    }, new Encoder<>() {

        @Override
        public <T> T encode(CodecSchema<T> codecSchema, Float value) {
            return codecSchema.createFloatValue(value);
        }
    });

    public static final Codec<Long> LONG = Codec.of("Long", new Decoder<>() {

        @Override
        public <T> Result<Long, T> decode(CodecSchema<T> codecSchema, T input) {
            return codecSchema.getNumberValue(input)
                .map(Number::longValue);
        }
    }, new Encoder<>() {

        @Override
        public <T> T encode(CodecSchema<T> codecSchema, Long value) {
            return codecSchema.createLongValue(value);
        }
    });

    public static final Codec<Double> DOUBLE = Codec.of("Double", new Decoder<>() {

        @Override
        public <T> Result<Double, T> decode(CodecSchema<T> codecSchema, T input) {
            return codecSchema.getNumberValue(input)
                .map(Number::doubleValue);
        }
    }, new Encoder<>() {

        @Override
        public <T> T encode(CodecSchema<T> codecSchema, Double value) {
            return codecSchema.createDoubleValue(value);
        }
    });

    public static final Codec<String> STRING = Codec.of("String", new Decoder<>() {

        @Override
        public <T> Result<String, T> decode(CodecSchema<T> codecSchema, T input) {
            return codecSchema.getStringValue(input);
        }
    }, new Encoder<>() {

        @Override
        public <T> T encode(CodecSchema<T> codecSchema, String value) {
            return codecSchema.createStringValue(value);
        }
    });

    public static final Codec<UUID> UUID = new UUIDCodec();

    public static final Codec<UUID> UUID_STRING = STRING.contraMapFlatMap(
        java.util.UUID::fromString,
        java.util.UUID::toString
    );

    // Option Codecs

    public static final Codec<Option<Byte>> OPTION_BYTE = BYTE.asOption();

    public static final Codec<Option<Short>> OPTION_SHORT = SHORT.asOption();

    public static final Codec<Option<Integer>> OPTION_INT = INT.asOption();

    public static final Codec<Option<Float>> OPTION_FLOAT = FLOAT.asOption();

    public static final Codec<Option<Long>> OPTION_LONG = LONG.asOption();

    public static final Codec<Option<Double>> OPTION_DOUBLE = DOUBLE.asOption();

    public static final Codec<Option<String>> OPTION_STRING = STRING.asOption();

    public static final Codec<Option<UUID>> OPTION_UUID = UUID.asOption();

    public static final Codec<Option<UUID>> OPTION_UUID_STRING = UUID_STRING.asOption();

    // Optional Codecs

    public static final Codec<Optional<Byte>> OPTIONAL_BYTE = BYTE.asOptional();

    public static final Codec<Optional<Short>> OPTIONAL_SHORT = SHORT.asOptional();

    public static final Codec<Optional<Integer>> OPTIONAL_INT = INT.asOptional();

    public static final Codec<Optional<Float>> OPTIONAL_FLOAT = FLOAT.asOptional();

    public static final Codec<Optional<Long>> OPTIONAL_LONG = LONG.asOptional();

    public static final Codec<Optional<Double>> OPTIONAL_DOUBLE = DOUBLE.asOptional();

    public static final Codec<Optional<String>> OPTIONAL_STRING = STRING.asOptional();

    public static final Codec<Optional<UUID>> OPTIONAL_UUID = UUID.asOptional();

    public static final Codec<Optional<UUID>> OPTIONAL_UUID_STRING = UUID_STRING.asOptional();

    Codecs() {
        throw new UnsupportedOperationException();
    }
}
