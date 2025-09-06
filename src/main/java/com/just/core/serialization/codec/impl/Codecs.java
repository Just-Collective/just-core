package com.just.core.serialization.codec.impl;

import java.util.Optional;
import java.util.UUID;

import com.just.core.functional.option.Option;
import com.just.core.functional.result.Result;
import com.just.core.serialization.codec.Codec;
import com.just.core.serialization.codec.schema.CodecSchema;

public class Codecs {

    public static final Codec<Boolean> BOOLEAN = new Codec<>() {

        @Override
        public <T> Result<Boolean, T> decode(CodecSchema<T> codecSchema, T input) {
            return codecSchema.getBooleanValue(input);
        }

        @Override
        public <T> T encode(CodecSchema<T> codecSchema, Boolean value) {
            return codecSchema.createBooleanValue(value);
        }

        @Override
        public String toString() {
            return "Boolean";
        }
    };

    public static final Codec<Byte> BYTE = new Codec<>() {

        @Override
        public <T> Result<Byte, T> decode(CodecSchema<T> codecSchema, T input) {
            return codecSchema.getNumberValue(input)
                .map(Number::byteValue);
        }

        @Override
        public <T> T encode(CodecSchema<T> codecSchema, Byte value) {
            return codecSchema.createByteValue(value);
        }

        @Override
        public String toString() {
            return "Byte";
        }
    };

    public static final Codec<Short> SHORT = new Codec<>() {

        @Override
        public <T> Result<Short, T> decode(CodecSchema<T> codecSchema, T input) {
            return codecSchema.getNumberValue(input)
                .map(Number::shortValue);
        }

        @Override
        public <T> T encode(CodecSchema<T> codecSchema, Short value) {
            return codecSchema.createShortValue(value);
        }

        @Override
        public String toString() {
            return "Short";
        }
    };

    public static final Codec<Integer> INT = new Codec<>() {

        @Override
        public <T> Result<Integer, T> decode(CodecSchema<T> codecSchema, T input) {
            return codecSchema.getNumberValue(input)
                .map(Number::intValue);
        }

        @Override
        public <T> T encode(CodecSchema<T> codecSchema, Integer value) {
            return codecSchema.createIntValue(value);
        }

        @Override
        public String toString() {
            return "Int";
        }
    };

    public static final Codec<Float> FLOAT = new Codec<>() {

        @Override
        public <T> Result<Float, T> decode(CodecSchema<T> codecSchema, T input) {
            return codecSchema.getNumberValue(input)
                .map(Number::floatValue);
        }

        @Override
        public <T> T encode(CodecSchema<T> codecSchema, Float value) {
            return codecSchema.createFloatValue(value);
        }

        @Override
        public String toString() {
            return "Float";
        }
    };

    public static final Codec<Long> LONG = new Codec<>() {

        @Override
        public <T> Result<Long, T> decode(CodecSchema<T> codecSchema, T input) {
            return codecSchema.getNumberValue(input)
                .map(Number::longValue);
        }

        @Override
        public <T> T encode(CodecSchema<T> codecSchema, Long value) {
            return codecSchema.createLongValue(value);
        }

        @Override
        public String toString() {
            return "Long";
        }
    };

    public static final Codec<Double> DOUBLE = new Codec<>() {

        @Override
        public <T> Result<Double, T> decode(CodecSchema<T> codecSchema, T input) {
            return codecSchema.getNumberValue(input)
                .map(Number::doubleValue);
        }

        @Override
        public <T> T encode(CodecSchema<T> codecSchema, Double value) {
            return codecSchema.createDoubleValue(value);
        }

        @Override
        public String toString() {
            return "Double";
        }
    };

    public static final Codec<String> STRING = new Codec<>() {

        @Override
        public <T> Result<String, T> decode(CodecSchema<T> codecSchema, T input) {
            return codecSchema.getStringValue(input);
        }

        @Override
        public <T> T encode(CodecSchema<T> codecSchema, String value) {
            return codecSchema.createStringValue(value);
        }

        @Override
        public String toString() {
            return "String";
        }
    };

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
